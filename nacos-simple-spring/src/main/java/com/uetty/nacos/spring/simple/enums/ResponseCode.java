package com.uetty.nacos.spring.simple.enums;

import lombok.Getter;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

/**
 * api接口统一响应状态码
 */
@Getter
public enum ResponseCode implements Identifier {

    /** 成功 */ SUCCESS("1"),

    /* 100_001 - 100_999 系统级错误 */
    /** 失败 */ FAILED("100000"),
    /** 内部错误 */ INTERNAL_ERROR("100001"),
    /* 登录失败 */ LOGIN_FAILED("100002"),
    /** 无权限 */ USER_HAS_NO_ACCESS("100003"),
    /** 404 */ STATUS_404("100404"),

    ;

    private String id; // 返回码

    ResponseCode(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "ResponseCode{" +
                "name=" + name() +
                ",id=" + id +
                '}';
    }

    private static MessageSource messageSource;

    private static final String I18N_MESSAGE_KEY = "reponse.message.code_";
    /**
     * 默认中国区域的消息返回值
     */
    @SuppressWarnings("unused")
    public String getDefaultMessage() {
        return getDefaultMessage(Locale.CHINA);
    }

    @Component
    public static class ContextMessageSourceAware implements InitializingBean {

        @Autowired
        private MessageSource messageSource;

        @Override
        public void afterPropertiesSet() {
            ResponseCode.messageSource = this.messageSource;
        }
    }

    /**
     * 国际化消息返回值
     */
    public String getDefaultMessage(Locale locale) {
        try {
            if (messageSource == null) {
                return null;
            }
            if (locale == null) {
                locale = Locale.CHINA;
            }
            return messageSource.getMessage(I18N_MESSAGE_KEY + getId(), null, locale);
        } catch (Exception ignore) {}
        return null;
    }
}
