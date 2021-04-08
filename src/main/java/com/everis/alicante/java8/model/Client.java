package com.everis.alicante.java8.model;

import java.util.Optional;

public class Client {
    private final Account account;

    public Client(Account account) {
        this.account = account;
    }

    public Optional<Account> getAccount() {
        return Optional.ofNullable(account);
    }
}
