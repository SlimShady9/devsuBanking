package com.devsu.account.infrastructure.db.jpa.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import com.devsu.account.infrastructure.db.jpa.entities.AccountEntity;

@Repository
public interface JpaAccountRepository extends JpaRepository<AccountEntity, UUID> {

    @NonNull
    Optional<AccountEntity> findById(@NonNull UUID id);

    List<AccountEntity> findByCustomerId(@NonNull UUID customerId);

    Optional<AccountEntity> findByAccountNumber(@NonNull String accountNumber);

}
