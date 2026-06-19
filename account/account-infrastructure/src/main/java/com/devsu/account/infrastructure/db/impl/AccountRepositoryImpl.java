package com.devsu.account.infrastructure.db.impl;

import java.util.List;
import java.util.MissingResourceException;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.devsu.account.domain.model.Account;
import com.devsu.account.domain.repository.AccountRepository;
import com.devsu.account.infrastructure.db.jpa.entities.AccountEntity;
import com.devsu.account.infrastructure.db.jpa.repository.JpaAccountRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class AccountRepositoryImpl implements AccountRepository {

    private final JpaAccountRepository jpaAccountRepository;

    @Override
    public Account save(Account account) {

        AccountEntity accountEntity = AccountEntity.fromDomain(account);

        AccountEntity newAccountEntity = jpaAccountRepository.save(accountEntity);

        return newAccountEntity.toDomain();
    }

    @Override
    public Optional<Account> findById(UUID id) {

        return jpaAccountRepository.findById(id).map(AccountEntity::toDomain);

    }

    @Override
    public Optional<Account> findByAccountNumber(String accountNumber) {

        return jpaAccountRepository.findByAccountNumber(accountNumber).map(AccountEntity::toDomain);

    }

    @Override
    public List<Account> findByClientId(UUID clientId) {

        return jpaAccountRepository.findByCustomerId(clientId).stream().map(AccountEntity::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Account> findAll() {

        return jpaAccountRepository.findAll().stream().map(AccountEntity::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Account update(UUID id, Account account) {

        Optional<AccountEntity> accountEntityOptional = jpaAccountRepository.findById(id);

        if (!accountEntityOptional.isPresent()) {
            throw new MissingResourceException("Account not found", "Account", id.toString());
        }

        AccountEntity accountEntity = accountEntityOptional.get();

        accountEntity.setAccountType(account.getAccountType().name());
        accountEntity.setBalance(account.getBalance());
        accountEntity.setState(account.getState());
        accountEntity.setCustomerId(account.getCustomerId());

        AccountEntity updatedAccountEntity = jpaAccountRepository.save(accountEntity);

        return updatedAccountEntity.toDomain();

    }

}
