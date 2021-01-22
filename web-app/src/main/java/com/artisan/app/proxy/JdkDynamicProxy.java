package com.artisan.app.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * jdk的动态代理
 *
 * @author xz man
 * @since 2021/1/22 09:55
 */
public class JdkDynamicProxy {

    public static void main(String[] args) {
        UserService us = new UserServiceImpl();
        UserService usp = (UserService) Proxy.newProxyInstance(us.getClass().getClassLoader(),
                us.getClass().getInterfaces(), new UserInvokeLog(us));
        usp.register("artisan","a","mars","1");
        usp.register("admin","a","mars","1");
        usp.login("artisan", "a");
        usp.loginOut("artisan");
    }

    public static interface UserService {
        Map<String, String> USER_DB = new ConcurrentHashMap<>();

        boolean register(String loginCode, String password, String nikeName, String gender);

        boolean login(String loginCode, String password);

        boolean loginOut(String loginCode);
    }

    public static class UserServiceImpl implements UserService {
        static {
            USER_DB.put("admin", "admin@1216");
            USER_DB.put("guest", "guest@1216");
        }

        @Override
        public boolean register(String loginCode, String password, String nikeName, String gender) {
            if (USER_DB.containsKey(loginCode)) {
                System.out.printf("loginCode->%s, password->%s, nikeName->%s, gender->%s register error.\n",
                        loginCode, password, nikeName, gender);
                return false;
            }
            USER_DB.put(loginCode, password);
            System.out.printf("loginCode->%s, password->%s, nikeName->%s, gender->%s register success.\n",
                    loginCode, password, nikeName, gender);
            return true;
        }

        @Override
        public boolean login(String loginCode, String password) {
            String dbPwd = USER_DB.get(loginCode);
            if (null == dbPwd || !dbPwd.equals(password)) {
                System.out.printf("loginCode->%s,  login error.\n",
                        loginCode);
                return false;
            }
            System.out.printf("loginCode->%s, password->%s login success.\n",
                    loginCode, password);
            return true;
        }

        @Override
        public boolean loginOut(String loginCode) {
            System.out.printf("loginCode->%s, login out.\n",
                    loginCode);
            return true;
        }
    }


    public static class UserInvokeLog implements InvocationHandler {

        private UserService userService;

        public UserInvokeLog(UserService userService) {
            Objects.requireNonNull(userService);
            this.userService = userService;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.printf("class name ->%s method name ->%s invoke.\n", userService.getClass().getName(), method.getName());
            return method.invoke(userService, args);
        }
    }
}
