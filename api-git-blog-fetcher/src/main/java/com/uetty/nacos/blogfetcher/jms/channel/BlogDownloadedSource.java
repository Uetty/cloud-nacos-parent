package com.uetty.nacos.blogfetcher.jms.channel;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface BlogDownloadedSource {

    String OUTPUT_NAME = "blogDownloaded";

    @Output(OUTPUT_NAME)
    MessageChannel output();

}
