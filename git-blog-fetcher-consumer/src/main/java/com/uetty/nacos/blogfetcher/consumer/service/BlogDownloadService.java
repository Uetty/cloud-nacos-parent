package com.uetty.nacos.blogfetcher.consumer.service;

import com.uetty.nacos.blogfetcher.api.BlogDownloadApi;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.FeignClientsConfiguration;

@FeignClient(name="blog-fetcher", configuration = FeignClientsConfiguration.class)
public interface BlogDownloadService extends BlogDownloadApi {

}
