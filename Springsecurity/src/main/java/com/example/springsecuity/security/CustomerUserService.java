package com.example.springsecuity.security;

import com.example.springsecuity.model.CustomUser;
import com.example.springsecuity.repository.UserDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class CustomerUserService implements UserDetailsService {

private UserDaoImpl userDao;

    @Autowired
    public void setUserDao(UserDaoImpl userDao) {
        this.userDao = userDao;
    }

    @Override
    public CustomUser loadUserByUsername(String username) throws UsernameNotFoundException {
        return userDao.loadByUsername(username);
    }
}
