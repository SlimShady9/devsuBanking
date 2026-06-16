package com.devsu.customer.domain.model;

import com.devsu.customer.domain.repository.PasswordHasher;

public class Password {

    private final String hashedPassword;

    public Password(String plainPassword, PasswordHasher passwordHasher) {
        if (plainPassword == null || plainPassword.trim().isEmpty()) {
            throw new IllegalArgumentException("Password cannot be null or empty");
        }
        // Business rules, password length must be between 4 and 20 characters
        if (plainPassword.length() < 4 || plainPassword.length() > 20) {
            throw new IllegalArgumentException("Password must be between 4 and 20 characters long");
        }
        // Password must contain at least one number
        if (!plainPassword.matches(".*\\d.*")) {
            throw new IllegalArgumentException("Password must contain at least one number");
        }
        // Password must contain at least one special character
        if (!plainPassword.matches(".*[^a-zA-Z0-9].*")) {
            throw new IllegalArgumentException("Password must contain at least one special character");
        }
        this.hashedPassword = passwordHasher.hashPassword(plainPassword);
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

}
