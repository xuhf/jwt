package com.soft.dao.impl;

import com.soft.dao.IUserRepository;
import com.soft.domain.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xuhf on 2018/6/5.
 */
@Component
public class UserRepositoryImpl implements IUserRepository {

    @Override
    public User findByUsername(String username) {
        User user = new User();
        user.setId("1");
        user.setUsername("xuhf");
        user.setPassword("123456");
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = user.getPassword();
        user.setPassword(encoder.encode(rawPassword));
        List<String> roles = new ArrayList<String>();
        roles.add("ROLE_ADMIN");
        user.setRoles(roles);
        return user;
    }

    @Override
    public void insert(User user) {

    }
}
