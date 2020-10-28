package com.uetty.nacos.blogfetcher.consumer;

import com.uetty.nacos.blogfetcher.jms.channel.GithubJmsSink;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.stream.annotation.EnableBinding;

@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
@EnableBinding(GithubJmsSink.class)
public class BlogFetcherConsumerApp {

    public static void main(String[] args) {
        SpringApplication.run(BlogFetcherConsumerApp.class, args);
    }
}
