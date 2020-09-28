package com.uetty.nacos.blogfetcher.component;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Getter
@Component
public class RandomIdentity implements ApplicationListener<ApplicationStartedEvent> {

    private String id;

    @SuppressWarnings("NullableProblems")
    @Override
    public void onApplicationEvent(ApplicationStartedEvent applicationStartedEvent) {
        id = UUID.randomUUID().toString();
        log.info("application-random-id: {}", id);
    }

}
