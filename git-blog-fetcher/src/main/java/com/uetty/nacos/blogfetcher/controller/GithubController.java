package com.uetty.nacos.blogfetcher.controller;

import brave.http.HttpResponse;
import com.uetty.nacos.annotation.AutoLogSpec;
import com.uetty.nacos.blogfetcher.dto.GithubFetchTriggerDto;
import com.uetty.nacos.blogfetcher.entity.Task;
import com.uetty.nacos.blogfetcher.entity.TaskStatus;
import com.uetty.nacos.blogfetcher.jms.BlogDownloadedProducer;
import com.uetty.nacos.blogfetcher.service.GithubService;
import com.uetty.nacos.resp.BaseResponse;
import com.uetty.nacos.util.ResourceUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Random;

@Slf4j
@RestController
@RequestMapping("github")
public class GithubController extends BaseController {

    @Autowired
    private BlogDownloadedProducer githubCompleteProducer;

    @Autowired
    private GithubService githubService;

    @AutoLogSpec("触发博客下载任务")
    @RequestMapping("triggerBlogFetcher")
    public BaseResponse<Object> triggerBlogFetcher(GithubFetchTriggerDto dto) {

        
        return BaseResponse.success();
    }

    @AutoLogSpec("下载博客解析结果")
    @GetMapping(value = "downloadBlogs")
    public void downloadBlogs(String taskId, HttpServletResponse response) {
        Task task = githubService.getTaskById(taskId);
        if (task == null || Objects.equals(task.getStatus(), TaskStatus.FAILED.getId())) {
            downloadFailed(response);
        } else if (Objects.equals(task.getStatus(), TaskStatus.DOWNLOADED.getId())) {
            downloadOutOfDate(response);
        } else if (Objects.equals(task.getStatus(), TaskStatus.COMPLETED.getId())) {
            // TODO 返回文件

        } else {
            downloadTaskNotComplete(response);
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

        byte[] bytes = new byte[1024];
        int len;

        while ((len = inputStream.read(bytes, 0, bytes.length)) != -1) {
            outputStream.write(bytes, 0, len);
        }

        outputStream.flush();
    }

    @GetMapping("getById")
    public BaseResponse<Task> getById(String id) {

        Task task = githubService.getTaskById(id);

        return BaseResponse.success(task);
    }

    @AutoLogSpec("博客下载任务完成消息通知测试")
    @RequestMapping("replyDonned")
    public BaseResponse<Object> noticeTest() {

        int i = new Random().nextInt(100);
        githubCompleteProducer.send("taskCode " + i);
        return successResult();
    }
}
