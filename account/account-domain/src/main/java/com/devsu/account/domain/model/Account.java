package com.devsu.account.domain.model;

import java.time.Instant;
import java.util.UUID;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Account {

    private final UUID id;
    private final String accountNumber;
    private AccountType accountType;
    private double balance;
    private Boolean state;
    private UUID customerId;
    private final Instant creationDate;

    public Account(UUID id, String accountNumber, AccountType accountType, double balance, Boolean state,
            UUID customerId) {
        if (id == null) {
            throw new IllegalArgumentException("Id cannot be null");
        }
        if (accountNumber == null || accountNumber.trim().isEmpty()) {
            throw new IllegalArgumentException("Account number cannot be null or empty");
        }
        if (accountType == null) {
            throw new IllegalArgumentException("Account type cannot be null");
        }
        if (balance < 0) {
            throw new IllegalArgumentException("Initial balance cannot be negative");
        }
        if (state == null) {
            throw new IllegalArgumentException("State cannot be null");
        }
        if (customerId == null) {
            throw new IllegalArgumentException("Customer id cannot be null");
        }

        this.id = id;
        this.accountNumber = accountNumber;
        this.accountType = accountType;
        this.balance = balance;
        this.state = state;
        this.customerId = customerId;
        this.creationDate = Instant.now();
    }

    public void makeTransaction(double amount) {
        if (this.customerId == null) {
            throw new IllegalStateException("Cannot add movement to an account without a customer");
        }
        if (!this.state) {
            throw new IllegalStateException("Cannot add movement to an inactive account");
        }
        if (amount < 0 && (this.balance + amount < 0)) {
            throw new IllegalArgumentException("Insufficient balance for withdrawal");
        }
        this.balance += amount;
    }

    public void updateAccountData(AccountType accountType, Boolean state) {
        if (accountType == null) {
            throw new IllegalArgumentException("Account type cannot be null");
        }
        if (state == null) {
            throw new IllegalArgumentException("State cannot be null");
        }
        this.accountType = accountType;
        this.state = state;
    }

    public static Account createSavingsAccount(String accountNumber, double balance, UUID customerId) {
        return new Account(UUID.randomUUID(), accountNumber, AccountType.SAVINGS, balance, true, customerId);
    }

    public void removeCustomer() {
        this.customerId = null;
    }

    public void deactiveAccount() {
        this.state = false;
    }

}
