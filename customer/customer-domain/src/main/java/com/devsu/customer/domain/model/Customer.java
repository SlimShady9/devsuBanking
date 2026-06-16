package com.devsu.customer.domain.model;

import java.util.UUID;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Customer {
    private UUID id;
    private String password;
    private Boolean state;
    private Person person;

    public Customer(UUID id, String hashedPassword, Boolean state, Person person) {
        if (hashedPassword == null) {
            throw new IllegalArgumentException("Password cannot be null or empty");
        }
        if (state == null) {
            throw new IllegalArgumentException("State cannot be null");
        }
        if (person == null) {
            throw new IllegalArgumentException("Person cannot be null");
        }
        this.id = id;
        this.password = hashedPassword;
        this.state = state;
        this.person = person;
    }

}
