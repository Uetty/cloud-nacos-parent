package com.uetty.nacos.spring.simple.entity;


import lombok.Data;

import java.util.Date;

@Data
public class VerificationCode {

    private Long id;
    private String userId;
    private String value;
    private String type;
    private Date createTime;
    private Date expireTime;

}