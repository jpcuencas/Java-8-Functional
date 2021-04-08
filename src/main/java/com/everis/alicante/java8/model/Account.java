package com.everis.alicante.java8.model;

import java.util.Optional;

public class Account {
    private final Integer id;
    private final String description;
    private final PaymentMethod paymentMethod;

    public Account(Integer id, String description, PaymentMethod paymentMethod) {
        this.id = id;
        this.description = description;
        this.paymentMethod = paymentMethod;
    }

    public Optional<PaymentMethod> getPaymentMethod() {
        return Optional.ofNullable(paymentMethod);
    }

    public Integer getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }
}
