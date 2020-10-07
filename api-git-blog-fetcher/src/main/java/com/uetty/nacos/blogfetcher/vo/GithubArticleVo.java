package com.uetty.nacos.blogfetcher.vo;

import lombok.Data;

import java.util.*;

@Data
public class GithubArticleVo {

    // 文章主题
    private String title;
    // 文章创建时间（避免定时任务轮询刷新时，覆盖旧的文章，所以允许设置文章创建时间）
    private Date createTime;
    // 文章层级
    private String hierarchy;
    // 上一篇文章 rid
    private String prevArticleRid;
    // 下一篇文章 rid
    private String nextArticleRid;
    // 文章标签
    private String tags;
    // 主题
    private String topic;
    // 文章访问路径
    private String permalink;
    // 文章关键字
    private List<String> keywords = new ArrayList<>();
    // 文章id
    private String rid;
    // 文章曾经的id（说明现在要替换成新的id）
    private String originId;
    // 文章的私有标志
    private Boolean isShadow;
    // 文章内容
    // 注意：引用同系统下其他文章的链接路径，会被代表引用文章的占位符字符串替换
    private String content;
    // 引用其他文章的id
    // content中引用同系统下其他文章的链接路径，会被代表引用文章的占位符字符串替换
    private Set<String> refIdSet = new HashSet<>();
    // 引用附件url路径集合
    private Set<String> attachmentSet = new HashSet<>();
    private String path;

    /**
     * 根据引用文章的id，获取文章内容中，代表引用文章的占位符字符串
     */
    public static String getMarkNameByRefId(String refId) {
        return "<ref=" + refId + ">";
    }
}
