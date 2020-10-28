package com.uetty.nacos.blogfetcher.jms.channel;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface GithubJmsSink {

    String INPUT_NAME = "blogDownloaded";

    @Input(INPUT_NAME)
    SubscribableChannel input();
}
