package com.uetty.nacos.blogfetcher.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/git")
public class FetcherController {

    @Value(value = "${app.tip:}")
    private String tip;

    @Value(value = "${ext.name:}")
    private String ext;

    @RequestMapping("getTip")
    public String getTip() {
        return "tip: " + tip;
    }

    @RequestMapping("getExt")
    public String getExt() {
        return "extName: " + ext;
    }
}
