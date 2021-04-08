package com.everis.alicante.java8.service;

import com.everis.alicante.java8.model.Transaction;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class TransactionServiceImpl implements TransactionService {
    
    private static final BigDecimal BIG_TRANSACTION_AMOUNT = new BigDecimal(1000);
    private static final BigDecimal SMALL_TRANSACTION_AMOUNT = new BigDecimal(10);
    
    private static boolean isSmall(Transaction t) {
        return SMALL_TRANSACTION_AMOUNT.compareTo(t.getAmount()) >= 0;
    }
    
    private static boolean isBig(Transaction t) {
        return BIG_TRANSACTION_AMOUNT.compareTo(t.getAmount()) <= 0;
    }
    
    private void notifyOwner(Transaction t) {
        System.out.println("Notify");
    }
    
    private void doNothing(Transaction t) {
    }
    
    private void sendEmail(Transaction t) {
        System.out.println("Send email");
    }
    
    public List<Transaction> filterBig(Collection<Transaction> transactions) {
        return filterTransactions(transactions, TransactionServiceImpl :: isBig);
    }
    
    @Override
    public String getAsString(Collection<Transaction> transactions) {
        return getNotNull(transactions).stream()
                .map(this :: getAsString)
                .collect(Collectors.joining(System.lineSeparator()));
    }
    
    @Override
    public boolean hasOrdinary(Collection<Transaction> transactions) {
        return getNotNull(transactions).stream()
                .anyMatch(this :: isOrdinary);
    }
    
    private <T> Collection<T> getNotNull(Collection<T> transactions) {
        return Optional.ofNullable(transactions).orElseGet(Collections :: emptyList);
    }
    
    @Override
    public BigDecimal sum(Collection<Transaction> transactions) {
        return getNotNull(transactions).stream()
                .map(Transaction :: getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal :: add);
    }
    
    
    public Integer sumIntegers(Collection<Integer> integers) {
        return getNotNull(integers).stream()
                .mapToInt(Integer :: intValue)
                .sum();
    }
    
    private boolean isOrdinary(Transaction t) {
        return ! isSmall(t) && ! isBig(t);
    }
    
    private String getAsString(Transaction transaction) {
        return MessageFormat.format(
                "Transfered {0} from {1} to {2}",
                transaction.getAmount(),
                transaction.getSource(),
                transaction.getTarget());
    }
    
    public List<Transaction> filterSmallTransactions(Collection<Transaction> transactions) {
        return filterTransactions(transactions, TransactionServiceImpl :: isSmall);
    }
    
    private List<Transaction> filterTransactions(Collection<Transaction> transactions, Predicate<Transaction> predicate) {
        return getNotNull(transactions).stream()
                .filter(predicate)
                .collect(Collectors.toList());
    }
    
    public void processTransation(Collection<Transaction> transactions) {
        TransactionProcessor processor = new TransactionProcessor();
        for (Transaction transaction : transactions) {
            if (isBig(transaction)) {
                processor.process(
                        transaction,
                        this :: notifyOwner,
                        this :: doNothing);
            } else if (isSmall(transaction)) {
                processor.process(
                        transaction,
                        this :: doNothing,
                        this :: sendEmail);
            } else {
                processor.process(
                        transaction,
                        this :: doNothing,
                        this :: doNothing);
            }
        }
    }
}
