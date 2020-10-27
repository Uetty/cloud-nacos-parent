package com.uetty.nacos.blogfetcher.api;

import feign.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

public interface BlogDownloadApi {


    // GET 方法，要加 @RequestParam 并指定 taskId
    @RequestMapping(value = "/github/downloadBlogs", method = RequestMethod.GET)
    Response downloadBlogs(@RequestParam("taskId") String taskId);

    @RequestMapping(value = "/github/downloadBlogs", method = RequestMethod.GET)
    ResponseEntity<byte[]> downloadBlogs2(@RequestParam("taskId") String taskId);
}
