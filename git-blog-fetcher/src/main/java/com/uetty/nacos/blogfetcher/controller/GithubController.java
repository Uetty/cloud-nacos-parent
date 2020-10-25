package com.uetty.nacos.blogfetcher.controller;

import com.sun.corba.se.spi.ior.ObjectKey;
import com.uetty.nacos.annotation.AutoLogSpec;
import com.uetty.nacos.blogfetcher.api.BlogDownloadApi;
import com.uetty.nacos.blogfetcher.dto.GithubFetchTriggerDto;
import com.uetty.nacos.blogfetcher.entity.Task;
import com.uetty.nacos.blogfetcher.jms.BlogDownloadedProducer;
import com.uetty.nacos.blogfetcher.service.GithubService;
import com.uetty.nacos.resp.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

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

    @AutoLogSpec("")
    @RequestMapping(value = "downloadBlogs", method = RequestMethod.GET)
    public void downloadBlogs() {

    }

    @GetMapping("getById")
    public BaseResponse<Task> getById(String id) {

        Task task = githubService.getById(id);

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
