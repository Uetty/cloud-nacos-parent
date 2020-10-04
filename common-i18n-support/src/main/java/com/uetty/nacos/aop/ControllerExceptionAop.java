package com.uetty.nacos.aop;

import com.uetty.nacos.exception.BusinessException;
import com.uetty.nacos.util.I18nUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

@Aspect
@Slf4j
public class ControllerExceptionAop {

    @Autowired
    protected HttpServletRequest httpServletRequest;

    @Pointcut("(@within(org.springframework.web.bind.annotation.RestController) || @within(org.springframework.stereotype.Controller)) && @annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public void i18nPointCut() {}

    @Around("i18nPointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        try {
            log.info("i18n point cut");
            //执行方法
            return point.proceed();
        } catch (BusinessException be) {
            toLocalizedMessage(be);
            throw be;
        }
    }

    private Locale getLocale() {
        Locale locale = null;
        try {
            locale = httpServletRequest.getLocale();
        } catch (Exception e) {
            log.debug(e.getMessage(), e);
        }
        if (locale == null) {
            log.debug("no locale");
            return Locale.CHINA;
        }
        return locale;
    }

    private void toLocalizedMessage(BusinessException be) {
        String message = be.getMessage();
        if (message == null) {
            message = I18nUtil.getMessageByCode(getLocale(), be.getResponseCode());
        } else {
            message = I18nUtil.getMessage(getLocale(), message);
        }
        be.setLocalizedMessage(message);
    }
}
