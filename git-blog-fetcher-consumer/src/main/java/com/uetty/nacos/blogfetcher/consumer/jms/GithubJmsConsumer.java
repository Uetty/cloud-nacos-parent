package com.uetty.nacos.blogfetcher.consumer.jms;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.uetty.nacos.blogfetcher.jms.GithubJmsListener;
import com.uetty.nacos.blogfetcher.vo.GithubTaskVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
@Component
public class GithubJmsConsumer implements GithubJmsListener {

    @Override
    public void onBlogDownloaded(@Payload GithubTaskVo taskVo, @Headers Map<String, Object> headers) {


        try {
            log.info("header -> {}", new ObjectMapper().writeValueAsString(headers));
        } catch (JsonProcessingException e) {
//            e.printStackTrace();
        }
        log.info("consume github complete --> {}", taskVo.getTaskCode());
    }
}
