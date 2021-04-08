package com.everis.alicante.java8.controller;

import com.everis.alicante.java8.model.Account;
import com.everis.alicante.java8.model.AccountVO;
import com.everis.alicante.java8.model.PaymentMethod;
import com.everis.alicante.java8.model.PaymentMethodVO;
import com.everis.alicante.java8.service.AccountService;

public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }
    public AccountVO get() {
        return accountService.get()
                .map(this::toAccountVO)
                .orElseThrow(() -> new RuntimeException("Resource not found (should be a specific exception)"));
    }

    private AccountVO toAccountVO(Account account) {
        return new AccountVO(
                account.getId(),
                account.getDescription(),
                account.getPaymentMethod().map(this::toPaymentMethodVO).orElse(null)
        );
    }

    private PaymentMethodVO toPaymentMethodVO(PaymentMethod paymentMethod) {
        return new PaymentMethodVO(paymentMethod.getName());
    }
}
