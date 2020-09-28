package com.uetty.nacos.blogfetcher.vo;

import lombok.Data;

import java.util.*;

@Data
public class GithubArticle {

    private String title;
    private Date createTime;
    private String tags;
    private String permalink;
    private List<String> keywords = new ArrayList<>();
    private String rid;
    private String originId;
    private String topic;
    private Boolean isShadow;
    private String content;
    private Set<String> refIdSet = new HashSet<>();
    private String path;

}
