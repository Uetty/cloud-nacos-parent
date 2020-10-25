package com.uetty.nacos.blogfetcher.service.impl;

import com.uetty.nacos.annotation.AutoLogSpec;
import com.uetty.nacos.blogfetcher.entity.Task;
import com.uetty.nacos.blogfetcher.mapper.TaskMapper;
import com.uetty.nacos.blogfetcher.service.GithubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GithubServiceImpl implements GithubService {

    @Autowired
    private TaskMapper taskMapper;

    @Override
    public Task getById(String id) {
        return taskMapper.selectById(id);
    }

}
