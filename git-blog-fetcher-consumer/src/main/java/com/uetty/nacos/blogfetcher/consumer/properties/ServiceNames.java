package com.uetty.nacos.blogfetcher.consumer.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "provider.service-name")
public class ServiceNames {

    private String blogServiceName;
}
