package com.uetty.nacos.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "com.uetty.nacos.i18n-support")
public class I18nProperties {

    private String keyPrefix = "reponse.message.code_";
}
