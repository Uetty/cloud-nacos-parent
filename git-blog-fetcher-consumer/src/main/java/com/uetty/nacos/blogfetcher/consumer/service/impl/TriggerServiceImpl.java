package com.uetty.nacos.blogfetcher.consumer.service.impl;

import com.uetty.nacos.blogfetcher.constants.ApiPaths;
import com.uetty.nacos.blogfetcher.consumer.properties.ServiceNames;
import com.uetty.nacos.blogfetcher.consumer.service.TriggerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TriggerServiceImpl implements TriggerService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ServiceNames serviceNames;

    @Override
    public String trigger() {

        return restTemplate.getForObject("http://" + serviceNames.getBlogServiceName() + ApiPaths.FETCH_SERVICE, String.class);
    }
}
