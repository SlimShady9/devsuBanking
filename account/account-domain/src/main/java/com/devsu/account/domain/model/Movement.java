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
public class Movement {
    private UUID id;
    private UUID accountId;
    private double amount;
    private Instant creationDate;

    public Movement(UUID id, UUID accountId, double amount) {
        if (id == null) {
            throw new IllegalArgumentException("Id cannot be null");
        }
        if (accountId == null) {
            throw new IllegalArgumentException("Account id cannot be null");
        }
        if (amount == 0) {
            throw new IllegalArgumentException("Amount cannot be zero");
        }
        this.id = id;
        this.accountId = accountId;
        this.amount = amount;
        this.creationDate = Instant.now();
    }

    public static Movement createMovement(UUID accountId, double amount) {
        if (accountId == null) {
            throw new IllegalArgumentException("Account id cannot be null");
        }
        if (amount == 0) {
            throw new IllegalArgumentException("Amount cannot be zero");
        }

        Movement movement = new Movement();
        movement.setAccountId(accountId);
        movement.setAmount(amount);
        movement.setCreationDate(Instant.now());
        return movement;

    }

}
