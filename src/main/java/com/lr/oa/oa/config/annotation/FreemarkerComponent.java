package com.lr.oa.oa.config.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义注解
 *
 * 如果自定义标签很多那么每个标签都要执行configuration.setSharedVariable实现注册，
 * 为了更优雅的实现，可以通过自定义注解的方式。
 * @author lr
 * @date: 2019/7/13
 */
@Target(value = ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Component
public @interface FreemarkerComponent {

    String value() default "";

}
