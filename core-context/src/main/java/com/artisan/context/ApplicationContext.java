package com.artisan.context;

import com.artisan.context.component.SecurityService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 应用上下文
 *
 * @author zou yao
 * @since 2021/1/29 10:45
 */
public class ApplicationContext {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext("com.artisan");
        SecurityService ss = ac.getBean(SecurityService.class);
        ss.accessLog();
        System.out.println(ss);
        System.out.println(ac.getBean(SecurityService.class));
    }
}
