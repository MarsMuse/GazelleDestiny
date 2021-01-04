package com.artisan.chaos.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 运行行时注解
 *
 * @author zou yao
 * @since 2021/1/4 16:18
 */
@Target({ElementType.TYPE, ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface RuntimeAnnotation {

    String value() default "";
}
