package com.uetty.nacos.blogfetcher.controller;

import com.uetty.nacos.annotation.AutoLogSpec;
import com.uetty.nacos.blogfetcher.dto.GithubFetchTriggerDto;
import com.uetty.nacos.blogfetcher.entity.Task;
import com.uetty.nacos.blogfetcher.entity.TaskStatus;
import com.uetty.nacos.blogfetcher.jms.GithubDynamicProducer;
import com.uetty.nacos.blogfetcher.jms.GithubSimpleSender;
import com.uetty.nacos.blogfetcher.service.GithubService;
import com.uetty.nacos.blogfetcher.vo.GithubTaskVo;
import com.uetty.nacos.resp.BaseResponse;
import com.uetty.nacos.util.FileTool;
import com.uetty.nacos.util.ResourceUtil;
import com.uetty.nacos.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("github")
public class GithubController extends BaseController {

    @Autowired
    private GithubDynamicProducer githubCompleteProducer;

    @Autowired
    private GithubService githubService;

    @Value("${app.tmpdir}")
    private String tmpDir;

    private String getTmpDir() {
        if (StringUtil.isNotBlank(tmpDir)) {
            return tmpDir;
        }
        return FileTool.getDefaultTmpDir();
    }

    @AutoLogSpec("触发博客下载任务")
    @RequestMapping("triggerBlogFetcher")
    public BaseResponse<Object> triggerBlogFetcher(GithubFetchTriggerDto dto) {
        
        
        return BaseResponse.success();
    }

    @AutoLogSpec("下载博客解析结果")
    @GetMapping(value = "downloadBlogs")
    public void downloadBlogs(String taskId, HttpServletResponse response) {
        Task task = githubService.getTaskById(taskId);

        try {
            if (task == null || Objects.equals(task.getStatus(), TaskStatus.FAILED.getId())) {
                downloadFailed(response);
            } else if (Objects.equals(task.getStatus(), TaskStatus.DOWNLOADED.getId())) {
                downloadOutOfDate(response);
            } else if (Objects.equals(task.getStatus(), TaskStatus.COMPLETED.getId())) {
                // 返回文件
                String tmpDir = getTmpDir();
                String resultFilePath = task.getResultFilePath();
                File file = new File(tmpDir, resultFilePath);
                try (FileInputStream fis = new FileInputStream(file)) {

                    responseFile(response, fis, "download.zip");
                }

            } else {
                downloadTaskNotComplete(response);
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            downloadFailed(response);
        }

    }

    private void downloadFailed(HttpServletResponse response) {
        try {
            responseFile(response, ResourceUtil.getResourceInputStream(this.getClass(), "/resp/status-failed.zip"), "download.zip");
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }

    private void downloadOutOfDate(HttpServletResponse response) {
        try {
            responseFile(response, ResourceUtil.getResourceInputStream(this.getClass(), "/resp/status-ofd.zip"), "download.zip");
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }

    private void downloadTaskNotComplete(HttpServletResponse response) {
        try {
            responseFile(response, ResourceUtil.getResourceInputStream(this.getClass(), "/resp/status-tnc.zip"), "download.zip");
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }

    private void  responseFile(HttpServletResponse response, InputStream inputStream, String fileName) throws IOException {
        int available = inputStream.available();
        response.setHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes()));
        response.setHeader("Content-Length", "" + available);
        response.setContentType("application/octet-stream");
        ServletOutputStream outputStream = response.getOutputStream();

        FileTool.writeFromInputStream(outputStream, inputStream);
    }

    @GetMapping("getById")
    public BaseResponse<Task> getById(String id) {

        Task task = githubService.getTaskById(id);

        return BaseResponse.success(task);
    }


    @AutoLogSpec("动态binding")
    @RequestMapping("dynamicSend")
    public BaseResponse<Object> dynamicBindingSend(String bindingName) {
        GithubTaskVo taskVo = new GithubTaskVo();
        taskVo.setTaskId(UUID.randomUUID().toString());
        taskVo.setTaskCode(bindingName);
        githubCompleteProducer.send(taskVo);

        return successResult();
    }

    @Autowired
    GithubSimpleSender simpleSender;

    @AutoLogSpec("固定binding")
    @RequestMapping("simpleSend")
    public BaseResponse<Object> simpleSend(String bindingName) {
        GithubTaskVo taskVo = new GithubTaskVo();
        taskVo.setTaskId(UUID.randomUUID().toString());
        taskVo.setTaskCode(bindingName);
        simpleSender.send(taskVo);

        return successResult();
    }
}
