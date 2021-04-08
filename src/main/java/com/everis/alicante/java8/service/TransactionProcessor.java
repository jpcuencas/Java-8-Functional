package com.everis.alicante.java8.service;

import com.everis.alicante.java8.model.Transaction;

import java.util.function.Consumer;

public class TransactionProcessor {

    public void process(Transaction transaction, Consumer<Transaction> preprocess, Consumer<Transaction> postprocess) {
        preprocess.accept(transaction);
        store(transaction);
        postprocess.accept(transaction);
    }

    private void store(Transaction transaction) {

    }
}

