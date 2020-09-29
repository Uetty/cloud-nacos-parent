package com.uetty.nacos.component;

import com.uetty.nacos.properties.LogInterceptorProperties;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Getter
public class RandomIdentity implements ApplicationListener<ApplicationStartedEvent> {

    private final String serverId;

    private final LogInterceptorProperties properties;

    public RandomIdentity(LogInterceptorProperties properties) {
        this.properties = properties;
        String id = properties.getServerId();
        if (id == null || "".equalsIgnoreCase(id.trim())) {
            id = UUID.randomUUID().toString();
        }
        this.serverId = id;
    }

    @SuppressWarnings("NullableProblems")
    @Override
    public void onApplicationEvent(ApplicationStartedEvent applicationStartedEvent) {
        log.info("application-server-id: {}", serverId);
    }

}
