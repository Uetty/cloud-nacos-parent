package com.uetty.nacos.spring.simple.entity;

import lombok.Data;

@Data
public class BaseResponse<T> {

    private String status;
    private String message;
    private T data;

}
