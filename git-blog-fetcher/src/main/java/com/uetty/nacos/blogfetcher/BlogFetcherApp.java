package com.uetty.nacos.blogfetcher;

import com.uetty.nacos.blogfetcher.jms.GithubSimpleSender;
import com.uetty.nacos.blogfetcher.jms.channel.GithubJmsSource;
import com.uetty.nacos.exception.CommonExceptionAdvice;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
@EnableBinding(GithubJmsSource.class)
public class BlogFetcherApp {

    @Bean
    public GithubSimpleSender githubSimpleSender() {
        return new GithubSimpleSender();
    }

    @Bean
    public CommonExceptionAdvice commonExceptionAdvice() {
        return new CommonExceptionAdvice();
    }

    public static void main(String[] args) {
        SpringApplication.run(BlogFetcherApp.class, args);
    }
}
