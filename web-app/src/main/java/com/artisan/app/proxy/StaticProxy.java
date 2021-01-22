package com.artisan.app.proxy;

import org.springframework.util.Assert;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class StaticProxy {


    public static void main(String[] args) {
        LoginService loginService = new LoginServiceImpl();
        loginService = new LoginServiceLogProxy(loginService);
        loginService = new LoginErrorCountCheck(loginService);
        loginService.login("a", "b");
        loginService.login("a", "b");
        loginService.login("a", "b");
        loginService.login("a", "b");
        loginService.login("a", "b");
        loginService.login("a", "b");
        loginService.login("a", "b");
        loginService.login("a", "b");
        loginService.login("a", "b");
        loginService.login("admin", "Boot@0901");
    }

    public static class LoginErrorCountCheck implements LoginService {

        private static int MAX_ERROR_COUNT = 3;
        private static Map<String, AtomicInteger> ERR_COUNT = new ConcurrentHashMap<>();

        private LoginService loginService;

        @Override
        public String login(String loginCode, String password) {
            AtomicInteger atomicCount = ERR_COUNT.get(loginCode);
            if (null != atomicCount && atomicCount.get() >= MAX_ERROR_COUNT) {
                System.out.println("超出次数被拦截");
                return LoginService.ERR_MSG;
            }
            String msg = loginService.login(loginCode, password);
            if (null == msg || msg.contains(ERR_MSG)) {
                if (null == atomicCount) {
                    atomicCount = ERR_COUNT.putIfAbsent(loginCode, new AtomicInteger(1));
                }
                if (null != atomicCount) {
                    atomicCount.getAndIncrement();
                }
            }
            return msg;
        }

        public LoginErrorCountCheck(LoginService loginService) {
            this.loginService = loginService;
        }
    }

    private static class LoginServiceLogProxy implements LoginService {

        private LoginService loginService;

        @Override
        public String login(String loginCode, String password) {
            System.out.printf("当前用户开始进行登陆，登陆账号为：%s\n", loginCode);
            String msg = loginService.login(loginCode, password);
            if (null == msg || msg.contains(ERR_MSG)) {
                System.out.printf("登陆账号为：%s，登陆失败。\n", loginCode);
            } else {
                System.out.printf("登陆账号为：%s，登陆成功。\n", loginCode);
            }
            return msg;
        }

        public LoginServiceLogProxy(LoginService loginService) {
            this.loginService = loginService;
        }
    }

    private static class LoginServiceImpl implements LoginService {
        static {
            USER_DB.put("admin", "Boot@0901");
            USER_DB.put("guest", "Boot@0902");
        }

        @Override
        public String login(String loginCode, String password) {
            Assert.notNull(loginCode, "登陆名不可为空");
            Assert.notNull(password, "密码不可为空");
            String dbPwd = USER_DB.get(loginCode);
            if (null == dbPwd || !dbPwd.equals(password)) {
                return ERR_MSG;
            }
            return "登陆成功";
        }
    }

    public static interface LoginService {
        String ERR_MSG = "登陆失败";

        Map<String, String> USER_DB = new ConcurrentHashMap<>(2);

        String login(String loginCode, String password);
    }
}
