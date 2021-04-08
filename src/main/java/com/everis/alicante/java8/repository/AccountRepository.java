package com.everis.alicante.java8.repository;

import com.everis.alicante.java8.model.Account;

import java.util.Optional;

public class AccountRepository {

    public Optional<Account> get() {
        return Optional.of(new Account(10, "Description", null));
    }
}
