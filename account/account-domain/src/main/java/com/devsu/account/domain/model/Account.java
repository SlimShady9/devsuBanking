package com.devsu.account.domain.model;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Account {

    private final UUID id;
    private final String accountNumber;
    private final AccountType accountType;
    private double balance;
    private final Boolean state;
    private final UUID customerId;
    private final List<Movement> movements;
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
        this.movements = new ArrayList<>();
        this.creationDate = Instant.now();
    }

    public void addMovement(Movement movement) {
        if (movement == null) {
            throw new IllegalArgumentException("Movement cannot be null");
        }
        if (!this.state) {
            throw new IllegalStateException("Cannot add movement to an inactive account");
        }
        if (movement.getAmount() < 0 && (this.balance + movement.getAmount() < 0)) {
            throw new IllegalArgumentException("Insufficient balance for withdrawal");
        }
        this.movements.add(movement);
        this.balance += movement.getAmount();
    }
}
