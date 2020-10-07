package com.uetty.nacos.blogfetcher.controller;

import com.uetty.nacos.annotation.AutoLogSpec;
import com.uetty.nacos.blogfetcher.api.BlogDownloadApi;
import com.uetty.nacos.blogfetcher.dto.GithubFetchTriggerDto;
import com.uetty.nacos.resp.BaseResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("github")
public class GithubController extends BaseController implements BlogDownloadApi {

    @AutoLogSpec("触发博客下载任务")
    @RequestMapping("downloadBlogs")
    @Override
    public BaseResponse<Object> downloadBlogs(GithubFetchTriggerDto dto) {
        return successResult(dto);
    }
}
