package com.uetty.nacos.blogfetcher.dto;

import lombok.Data;

@Data
public class GithubFetchTriggerDto {

    // 预热 url
    private String preUrl;

    // 仓库下载路径
    private String repoDownloadUrl;

    // 任务code
    private String taskCode;

    // 仓库名称（用于任务code + 仓库名称，确定本地存储地址）
    private String repoName;

    // github项目中附件文件夹名称
    private String attachFolderName;

    // 最终网站附件链接基础地址
    private String attachBaseUri;



}
