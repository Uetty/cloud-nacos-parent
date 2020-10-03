package com.uetty.nacos.resp;

import lombok.Getter;

@Getter
public enum ResponseCode {

    SUCCESS("200000"),

    M1_FAILED("5010000");
    ;

    private final String responseCode;

    private final String responseMessage;

    ResponseCode(String responseCode) {
        this.responseCode = responseCode;
        this.responseMessage = null;
    }
}
