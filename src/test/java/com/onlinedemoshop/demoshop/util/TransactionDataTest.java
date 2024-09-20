package com.onlinedemoshop.demoshop.util;


import com.onlinedemoshop.demoshop.model.OrderTransaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransactionDataTest {

    private TransactionData transactionData;

    @BeforeEach
    public void setup() {
        transactionData = new TransactionData();
    }

    @Test
    public void testGetTransactionData() {

        List<OrderTransaction> transactions = transactionData.getTransactionData();


        assertEquals(20, transactions.size());
        OrderTransaction firstTransaction = transactions.get(0);
        assertEquals("1", firstTransaction.getTransactionId());
        assertEquals("1", firstTransaction.getCustomerId());
        assertEquals("1", firstTransaction.getProductId());
        assertEquals(1, firstTransaction.getQuantity());
        assertEquals(BigDecimal.valueOf(100.0), firstTransaction.getTotalAmount());
        assertEquals(LocalDate.of(2024, Month.SEPTEMBER, 10), firstTransaction.getTransactionDate());
    }
}
