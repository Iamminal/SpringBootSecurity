package com.example.springsecuity.repository;

import com.example.springsecuity.model.CustomUser;
import com.example.springsecuity.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@Component
public class UserDaoImpl {

@Autowired
private ProductRepository productRepository;



    public CustomUser loadByUsername(String username)
    {
        CustomUser customUser=productRepository.findByUsername(username);

        return customUser;
    }
}
