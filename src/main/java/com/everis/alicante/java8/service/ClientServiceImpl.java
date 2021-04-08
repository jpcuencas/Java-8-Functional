package com.everis.alicante.java8.service;

import com.everis.alicante.java8.model.Account;
import com.everis.alicante.java8.model.Client;
import com.everis.alicante.java8.model.PaymentMethod;

import java.util.Optional;

public class ClientServiceImpl {

    public String getPaymentMethodName(Client client) {
        return Optional.ofNullable(client)
                .flatMap(Client::getAccount)
                .flatMap(Account::getPaymentMethod)
                .map(PaymentMethod::getName)
                .orElse(null);
    }
}
