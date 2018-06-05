package com.soft.dao;

import com.soft.domain.User;

/**
 * Created by xuhf on 2018/6/5.
 */
public interface IUserRepository {

    /**
     * 通过用户名查找用户
     *
     * @param username 用户名
     * @return 用户信息
     */
    User findByUsername(String username);


    void insert(User user);
}
