package com.everis.alicante.java8.model;

public class AccountVO {
    private final Integer id;
    private final String description;
    private final PaymentMethodVO paymentMethod;

    public AccountVO(Integer id, String description, PaymentMethodVO paymentMethod) {
        this.id = id;
        this.description = description;
        this.paymentMethod = paymentMethod;
    }

    public PaymentMethodVO getPaymentMethod() {
        return paymentMethod;
    }

    public Integer getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }
}
