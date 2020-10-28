package com.uetty.nacos.blogfetcher.jms;

import com.uetty.nacos.blogfetcher.jms.channel.JmsDestination;
import com.uetty.nacos.blogfetcher.vo.GithubTaskVo;
import com.uetty.nacos.exception.BusinessException;
import com.uetty.nacos.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.binding.BinderAwareChannelResolver;
import org.springframework.messaging.support.MessageBuilder;

public class GithubDynamicSender {

//    @Autowired
//    protected StreamBridge streamBridge; // 这个有问题，接收方没法正确反序列化全部header，虽然只是有 error log 还是能正常拿到消息

    @Autowired
    BinderAwareChannelResolver resolver;

    // 动态选择发送的通道
    public boolean send(GithubTaskVo githubTaskVo) {
        if (StringUtil.isBlank(githubTaskVo.getTaskCode())) {
            throw new BusinessException("task code cannot be empty");
        }

//        // MimeTypeUtils.APPLICATION_JSON 会自动引用 MappingJackson2MessageConverter 转换
//        return streamBridge.send(JmsDestination.DESTINATION_PREFIX + githubTaskVo.getTaskCode(),
//                MessageBuilder.withPayload(githubTaskVo).build(), MimeTypeUtils.APPLICATION_JSON);

        return resolver.resolveDestination(JmsDestination.DESTINATION_PREFIX + githubTaskVo.getTaskCode())
                .send(MessageBuilder.withPayload(githubTaskVo).build());
    }
}
