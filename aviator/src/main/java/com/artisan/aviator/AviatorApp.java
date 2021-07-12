package com.artisan.aviator;

import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.Expression;

/**
 * AviatorScript 实践
 *
 * @author xz man
 * @since 2021/6/30 18:09
 */
public class AviatorApp {

    public static void main(String[] args) {
        Expression script = AviatorEvaluator.getInstance().compile("println('Hello, AviatorScript!');");
        script.execute();
    }
}
