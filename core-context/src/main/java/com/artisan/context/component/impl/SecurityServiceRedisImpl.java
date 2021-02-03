package com.artisan.context.component.impl;

import com.artisan.context.component.SecurityService;
import org.springframework.stereotype.Component;

/**
 * 安全服务 --基于redis的实现
 *
 * @author xz man
 * @since 1.8
 */
@Component
public class SecurityServiceRedisImpl implements SecurityService {

    @Override
    public void accessLog() {
        System.out.println("access log starting....");
        System.out.println("access log success....");
    }
}
