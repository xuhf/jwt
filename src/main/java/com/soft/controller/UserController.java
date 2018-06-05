package com.soft.controller;

import com.soft.domain.User;
import com.soft.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(value = "/user", produces = "text/html;charset=UTF-8")
public class UserController {

    private IUserService userService;

    @Autowired
    public UserController(IUserService userService) {
        this.userService = userService;
    }

    /**
     * 用户登录
     *
     * @param username 用户名
     * @param password 密码
     * @return 操作结果
     * @throws AuthenticationException 错误信息
     */
    @RequestMapping(value = "/login", params = {"username", "password"})
    public String getToken(String username, String password) {
        String token = null;
        try {
            token = userService.login(username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return token;
    }

    /**
     * 用户注册
     *
     * @param user 用户信息
     * @return 操作结果
     * @throws AuthenticationException 错误信息
     */
    @RequestMapping(value = "/register")
    public String register(User user) throws AuthenticationException {
        return userService.register(user);
    }

    /**
     * 刷新密钥
     *
     * @param authorization 原密钥
     * @return 新密钥
     * @throws AuthenticationException 错误信息
     */
    @RequestMapping(value = "/refreshToken")
    public String refreshToken(@RequestHeader String authorization) throws AuthenticationException {
        return userService.refreshToken(authorization);
    }

}