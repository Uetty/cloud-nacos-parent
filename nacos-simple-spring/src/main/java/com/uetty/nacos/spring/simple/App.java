package com.uetty.nacos.spring.simple;

import com.alibaba.nacos.api.annotation.NacosProperties;
import com.alibaba.nacos.spring.context.annotation.config.EnableNacosConfig;
import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.logging.ClasspathLoggingApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.GenericApplicationListenerAdapter;

@SpringBootApplication
@Configuration
@EnableNacosConfig(globalProperties = @NacosProperties(serverAddr = "www.uetty.com:8848",
        namespace = "99300844-097b-4a94-86b2-fdc9df9b31d6"))
@NacosPropertySource(dataId = "application.yml", groupId = "nacos-simple-spring", autoRefreshed = true)
@NacosPropertySource(dataId = "application-${spring.profiles.active:dev}.yml", groupId = "nacos-simple-spring", autoRefreshed = true)
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
