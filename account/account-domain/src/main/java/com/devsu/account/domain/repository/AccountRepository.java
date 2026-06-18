package com.devsu.account.domain.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.devsu.account.domain.model.Account;

public interface AccountRepository {
    Account save(Account account);

    Optional<Account> findById(UUID id);

    Optional<Account> findByAccountNumber(String accountNumber);

    List<Account> findByClientId(UUID clientId);

    List<Account> findAll();

    void delete(UUID id);

    Account update(UUID id, Account account);
}
