package com.artisan.app.proxy;

import lombok.SneakyThrows;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;

/**
 * 动态代理 -- JDk
 *
 * @author xz man
 * @since 1.8
 */
public class DynamicProxy {

    @SneakyThrows
    public static void main(String[] args) {
        UserService us = new UserServiceImpl();
        UserService usProxy = (UserService) Proxy.newProxyInstance(us.getClass().getClassLoader(),
                us.getClass().getInterfaces(), new MethodInvocationLog(us));
        usProxy.register("admin", "123456");
    }

    public static class UserServiceImpl implements UserService {
        @Override
        public boolean register(String loginName, String password) {
            System.out.printf("用户注册->登录名:%s密码:%s.\n", loginName, password);
            if (LOGIN_NAME_SET.contains(loginName)) {
                System.out.printf("已经存在登录名:%s的用户，注册失败\n", loginName);
                return false;
            }
            LOGIN_NAME_SET.add(loginName);
            return true;
        }
    }

    public static interface UserService {
        Set<String> LOGIN_NAME_SET = new ConcurrentSkipListSet<>();

        boolean register(String loginName, String password);
    }

    public static class MethodInvocationLog implements InvocationHandler {
        private Object target;

        public MethodInvocationLog(Object target) {
            this.target = target;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if (null == target || null == method) {
                return null;
            }
            System.out.printf("class name->%s, method name->%s invoke success.\n",
                    target.getClass().getName(), method.getName());
            Object result = method.invoke(target, args);
            System.out.printf("invoke result->%s.\n", result);
            return result;
        }
    }
}
