package com.devsu.customer.domain.model;

import java.util.UUID;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Person {

    private UUID id;
    private String name;
    private Gender gender;
    private int age;

    public Person(UUID id, String name, Gender gender, int age) {
        validateData(name, gender, age);
        this.id = id;
        this.name = name.trim();
        this.gender = gender;
        this.age = age;
    }

    public static Person createPerson(String name, Gender gender, int age) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        if (gender == null) {
            throw new IllegalArgumentException("Gender cannot be null");
        }
        if (age < 19 || age > 120) {
            throw new IllegalArgumentException("Age must be between 19 and 120 years old");
        }

        Person person = new Person();
        person.setName(name.trim());
        person.setGender(gender);
        person.setAge(age);
        return person;

    }

    public void updatePersonData(String name, Gender gender, int age) {
        validateData(name, gender, age);

        this.name = name.trim();
        this.gender = gender;
        this.age = age;
    }

    private void validateData(String name, Gender gender, int age) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        if (gender == null) {
            throw new IllegalArgumentException("Gender cannot be null");
        }
        if (age < 0) {
            throw new IllegalArgumentException("Age cannot be negative");
        }
    }

}
