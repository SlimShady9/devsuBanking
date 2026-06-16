package com.devsu.customer.domain.model;

import java.util.UUID;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Person {

    private UUID id;
    private String name;
    private Gender gender;
    private int age;

    public Person(UUID id, String name, Gender gender, int age) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        if (gender == null) {
            throw new IllegalArgumentException("Gender cannot be null");
        }
        if (age < 0) {
            throw new IllegalArgumentException("Age cannot be negative");
        }
        this.id = id;
        this.name = name.trim();
        this.gender = gender;
        this.age = age;
    }

}
