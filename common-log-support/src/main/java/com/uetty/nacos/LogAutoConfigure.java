package com.uetty.nacos;

import com.uetty.nacos.component.RandomIdentity;
import com.uetty.nacos.constants.Constants;
import com.uetty.nacos.interceptor.LogInterceptor;
import com.uetty.nacos.properties.LogInterceptorProperties;
import com.uetty.nacos.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Import(value = {LogInterceptorProperties.class, RandomIdentity.class, LogInterceptor.class})
@AutoConfigureAfter(value = {LogInterceptorProperties.class, LogInterceptor.class})
@ConditionalOnProperty(
        name = {"com.uetty.nacos.log-support.enabled"},
        matchIfMissing = true
)
@Configuration
public class LogAutoConfigure implements WebMvcConfigurer {

    @Autowired
    private LogInterceptorProperties properties;

    @Autowired
    private LogInterceptor logInterceptor;

    @SuppressWarnings("NullableProblems")
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        String paths = properties.getPaths();
        List<String> pathList = StringUtil.toStringList(paths, Constants.COMMA);

        if (pathList.size() == 0) {
            registry.addInterceptor(logInterceptor).addPathPatterns(Constants.ALL_PATH);
        } else {
            registry.addInterceptor(logInterceptor).addPathPatterns(pathList);
        }
    }
}
