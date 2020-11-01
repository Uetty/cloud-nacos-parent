package com.uetty.nacos.blogfetcher.service.impl;

import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;
import com.uetty.nacos.annotation.AutoLogSpec;
import com.uetty.nacos.blogfetcher.ao.BlogTaskTriggerAo;
import com.uetty.nacos.blogfetcher.entity.Task;
import com.uetty.nacos.blogfetcher.jms.GithubTaskProducer;
import com.uetty.nacos.blogfetcher.jms.channel.TaskJmsProcessor;
import com.uetty.nacos.blogfetcher.mapper.TaskMapper;
import com.uetty.nacos.blogfetcher.service.GithubService;
import com.uetty.nacos.exception.BusinessException;
import com.uetty.nacos.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class GithubServiceImpl implements GithubService {

    @Autowired
    private TaskMapper taskMapper;

    @Autowired
    private GithubTaskProducer producer;

    @Override
    public Task getTaskById(String id) {
        return taskMapper.selectById(id);
    }

    private void checkNotBlank(String value, String message) {
        if (StringUtil.isBlank(value)) {
            throw new BusinessException(message);
        }
    }

    @Override
    public Task addTask(BlogTaskTriggerAo ao) {

        checkNotBlank(ao.getTaskCode(), "task code cannot be empty");
        checkNotBlank(ao.getRepoDownloadUrl(), "download url cannot be empty");
        checkNotBlank(ao.getRepoName(), "repo name cannot be empty");
        checkNotBlank(ao.getAttachFolderName(), "attach folder name cannot be empty");
        checkNotBlank(ao.getAttachBaseUri(), "attach base uri cannot be empty");

        Task task = new Task();
        task.setId(UUID.randomUUID().toString());
        task.setCode(ao.getTaskCode());
        task.setRepoName(ao.getRepoName());
        task.setPreUrl(ao.getPreUrl());
        task.setRespDownloadUrl(ao.getRepoDownloadUrl());
        task.setAttachFolderName(ao.getAttachFolderName());
        task.setAttachUriPrefix(ao.getAttachBaseUri());
        Date date = new Date();
        task.setUpdateTime(date);
        task.setCreateTime(date);

        taskMapper.insert(task);

        producer.send(task.getId());

        return task;
    }

    @Override
    public Task consumeTask(String taskId) {


        return null;
    }
}
