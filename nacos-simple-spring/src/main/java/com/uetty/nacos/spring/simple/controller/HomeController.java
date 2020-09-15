package com.uetty.nacos.spring.simple.controller;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.uetty.nacos.spring.simple.entity.BaseResponse;
import com.uetty.nacos.spring.simple.entity.User;
import com.uetty.nacos.spring.simple.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController extends BaseController {

    @NacosValue(value = "${app.tip:}", autoRefreshed = true)
    private String tip;

    @Autowired
    HomeService homeService;

    @RequestMapping("getTip")
    public BaseResponse<String> getTip() {
        return successResult(tip);
    }

    @RequestMapping("getAdmin")
    public BaseResponse<User> getAdmin() {
        User adminUser = homeService.getAdminUser();
        return successResult(adminUser);
    }
}
