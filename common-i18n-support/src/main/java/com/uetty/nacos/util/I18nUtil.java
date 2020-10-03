package com.uetty.nacos.util;

import com.uetty.nacos.properties.I18nProperties;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

import java.util.Locale;

public class I18nUtil {

    private static MessageSource messageSource;

    private static I18nProperties properties;

    /**
     * 国际化消息返回值
     */
    public static String getMessage(Locale locale, String message) {
        return resolveI18nString(locale, message);
    }

    /**
     * 通过code获取消息
     */
    public static String getMessageByCode(Locale locale, String code) {
        if (code == null || properties.getKeyPrefix() == null) {
            return null;
        }
        return getMessage(locale, properties.getKeyPrefix() + code);
    }

    private static String resolveI18nString(Locale locale, String str) {
        if (str == null) {
            return null;
        }
        char[] chars = str.toCharArray();
        int[] stack = new int[chars.length];
        int stackSize = 0;

        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (i < chars.length) {
            if (chars[i] == '{') {
                sb.append('{');
                stack[stackSize++] = sb.length();
            } else if (chars[i] == '}') {
                if (stackSize > 0) {
                    String code = sb.substring(stack[stackSize - 1], sb.length());
                    sb.delete(stack[--stackSize], sb.length());
                    String i18n = getI18n(locale, code);
                    if (i18n == null) {
                        sb.append(code).append('}');
                    } else {
                        sb.delete(sb.length() - 1, sb.length());
                        sb.append(i18n);
                    }
                } else {
                    sb.append('}');
                }
            } else {
                sb.append(chars[i]);
            }
            i++;
        }

        return sb.toString();
    }

    private static String getI18n(Locale locale, String code) {
        try {
            if (locale == null) {
                locale = Locale.CHINA;
            }
            if (messageSource == null) {
                return code;
            }
            if (code == null || code.length() == 0) {
                return code;
            }

            return messageSource.getMessage(code, null, locale);
        } catch (Exception ignore) {}
        return null;
    }

    public static class I18nUtilInitializer implements InitializingBean {

        @Autowired
        MessageSource messageSource;

        @Autowired
        I18nProperties properties;

        @Override
        public void afterPropertiesSet() {
            I18nUtil.messageSource = this.messageSource;
            I18nUtil.properties = this.properties;
        }
    }
}
