package com.uetty.nacos.blogfetcher.jms;

import com.uetty.nacos.blogfetcher.entity.Task;
import com.uetty.nacos.blogfetcher.vo.GithubTaskVo;
import org.springframework.stereotype.Component;

@Component
public class DownloadedHookDynamicProducer extends GithubDynamicSender {

    public void send(Task task) {
        if (task != null) {
            GithubTaskVo taskVo = new GithubTaskVo();
            taskVo.setTaskId(task.getId());
            taskVo.setTaskCode(task.getCode());
            send(taskVo);
        }
    }
}
