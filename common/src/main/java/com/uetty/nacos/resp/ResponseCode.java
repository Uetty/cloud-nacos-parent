package com.uetty.nacos.resp;

import lombok.Getter;

@Getter
public enum ResponseCode {

    SUCCESS("200"),
    ERROR("500"),

    M1_FAILED("5010000");
    ;

    private final String responseCode;


    ResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }
}
