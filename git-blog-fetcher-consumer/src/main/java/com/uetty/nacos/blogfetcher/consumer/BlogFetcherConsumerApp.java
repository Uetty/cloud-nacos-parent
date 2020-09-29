package com.uetty.nacos.blogfetcher.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient(autoRegister = false)
public class BlogFetcherConsumerApp {

    public static void main(String[] args) {
        SpringApplication.run(BlogFetcherConsumerApp.class, args);
    }
}
