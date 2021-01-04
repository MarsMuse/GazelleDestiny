package com.artisan.chaos;

/**
 * lambda 表达式的字节码分析
 *
 * @author xz man
 * @since 2020/12/25 17:20
 */
public class LambdaByteCode {


    public void testAsync(){
        String name = "xz man";

        Thread t1 = new Thread(() -> System.out.println(name));
        t1.start();
    }
}
