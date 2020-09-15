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
        namespace = "473c483f-b4da-44a5-8bc9-92d77e4dd5c6"))
@NacosPropertySource(dataId = "application.yml", groupId = "${spring.profiles.active:dev}", autoRefreshed = true)
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
