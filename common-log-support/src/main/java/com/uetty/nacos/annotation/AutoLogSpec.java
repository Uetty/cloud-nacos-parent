package com.uetty.nacos.annotation;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AutoLogSpec {

    String value();

    String group() default "DEFAULT";

    String namespace() default "";
}
