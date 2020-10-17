package com.uetty.nacos.blogfetcher.jms;

import com.uetty.nacos.blogfetcher.jms.channel.BlogDownloadedSource;
import com.uetty.nacos.blogfetcher.vo.GithubTaskVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;

public abstract class BlogDownloadedSender {


    @Autowired
    protected BlogDownloadedSource source;

    public boolean send(GithubTaskVo githubTaskVo) {
        return this.source.output().send(
                MessageBuilder.withPayload(githubTaskVo).build()
        );
    }
}
