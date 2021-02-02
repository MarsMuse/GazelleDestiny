package com.artisan.chaos.security;

import java.io.File;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Enumeration;
import java.util.Properties;
import java.util.StringTokenizer;

/**
 * 测试访问控制
 *
 * @author zou yao
 * @since 2021/1/25 16:36
 */
public class AccessControllerTest {

    public static void main(String[] args) {
        AccessController.doPrivileged(new SystemProperties());
        System.out.printf("当前类加载器：%s\n", AccessControllerTest.class.getName());
        System.out.printf("当前类加载器：%s\n", AccessControllerTest.class.getClassLoader().getClass().getName());
        ClassLoader cl = Thread.currentThread().getContextClassLoader();
        while (null != cl) {
            System.out.println(cl.getClass().getName());
            cl = cl.getParent();
        }

        System.out.println(File.pathSeparator);
        StringTokenizer st = new StringTokenizer(System.getProperty("java.ext.dirs"), File.pathSeparator);
        int length = st.countTokens();
        while (length-- > 0) {
            System.out.println(st.nextToken());
        }

        System.out.println("---------------------------");

        StringTokenizer st1 = new StringTokenizer(System.getProperty("java.class.path"), File.pathSeparator);
        length = st1.countTokens();
        System.out.printf("获取到行数：%d\n", length);
        while (length-- > 0) {
            System.out.println(st1.nextToken());
        }
        int loopLength=1;
        int index;
        for(int i = 0; (index = System.getProperty("java.class.path").indexOf(File.pathSeparator, i)) != -1; i = index + 1) {
            ++loopLength;
        }
        System.out.printf("获取到Loop行数：%d\n", loopLength);


        
    }

    public static class SystemProperties implements PrivilegedAction<Boolean> {

        @Override
        public Boolean run() {
            Properties ps = System.getProperties();
            if (null == ps) {
                return false;
            }
            Enumeration<String> nameEnum = (Enumeration<String>) ps.propertyNames();
            while (nameEnum.hasMoreElements()) {
                String key = nameEnum.nextElement();
                System.out.printf("argument key->%s, value->%s\n", key, ps.getProperty(key));
            }
            System.out.println("SystemProperties access success");
            return true;
        }
    }
}
