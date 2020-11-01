package com.uetty.nacos.blogfetcher.jms;

import com.uetty.nacos.blogfetcher.jms.channel.TaskJmsProcessor;
import com.uetty.nacos.blogfetcher.vo.GithubTaskVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
public class GithubTaskProducer {


    @Autowired
    private TaskJmsProcessor taskProcessor;

    // 发送消息
    public boolean send(String taskId) {
        return taskProcessor.output().send(
                MessageBuilder.withPayload(taskId).build()
        );
    }
}
