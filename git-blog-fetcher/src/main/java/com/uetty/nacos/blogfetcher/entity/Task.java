package com.uetty.nacos.blogfetcher.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("bf_task")
public class Task {

    private String id;

    private String code;

    private String status;

    private String preUrl;

    private String respDownloadUrl;

    private String repoName;

    private String attachFolderName;

    private String attachUriPrefix;

    private String resultFilePath;

    private Date createTime;

    private String createBy;

    private Date updateTime;

    private String updateBy;

    private Integer version = 1;

}
