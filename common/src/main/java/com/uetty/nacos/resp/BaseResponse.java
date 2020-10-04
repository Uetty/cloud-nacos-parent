package com.uetty.nacos.resp;

import lombok.Data;

import java.io.Serializable;

@Data
public class BaseResponse<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    String code;
    String message;
    T data;

    public static <T> BaseResponse<T> success(T data) {
        BaseResponse<T> baseResponse = new BaseResponse<>();
        baseResponse.setCode(ResponseCode.SUCCESS.getResponseCode());
        baseResponse.setMessage("success");
        baseResponse.setData(data);
        return baseResponse;
    }

    public static <T> BaseResponse<T> success(String message, T data) {
        BaseResponse<T> baseResponse = new BaseResponse<>();
        baseResponse.setCode(ResponseCode.SUCCESS.getResponseCode());
        baseResponse.setMessage(message);
        baseResponse.setData(data);
        return baseResponse;
    }

    public static BaseResponse<Object> error(String message) {
        BaseResponse<Object> baseResponse = new BaseResponse<>();
        baseResponse.setCode(ResponseCode.ERROR.getResponseCode());
        baseResponse.setMessage(message);
        return baseResponse;
    }

    public static <T> BaseResponse<T> error(String message, T data) {
        BaseResponse<T> baseResponse = new BaseResponse<>();
        baseResponse.setCode(ResponseCode.ERROR.getResponseCode());
        baseResponse.setMessage(message);
        baseResponse.setData(data);
        return baseResponse;
    }
}
