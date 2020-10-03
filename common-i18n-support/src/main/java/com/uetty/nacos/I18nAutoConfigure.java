package com.uetty.nacos;

import com.uetty.nacos.aop.ControllerExceptionAop;
import com.uetty.nacos.properties.I18nProperties;
import com.uetty.nacos.util.ExceptionBox;
import com.uetty.nacos.util.I18nUtil;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@ConditionalOnProperty(
        name = {"spring.messages.basename"},
        matchIfMissing = true
)
@Configuration
public class I18nAutoConfigure {

    @Bean
    public ExceptionBox exceptionBox() {
        return new ExceptionBox();
    }

    @Bean
    public I18nUtil.I18nUtilInitializer i18nUtilInitializer() {
        return new I18nUtil.I18nUtilInitializer();
    }

    @Bean
    public I18nProperties i18nProperties() {
        return new I18nProperties();
    }

    @ConditionalOnProperty(
            name = {"com.uetty.nacos.i18n-support.exceptionAop"},
            matchIfMissing = true
    )
    @Bean
    public ControllerExceptionAop controllerExceptionAop() {
        return new ControllerExceptionAop();
    }
}
