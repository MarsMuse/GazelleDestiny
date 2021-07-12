package com.artisan.script.factory;

import groovy.lang.GroovyClassLoader;
import groovy.lang.Script;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 动态参数工厂
 *
 * @author xz man
 * @since 2021/7/12 19:41
 */
public class DynamicVariableFactory {

    /**
     * 类加载器
     */
    private static GroovyClassLoader CLASS_LOADER = new GroovyClassLoader();
    /**
     * 缓存
     */
    private static Map<String, Class<Script>> CLASS_CACHE = new ConcurrentHashMap<>();

    private static MessageDigest MD5 = null;

    static {
        try {
            MD5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取到类
     *
     * @author xz man
     * @since 2021/7/12 19:42
     */
    public static Class<Script> getClass(String sourceCode) {
        String key = null == MD5 ? sourceCode :
                new BigInteger(1, MD5.digest(sourceCode.getBytes())).toString(16);
        return CLASS_CACHE.computeIfAbsent(key, (k) -> (Class<Script>) CLASS_LOADER.parseClass(sourceCode));
    }


}
