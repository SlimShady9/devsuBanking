package com.devsu.customer.infrastructure.config;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.devsu.customer.domain.repository.PasswordHasher;

@Component
public class PasswordHasherImpl implements PasswordHasher {

    private final PasswordEncoder passwordEncoder;

    public PasswordHasherImpl() {
        this.passwordEncoder = new BCryptPasswordEncoder(12);
    }

    @Override
    public String hashPassword(String password) {
        return passwordEncoder.encode(password);
    }

}
