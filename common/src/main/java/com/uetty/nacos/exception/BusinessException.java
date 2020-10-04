package com.uetty.nacos.exception;


import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Locale;

@EqualsAndHashCode(callSuper = true)
@Data
public class BusinessException extends RuntimeException {

    protected String responseCode;

    protected Locale locale;

    protected String localizedMessage;

    public BusinessException(String message) {
        super(message);
        this.localizedMessage = getMessage();
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
        this.localizedMessage = getMessage();
    }

    public BusinessException(String message, String responseCode) {
        super(message);
        this.responseCode = responseCode;
        this.localizedMessage = getMessage();
    }

    public BusinessException(String message, Throwable cause, String responseCode) {
        super(message, cause);
        this.responseCode = responseCode;
        this.localizedMessage = getMessage();
    }

    public BusinessException(Throwable cause, String responseCode) {
        super(cause);
        this.responseCode = responseCode;
        this.localizedMessage = getMessage();
    }

    public BusinessException(String responseCode, Locale locale) {
        this.responseCode = responseCode;
        this.locale = locale;
        this.localizedMessage = getMessage();
    }

    public BusinessException(String message, String responseCode, Locale locale) {
        super(message);
        this.responseCode = responseCode;
        this.locale = locale;
        this.localizedMessage = getMessage();
    }

    public BusinessException(Throwable cause, String responseCode, Locale locale) {
        super(cause);
        this.responseCode = responseCode;
        this.locale = locale;
        this.localizedMessage = getMessage();
    }

    @Override
    public String getLocalizedMessage() {
        return this.localizedMessage;
    }


}