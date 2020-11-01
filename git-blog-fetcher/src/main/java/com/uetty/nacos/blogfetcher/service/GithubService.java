package com.uetty.nacos.blogfetcher.service;

import com.uetty.nacos.blogfetcher.ao.BlogTaskTriggerAo;
import com.uetty.nacos.blogfetcher.entity.Task;

public interface GithubService {
    Task getTaskById(String id);

    Task addTask(BlogTaskTriggerAo ao);

    Task consumeTask(String taskId);
}
