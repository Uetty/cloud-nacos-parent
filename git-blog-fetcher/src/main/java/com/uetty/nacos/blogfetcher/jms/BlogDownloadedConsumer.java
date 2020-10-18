package com.uetty.nacos.blogfetcher.jms;

import com.uetty.nacos.blogfetcher.vo.GithubTaskVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.Enumeration;
import java.util.Map;

@Slf4j
@Component
public class BlogDownloadedConsumer implements BlogDownloadedListener {

    @Override
    public void onBlogDownloaded(@Payload GithubTaskVo taskVo, @Headers Map<String, Object> headers) {
        StringBuilder sb = new StringBuilder();
        StringBuilder headerValues = new StringBuilder();
        for (String s : headers.keySet()) {
            sb.append(s).append(":");
            headerValues.append(headers.get(s)).append(":");
        }
        if (sb.length() > 0) {
            sb.delete(sb.length() - 1, sb.length());
            headerValues.delete(headerValues.length() - 1, headerValues.length());
        }
        log.info("header names -> {}", sb);
        log.info("header values -> {}", headerValues);
        log.info("consume github complete --> {}", taskVo.getTaskCode());
    }
}
