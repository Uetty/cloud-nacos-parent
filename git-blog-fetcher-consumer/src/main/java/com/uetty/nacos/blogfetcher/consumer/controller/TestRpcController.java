package com.uetty.nacos.blogfetcher.consumer.controller;

import com.uetty.nacos.annotation.AutoLogSpec;
import com.uetty.nacos.blogfetcher.consumer.service.BlogDownloadService;
import com.uetty.nacos.blogfetcher.consumer.service.RestTemplateService;
import com.uetty.nacos.blogfetcher.dto.GithubFetchTriggerDto;
import com.uetty.nacos.resp.BaseResponse;
import com.uetty.nacos.util.FileTool;
import feign.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

@Slf4j
@RestController
@RequestMapping("/trigger")
@RefreshScope
public class TestRpcController {


    @Autowired
    private RestTemplateService restTemplateService;

    @Autowired
    private BlogDownloadService blogDownloadService;

    @RequestMapping("triggerFetcher")
    public String triggerFetcher() {

        return restTemplateService.getBlogServiceName();
    }

    @AutoLogSpec("下载博客")
    @GetMapping("download")
    public BaseResponse<Object> download(String taskId) throws IOException {
        log.info("consumer download");
        Response response = blogDownloadService.downloadBlogs(taskId);
        InputStream body = response.body().asInputStream();

        File file = new File("C:\\Users\\Vince\\Desktop\\新建文件夹\\out.zip");
        if (file.exists()) {
            file.delete();
        }
        file.createNewFile();

        log.info("length -> {}", body.available());
        FileTool.writeToFile(file, body, false);


        return BaseResponse.success();
    }

    @AutoLogSpec("下载博客")
    @GetMapping("download2")
    public BaseResponse<Object> download2(String taskId) throws IOException {
        log.info("consumer download");
        ResponseEntity<byte[]> responseEntity = blogDownloadService.downloadBlogs2(taskId);

        File file = new File("C:\\Users\\Vince\\Desktop\\新建文件夹\\out.zip");
        if (file.exists()) {
            file.delete();
        }
        file.createNewFile();

        byte[] body = responseEntity.getBody();
        log.info("length -> {}", body.length);
        FileTool.writeToFile(file, body, false);


        return BaseResponse.success();
    }
}
