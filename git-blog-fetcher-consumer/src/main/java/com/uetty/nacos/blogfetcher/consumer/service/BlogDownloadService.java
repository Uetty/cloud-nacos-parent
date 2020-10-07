package com.uetty.nacos.blogfetcher.consumer.service;

import com.uetty.nacos.blogfetcher.api.BlogDownloadApi;
import com.uetty.nacos.blogfetcher.dto.GithubFetchTriggerDto;
import com.uetty.nacos.resp.BaseResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.FeignClientsConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name="blog-fetcher", configuration = FeignClientsConfiguration.class)
public interface BlogDownloadService extends BlogDownloadApi {

    @RequestMapping("/github/downloadBlogs")
    @Override
    BaseResponse<Object> downloadBlogs(GithubFetchTriggerDto dto);
}
