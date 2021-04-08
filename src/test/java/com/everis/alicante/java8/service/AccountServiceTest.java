package com.everis.alicante.java8.service;

import com.everis.alicante.java8.model.Account;
import com.everis.alicante.java8.repository.AccountRepository;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class AccountServiceTest {

    private AccountService accountService = new AccountService(new AccountRepository());

    @Test
    void testGet() {

        Optional<Account> account = accountService.get();
        assertNotNull(account);
    }
}