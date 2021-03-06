package com.uetty.nacos.blogfetcher.jms;

import com.uetty.nacos.blogfetcher.jms.channel.GithubJmsSink;
import com.uetty.nacos.blogfetcher.vo.GithubTaskVo;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;

import java.util.Map;

public interface GithubJmsListener {

    @StreamListener(GithubJmsSink.INPUT_NAME)
    void onBlogDownloaded(@Payload GithubTaskVo taskVo, @Headers Map<String, Object> headers);

//    @StreamListener(Channels.GITHUB_COMPLETE)
//    @SendTo(..)
//    GithubTaskVo githubCompleteTransform(GithubTaskVo taskVo);
}
