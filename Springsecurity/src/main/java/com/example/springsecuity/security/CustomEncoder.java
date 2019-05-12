package com.example.springsecuity.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;

@Component
public class CustomEncoder extends BCryptPasswordEncoder {
    public CustomEncoder() {
        super();
    }

    public CustomEncoder(int strength) {
        super(strength);
    }

    public CustomEncoder(int strength, SecureRandom random) {
        super(strength, random);
    }
}
