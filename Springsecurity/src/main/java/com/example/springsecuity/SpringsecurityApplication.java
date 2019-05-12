package com.example.springsecuity;

import com.example.springsecuity.model.CustomUser;
import com.example.springsecuity.model.Role;
import com.example.springsecuity.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;

@SpringBootApplication
public class SpringsecurityApplication implements CommandLineRunner {

    @Autowired
    private ProductRepository productRepository;

    public static void main(String[] args) {
        SpringApplication.run(SpringsecurityApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();

        CustomUser customUser=new CustomUser();
        customUser.setUsername("minal");
        customUser.setPassword(passwordEncoder.encode("password"));

        Role role1=new Role();
        role1.setRole_name("ADMIN");
        role1.setCustomUser(customUser);

    customUser.setAuthorities(Arrays.asList(role1));
    productRepository.save(customUser);




    }
}
