package com.uetty.nacos.blogfetcher.consumer.service.impl;

import com.uetty.nacos.blogfetcher.consumer.properties.ServiceNames;
import com.uetty.nacos.blogfetcher.consumer.service.RestTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RestTemplateServiceImpl implements RestTemplateService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ServiceNames serviceNames;

    @Override
    public String getBlogServiceName() {

        return restTemplate.getForObject("http://" + serviceNames.getBlogServiceName() + "/api/git/fetch", String.class);
    }

}
