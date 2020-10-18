package com.uetty.nacos.interceptor;

import com.uetty.nacos.annotation.AutoLogSpec;
import com.uetty.nacos.component.RandomIdentity;
import com.uetty.nacos.util.IPUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.concurrent.atomic.AtomicLong;

@Slf4j
public class LogInterceptor implements HandlerInterceptor {

    @SuppressWarnings("unused")
    private RandomIdentity randomIdentity;

    private AtomicLong sequenceNumber = new AtomicLong(0);

    public static final String LOG_REQ_TIME = "I_REQ_TIME";
    public static final String LOG_SEQ_ID = "I_SEQ_ID";
    public static final String LOG_SPEC = "LOG_SPEC";

    public LogInterceptor(RandomIdentity randomIdentity) {
        this.randomIdentity = randomIdentity;
    }

    @SuppressWarnings("NullableProblems")
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();

        Enumeration<String> headerNames = request.getHeaderNames();
        StringBuilder sb = new StringBuilder();
        StringBuilder headerValues = new StringBuilder();
        while (headerNames.hasMoreElements()) {
            String s = headerNames.nextElement();
            sb.append(s).append(":");
            headerValues.append(request.getHeader(s)).append(":");
        }
        if (sb.length() > 0) {
            sb.delete(sb.length() - 1, sb.length());
            headerValues.delete(headerValues.length() - 1, headerValues.length());
        }
        log.info("header names -> {}", sb);
        log.info("header values -> {}", headerValues);

        String requestIp = IPUtil.getRequestIp(request);
        String logSpec = "";
        if (handler instanceof HandlerMethod) {
            logSpec = "";
            AutoLogSpec autoLogSpec = ((HandlerMethod) handler).getMethodAnnotation(AutoLogSpec.class);
            if (autoLogSpec != null) {
                logSpec = "LogSpec --> [name=" + autoLogSpec.value() + ",namespace=" + autoLogSpec.namespace() + ",group=" + autoLogSpec.group() + "]";
            }
        }
        Long seq = sequenceNumber.incrementAndGet();
        log.info("[{} : {}] from-ip: {}, uri: {} {}", randomIdentity.getServerId(), seq, requestIp, requestURI, logSpec);

        request.setAttribute(LOG_SEQ_ID, seq);
        request.setAttribute(LOG_SPEC, logSpec);
        request.setAttribute(LOG_REQ_TIME, System.currentTimeMillis());
        return true;
    }

    @SuppressWarnings("NullableProblems")
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("post handle");
    }

    @SuppressWarnings("NullableProblems")
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

        long startTime = (long) request.getAttribute(LOG_REQ_TIME);
        long seq = (long) request.getAttribute(LOG_SEQ_ID);

        long pass = System.currentTimeMillis() - startTime;

        log.info("[{} : {}] complete, {} ms", randomIdentity.getServerId(), seq, pass);
    }
}
