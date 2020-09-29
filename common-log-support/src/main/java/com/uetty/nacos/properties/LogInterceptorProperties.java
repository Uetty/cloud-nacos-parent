package com.uetty.nacos.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "com.uetty.nacos.log-support")
public class LogInterceptorProperties {

    private String serverId;

    private String paths;
}
