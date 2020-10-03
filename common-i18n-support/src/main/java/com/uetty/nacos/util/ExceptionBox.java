package com.uetty.nacos.util;

import com.uetty.nacos.exception.BusinessException;
import com.uetty.nacos.resp.ResponseCode;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

public class ExceptionBox {

    @Autowired
    protected HttpServletRequest httpServletRequest;

    /**
     * 国际化
     */
    protected Locale getLocale() {
        return httpServletRequest.getLocale();
    }


    public BusinessException newCodifiedException(String responseCode) {
        return new BusinessException(responseCode, getLocale());
    }


    public BusinessException newCodifiedException(String message, String responseCode) {
        return new BusinessException(message, responseCode, getLocale());
    }

    public BusinessException newCodifiedException(Throwable cause, String responseCode) {
        return new BusinessException(cause, responseCode, getLocale());
    }
}
