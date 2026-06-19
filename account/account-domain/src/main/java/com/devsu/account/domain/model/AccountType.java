package com.devsu.account.domain.model;

public enum AccountType {
    SAVINGS,
    CURRENT;

    public static AccountType fromString(String accountType) {
        return AccountType.valueOf(accountType.toUpperCase());
    }
}
