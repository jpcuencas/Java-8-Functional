package com.everis.alicante.java8.service;

import com.everis.alicante.java8.model.Transaction;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TransactionServiceImplTest {

    private static final String SOURCE = "ES9121000418450200051332";
    private static final String TARGET = "RS35260005601001611379";

    private TransactionServiceImpl transactionService = new TransactionServiceImpl();

    @Test
    void testFilterBigTransactions() {

        Collection<Transaction> transaction = Arrays.asList(
                new Transaction(SOURCE, TARGET, new BigDecimal(10)),
                new Transaction(SOURCE, TARGET, new BigDecimal(100)),
                new Transaction(SOURCE, TARGET, new BigDecimal(999)),
                new Transaction(SOURCE, TARGET, new BigDecimal(1000)),
                new Transaction(SOURCE, TARGET, new BigDecimal(1001)),
                new Transaction(SOURCE, TARGET, new BigDecimal(Integer.MAX_VALUE))
        );

        List<Transaction> result = transactionService.filterBig(transaction);

        assertEquals(3, result.size());
    }

    @Test
    void testFilterSmallTransactions() {

        Collection<Transaction> transaction = Arrays.asList(
                new Transaction(SOURCE, TARGET, new BigDecimal(0)),
                new Transaction(SOURCE, TARGET, new BigDecimal(10)),
                new Transaction(SOURCE, TARGET, new BigDecimal(100)),
                new Transaction(SOURCE, TARGET, new BigDecimal(999)),
                new Transaction(SOURCE, TARGET, new BigDecimal(1000)),
                new Transaction(SOURCE, TARGET, new BigDecimal(1001)),
                new Transaction(SOURCE, TARGET, new BigDecimal(Integer.MAX_VALUE))
        );

        List<Transaction> result = transactionService.filterSmallTransactions(transaction);

        assertEquals(2, result.size());


        A a = new A();
        B b = new B();

        setValue(a::setName);
        setValue(b::setDescription);


        Supplier<StringBuilder> supplier = StringBuilder::new;
        Function<String, StringBuilder> function = StringBuilder::new;
    }

    @Test
    public void testGetAsString() {

        Collection<Transaction> transaction = Arrays.asList(
                new Transaction(SOURCE, TARGET, new BigDecimal(22)),
                new Transaction(SOURCE, TARGET, new BigDecimal(50)),
                new Transaction(SOURCE, TARGET, new BigDecimal(88887)),
                new Transaction(SOURCE, TARGET, new BigDecimal(9)),
                new Transaction(SOURCE, TARGET, new BigDecimal(123123))
        );

        String result = transactionService.getAsString(transaction);

        System.out.println(result);
    }

    @Test
    public void testGetAsStringEmptyCollection() {
        Collection<Transaction> transaction = Collections.emptyList();

        String result = transactionService.getAsString(transaction);

        assertEquals("", result);
    }

    @Test
    public void testGetAsStringBigCollection() {
        Collection<Transaction> transaction = IntStream.range(0, 2_000_000)
                .mapToObj(i -> new Transaction(SOURCE, TARGET, new BigDecimal(i)))
                .collect(Collectors.toList());

        Instant start = Instant.now();

        transactionService.getAsString(transaction);
        System.out.println(Duration.between(start, Instant.now()));
    }

    @Test
    public void testGetAsStringNullCollection() {
        String result = transactionService.getAsString(null);

        assertEquals("", result);
    }

    @Test
    public void testGenericLie() {

        List<String> strings = new ArrayList<>();
        strings.add("");
        strings.add("hola");

        List<Integer> integers = new ArrayList<>();
        integers.add(22);
        integers.add(2656);

        List list = strings;
        list.add(Long.valueOf(19));

        list = integers;
        list.add("This is not a number");

        System.out.println(strings);
        System.out.println(integers);
    }

    @Test
    public void testSum() {

        Collection<Transaction> transactions = Arrays.asList(
                new Transaction(SOURCE, TARGET, new BigDecimal(10)),
                new Transaction(SOURCE, TARGET, new BigDecimal(44))
        );
        BigDecimal result = transactionService.sum(transactions);

        assertEquals(new BigDecimal(54), result);
    }

    private void setValue(Consumer<String> consumer) {
        consumer.accept("The value");
    }

    private static class A {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    private static class B {
        private String description;

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }
}