package com.everis.alicante.java8.model;

import java.math.BigDecimal;

public class Transaction {

    private final String source;
    private final String target;
    private final BigDecimal amount;

    public Transaction(String source, String target, BigDecimal amount) {
        this.source = source;
        this.target = target;
        this.amount = amount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getSource() {
        return source;
    }

    public String getTarget() {
        return target;
    }
}
