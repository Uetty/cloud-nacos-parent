package com.uetty.nacos.blogfetcher.jms.channel;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface TaskJmsProcessor {

    String BINDING_NAME = "blogDownloadTask";

    @Input(BINDING_NAME)
    SubscribableChannel input();

    @Output(BINDING_NAME)
    MessageChannel output();
}
