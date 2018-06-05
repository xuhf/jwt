package com.soft.security;

import com.soft.dao.IUserRepository;
import com.soft.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xuhf on 2018/6/5.
 */
@Component
public class JwtUserDetailsServiceImpl implements UserDetailsService {

    private IUserRepository userRepository;

    @Autowired
    public JwtUserDetailsServiceImpl(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
        } else {
            List<String> roles = user.getRoles();
            List<SimpleGrantedAuthority> list = new ArrayList<SimpleGrantedAuthority>();
            for (String r : roles) {
                SimpleGrantedAuthority s = new SimpleGrantedAuthority(r);
                list.add(s);
            }
            return new JwtUser(user.getUsername(), user.getPassword(), list);
        }
    }


}
