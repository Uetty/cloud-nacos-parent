package com.uetty.nacos.blogfetcher.jms;

import com.uetty.nacos.blogfetcher.jms.channel.BlogDownloadedSink;
import com.uetty.nacos.blogfetcher.vo.GithubTaskVo;
import org.springframework.cloud.stream.annotation.StreamListener;

public interface BlogDownloadedListener {

    @StreamListener(BlogDownloadedSink.INPUT_NAME)
    void onBlogDownloaded(GithubTaskVo taskVo);

//    @StreamListener(Channels.GITHUB_COMPLETE)
//    @SendTo(..)
//    GithubTaskVo githubCompleteTransform(GithubTaskVo taskVo);
}
