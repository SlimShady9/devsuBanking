package com.devsu.account.domain.model;

import java.time.Instant;
import java.util.UUID;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Movement {
    private final UUID id;
    private final UUID accountId;
    private final double amount;
    private final Instant creationDate;

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

}
