package com.uetty.nacos.blogfetcher.jms;

import com.uetty.nacos.blogfetcher.jms.channel.GithubJmsSource;
import com.uetty.nacos.blogfetcher.vo.GithubTaskVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;

public class GithubSimpleSender {

    @Autowired
    protected GithubJmsSource source;

    // 发送到固定的通道
    public boolean send(GithubTaskVo githubTaskVo) {
        return this.source.output().send(
                MessageBuilder.withPayload(githubTaskVo).build()
        );
    }
}
