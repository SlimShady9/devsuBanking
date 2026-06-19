package com.devsu.account.domain.model;

import java.time.Instant;
import java.util.UUID;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Account {

    private UUID id;
    private String accountNumber;
    private AccountType accountType;
    private double balance;
    private Boolean state;
    private UUID customerId;
    private Instant creationDate;

    public Account(String accountNumber, AccountType accountType, double balance, Boolean state,
            UUID customerId) {
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

        this.accountNumber = accountNumber;
        this.accountType = accountType;
        this.balance = balance;
        this.state = state;
        this.customerId = customerId;
        this.creationDate = Instant.now();
    }

    public static Account createAccount(String accountNumber, UUID customerId, double balance) {

        if (customerId == null) {
            throw new IllegalArgumentException("Customer id cannot be null");
        }

        Account account = new Account();

        account.accountNumber = accountNumber;
        account.accountType = AccountType.SAVINGS;
        account.balance = balance;
        account.state = true;
        account.customerId = customerId;
        account.creationDate = Instant.now();

        return account;
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
        return new Account(accountNumber, AccountType.SAVINGS, balance, true, customerId);
    }

    public void removeCustomer() {
        this.customerId = null;
    }

    public void deactiveAccount() {
        this.state = false;
    }

}
