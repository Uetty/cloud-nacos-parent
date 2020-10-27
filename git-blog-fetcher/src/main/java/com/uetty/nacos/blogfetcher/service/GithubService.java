package com.uetty.nacos.blogfetcher.service;

import com.uetty.nacos.blogfetcher.entity.Task;

public interface GithubService {
    Task getTaskById(String id);
}
