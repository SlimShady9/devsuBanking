package com.devsu.account.dto;

import java.time.Instant;
import java.util.UUID;

import com.devsu.account.domain.model.Movement;

import lombok.Data;

@Data
public class MovementResponse {

    private UUID id;
    private UUID accountId;
    private double amount;
    private Instant creationDate;

    public static MovementResponse fromDomain(Movement movement) {
        MovementResponse response = new MovementResponse();
        response.setId(movement.getId());
        response.setAccountId(movement.getAccountId());
        response.setAmount(movement.getAmount());
        response.setCreationDate(movement.getCreationDate());
        return response;
    }
}
