package com.devsu.account.domain.repository;

public class AccountNumberCreator {

    private final AccountRepository accountRepository;

    public AccountNumberCreator(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public String generateAccountNumber() {
        String accountNumber = (long) (Math.random() * 1000000000L) + 1000000000L + "";
        if (accountRepository.findByAccountNumber(accountNumber).isPresent()) {
            throw new IllegalArgumentException("Account number already exists");
        }
        return accountNumber;
    }
}
