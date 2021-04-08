package com.everis.alicante.java8.controller;

import com.everis.alicante.java8.model.AccountVO;
import com.everis.alicante.java8.repository.AccountRepository;
import com.everis.alicante.java8.service.AccountService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountControllerTest {

    private AccountController accountController = new AccountController(new AccountService(new AccountRepository()));

    @Test
    void testGet() {
        AccountVO accountVO = accountController.get();

        assertNotNull(accountVO);
    }
}