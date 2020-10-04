package com.uetty.nacos.blogfetcher;

import com.uetty.nacos.exception.CommonExceptionAdvice;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

import java.beans.Transient;

@SpringBootApplication
@EnableDiscoveryClient
public class BlogFetcherApp {

    @Bean
    public CommonExceptionAdvice commonExceptionAdvice() {
        return new CommonExceptionAdvice();
    }

    public static void main(String[] args) {
        SpringApplication.run(BlogFetcherApp.class, args);
    }
}
