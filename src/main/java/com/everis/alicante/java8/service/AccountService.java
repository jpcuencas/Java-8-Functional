package com.everis.alicante.java8.service;

import com.everis.alicante.java8.model.Account;
import com.everis.alicante.java8.repository.AccountRepository;

import java.util.Optional;

public class AccountService {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Optional<Account> get() {
        return accountRepository.get();
    }
}
