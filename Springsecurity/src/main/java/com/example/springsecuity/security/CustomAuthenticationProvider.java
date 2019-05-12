package com.example.springsecuity.security;

import com.example.springsecuity.model.CustomUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
private CustomerUserService customerUserService;
    @Autowired
    public void setCustomerUserService(CustomerUserService customerUserService) {
        this.customerUserService = customerUserService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String name=authentication.getName();
        String password=(String)authentication.getCredentials();
        System.out.println("First"+passwordEncoder.encode(password)+"   "+password);
        CustomUser user=customerUserService.loadUserByUsername(name);
        System.out.println("Second"+user.getPassword());
        if(user==null||!user.getUsername().equalsIgnoreCase(name))
        {
            throw new BadCredentialsException("UserName not Found");
        }
        if(!BCrypt.checkpw(password,user.getPassword()))
        {
            throw new BadCredentialsException("Wrong Password");
        }
        Collection<? extends GrantedAuthority> authorities=user.getAuthorities();
        return new UsernamePasswordAuthenticationToken(user,password,authorities);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }


}
