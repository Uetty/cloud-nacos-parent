package com.uetty.nacos.blogfetcher.consumer.controller;

import com.uetty.nacos.blogfetcher.consumer.service.TriggerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/trigger")
@RefreshScope
public class TriggerController {


    @Autowired
    private TriggerService triggerService;

    @RequestMapping("triggerFetcher")
    public String triggerFetcher() {
        
        String trigger = triggerService.trigger();

        return trigger;
    }
}
