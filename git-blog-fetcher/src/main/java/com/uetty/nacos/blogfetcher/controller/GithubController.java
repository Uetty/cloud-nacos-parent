package com.uetty.nacos.blogfetcher.controller;

import com.uetty.nacos.annotation.AutoLogSpec;
import com.uetty.nacos.blogfetcher.api.BlogDownloadApi;
import com.uetty.nacos.blogfetcher.dto.GithubFetchTriggerDto;
import com.uetty.nacos.blogfetcher.jms.BlogDownloadedProducer;
import com.uetty.nacos.resp.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
@RequestMapping("github")
public class GithubController extends BaseController implements BlogDownloadApi {

    @Autowired
    private BlogDownloadedProducer githubCompleteProducer;

    @AutoLogSpec("触发博客下载任务")
    @RequestMapping("downloadBlogs")
    @Override
    public BaseResponse<Object> downloadBlogs(GithubFetchTriggerDto dto) {
        return successResult(dto);
    }

    @AutoLogSpec("博客下载任务完成消息通知测试")
    @RequestMapping("replyDonned")
    public BaseResponse<Object> noticeTest() {

        int i = new Random().nextInt(100);
        githubCompleteProducer.send("taskCode " + i);
        return successResult();
    }
}
