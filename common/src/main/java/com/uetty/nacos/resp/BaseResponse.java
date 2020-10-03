package com.uetty.nacos.resp;

import lombok.Data;

@Data
public class BaseResponse<T> {

    String code;
    String message;
    T data;

}
