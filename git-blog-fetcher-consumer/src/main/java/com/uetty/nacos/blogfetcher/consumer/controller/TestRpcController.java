package com.uetty.nacos.blogfetcher.consumer.controller;

import com.uetty.nacos.annotation.AutoLogSpec;
import com.uetty.nacos.blogfetcher.consumer.service.BlogDownloadService;
import com.uetty.nacos.blogfetcher.consumer.service.RestTemplateService;
import com.uetty.nacos.blogfetcher.dto.GithubFetchTriggerDto;
import com.uetty.nacos.resp.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/trigger")
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
    @RequestMapping("download")
    public BaseResponse<Object> download() {
        log.info("consumer download");
        GithubFetchTriggerDto dto = new GithubFetchTriggerDto();
        return blogDownloadService.downloadBlogs(dto);
    }
}
