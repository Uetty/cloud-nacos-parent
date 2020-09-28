package com.uetty.nacos.blogfetcher.interceptor;

import com.uetty.nacos.blogfetcher.component.RandomIdentity;
import com.uetty.nacos.blogfetcher.util.IPUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
public class LogInterceptor implements HandlerInterceptor {

    @SuppressWarnings("unused")
    @Autowired
    private RandomIdentity randomIdentity;
    
    @SuppressWarnings("NullableProblems")
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();

        String requestIp = IPUtil.getRequestIp(request);
        log.info("from {} to {} - {}", requestIp, randomIdentity.getId(), requestURI);
        
        return true;
    }
}
