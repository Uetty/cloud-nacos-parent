package com.uetty.nacos.blogfetcher.jms;

import com.uetty.nacos.blogfetcher.vo.GithubTaskVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class BlogDownloadedConsumer implements BlogDownloadedListener {

    @Override
    public void onBlogDownloaded(GithubTaskVo taskVo) {

        log.info("consume github complete --> {}", taskVo.getTaskCode());
    }
}
