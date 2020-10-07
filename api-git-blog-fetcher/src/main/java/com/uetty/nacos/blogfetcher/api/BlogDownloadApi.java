package com.uetty.nacos.blogfetcher.api;

import com.uetty.nacos.blogfetcher.dto.GithubFetchTriggerDto;
import com.uetty.nacos.resp.BaseResponse;

public interface BlogDownloadApi {

    BaseResponse<Object> downloadBlogs(GithubFetchTriggerDto dto);
}
