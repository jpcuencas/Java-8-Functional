package com.everis.alicante.java8.service;

import com.everis.alicante.java8.model.Account;
import com.everis.alicante.java8.model.Client;
import com.everis.alicante.java8.model.PaymentMethod;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClientServiceImplTest {

    private ClientServiceImpl clientService = new ClientServiceImpl();

    @Test
    void getPaymentMethodName() {

        Client client = new Client(new Account(10, "Description", new PaymentMethod("Chachi")));
        String result = clientService.getPaymentMethodName(client);
        assertNull(result);
    }
}