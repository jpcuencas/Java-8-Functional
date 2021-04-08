package com.everis.alicante.java8.service;

import com.everis.alicante.java8.model.Transaction;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

public interface TransactionService {
    List<Transaction> filterBig(Collection<Transaction> transactions);

    String getAsString(Collection<Transaction> transactions);

    boolean hasOrdinary(Collection<Transaction> transactions);

    BigDecimal sum(Collection<Transaction> transactions);
}
