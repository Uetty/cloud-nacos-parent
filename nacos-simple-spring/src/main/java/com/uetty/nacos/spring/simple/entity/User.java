package com.uetty.nacos.spring.simple.entity;

import lombok.Data;

import java.util.Date;

@Data
public class User {

    private String id;
    private String username;
    private String displayName;
    private String password;
    private String phoneNumber;
    private String email;
    private String status;
    private String sysRole;
    private Date lastLoginTime;
    private Integer loginFailedTimes;
    private Date createTime;
    private Date updateTime;

}