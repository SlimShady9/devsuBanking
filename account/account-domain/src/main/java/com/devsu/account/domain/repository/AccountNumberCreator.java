package com.devsu.account.domain.repository;

import java.util.UUID;

public class AccountNumberCreator {

    public String generateAccountNumber() {
        long mostSigBits = UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
        return String.valueOf(mostSigBits).substring(0, 10);
    }
}
