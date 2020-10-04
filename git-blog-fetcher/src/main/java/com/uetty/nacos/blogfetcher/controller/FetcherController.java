package com.uetty.nacos.blogfetcher.controller;

import com.uetty.nacos.annotation.AutoLogSpec;
import com.uetty.nacos.blogfetcher.constants.ApiPaths;
import com.uetty.nacos.exception.BusinessException;
import com.uetty.nacos.util.I18nUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
@RefreshScope
public class FetcherController {

    @Value(value = "${app.tip:}")
    private String tip;

    @Value(value = "${ext.name:}")
    private String ext;

    @RequestMapping("/api/getTip")
    public String getTip() {
        System.out.println("-----");
        if (Math.random() > 0.5) {
            throw new BusinessException("{reponse.message.code_100001}");
        }
        return "tip: " + tip;
    }

    @RequestMapping("/api/getExt")
    @AutoLogSpec("获取Ext名称")
    public String getExt() {
        return "extName: " + ext;
    }

    @RequestMapping(ApiPaths.FETCH_SERVICE)
    public String fetch() {
        return "success trigger";
    }

    @RequestMapping("/api/i18n")
    public String testI18n(String message) {

        message = I18nUtil.getMessage(Locale.US, message);

        return message;
    }
}
