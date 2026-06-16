package com.devsu.customer.domain.model;

public enum Gender {
    MALE,
    FEMALE;

    public static Gender fromString(String gender) {
        if (gender == null) {
            throw new IllegalArgumentException("Gender cannot be null");
        }
        if (gender.equalsIgnoreCase("MALE")) {
            return MALE;
        }
        if (gender.equalsIgnoreCase("FEMALE")) {
            return FEMALE;
        }
        throw new IllegalArgumentException("Invalid gender: " + gender);
    }
}
