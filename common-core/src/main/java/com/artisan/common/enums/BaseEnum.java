package com.artisan.common.enums;

/**
 * 基础的枚举类
 *
 * @author xz man
 * @since 1.8
 */
public interface BaseEnum<T> {

    /**
     * 获取到枚举编码
     *
     * @author xzman
     */
    T getCode();

    /**
     * 获取到枚举描述
     *
     * @author xzman
     */
    String getDesc();
}
