package com.uetty.nacos.blogfetcher.jms;

import com.uetty.nacos.blogfetcher.vo.GithubTaskVo;
import org.springframework.stereotype.Component;

@Component
public class BlogDownloadedProducer extends BlogDownloadedSender {

    public void send(String taskCode) {
        GithubTaskVo githubTaskVo = new GithubTaskVo();
        githubTaskVo.setTaskCode(taskCode);
        this.send(githubTaskVo);
    }
}
