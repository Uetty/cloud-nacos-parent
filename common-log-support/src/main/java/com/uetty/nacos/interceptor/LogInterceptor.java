package com.uetty.nacos.interceptor;

import com.uetty.nacos.component.RandomIdentity;
import com.uetty.nacos.util.IPUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class LogInterceptor implements HandlerInterceptor {

    @SuppressWarnings("unused")
    private RandomIdentity randomIdentity;

    public LogInterceptor(RandomIdentity randomIdentity) {
        this.randomIdentity = randomIdentity;
    }

    @SuppressWarnings("NullableProblems")
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();

        String requestIp = IPUtil.getRequestIp(request);
        log.info("from {} to {} - {}", requestIp, randomIdentity.getServerId(), requestURI);
        
        return true;
    }
}
