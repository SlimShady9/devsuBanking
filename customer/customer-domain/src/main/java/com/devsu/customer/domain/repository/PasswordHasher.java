package com.devsu.customer.domain.repository;

public interface PasswordHasher {
    String hashPassword(String password);
}
