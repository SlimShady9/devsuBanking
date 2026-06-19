package com.devsu.account.infrastructure.db.jpa.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.Instant;
import java.util.UUID;

import com.devsu.account.domain.model.Movement;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "movement")
public class MovementEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(name = "account_id")
    private UUID accountId;
    @Column(name = "amount")
    private double amount;
    @Column(name = "creation_date")
    private Instant creationDate;

    public static MovementEntity fromDomain(Movement movement) {
        MovementEntity movementEntity = new MovementEntity();
        movementEntity.setId(movement.getId());
        movementEntity.setAccountId(movement.getAccountId());
        movementEntity.setAmount(movement.getAmount());
        movementEntity.setCreationDate(movement.getCreationDate());
        return movementEntity;
    }

    public Movement toDomain() {
        Movement movement = new Movement(this.id, this.accountId, this.amount);
        return movement;
    }

}
