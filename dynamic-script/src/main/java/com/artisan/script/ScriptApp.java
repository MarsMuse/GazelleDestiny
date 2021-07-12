package com.artisan.script;

import com.artisan.script.factory.DynamicVariableFactory;
import groovy.lang.Binding;
import groovy.lang.Script;

/**
 * 动态脚本
 *
 * @author xz man
 * @since 2021/7/12 19:40
 */
public class ScriptApp {

    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        StringBuilder builder = new StringBuilder();
        builder.append("def var;\n");
        builder.append("if(test) var = 1;\n");
        builder.append("else var = 2;\n");
        Script sc = DynamicVariableFactory.getClass(builder.toString()).newInstance();
        Binding binding = new Binding();
        binding.setVariable("test", true);
        sc.setBinding(binding);
        System.out.println(sc.run());
    }
}
