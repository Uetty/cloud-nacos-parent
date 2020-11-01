package com.uetty.nacos.blogfetcher.jms;

import com.uetty.nacos.blogfetcher.entity.Task;
import com.uetty.nacos.blogfetcher.jms.channel.GithubJmsSink;
import com.uetty.nacos.blogfetcher.service.GithubService;
import com.uetty.nacos.blogfetcher.vo.GithubTaskVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
public class GithubTaskConsumer {

    @Autowired
    private GithubService githubService;

    @Autowired
    private DownloadedHookDynamicProducer dynamicProducer;

    @StreamListener(GithubJmsSink.INPUT_NAME)
    public void onBlogDownloaded(@Payload String taskId) {
        Task task = null;
        try {
            task = githubService.consumeTask(taskId);
        } finally {
            dynamicProducer.send(task);
        }
    }

}
