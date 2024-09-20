package com.onlinedemoshop.demoshop.service;

import com.onlinedemoshop.demoshop.model.OrderTransaction;
import com.onlinedemoshop.demoshop.util.TransactionData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class CalculateRewardsTest {

    @InjectMocks
    private CalculateRewards calculateRewards;

    @Mock
    private TransactionData transactionData;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCalculateRewardsBasedOnDate() {

        LocalDate startDate = LocalDate.of(2024, Month.JULY, 1);
        LocalDate endDate = LocalDate.of(2024, Month.SEPTEMBER, 30);
        OrderTransaction transaction1 = OrderTransaction.builder()
            .transactionId("1")
            .customerId("1")
            .productId("1")
            .quantity(1)
            .totalAmount(BigDecimal.valueOf(100.0))
            .transactionDate(LocalDate.of(2024, Month.AUGUST, 10))
            .build();
        OrderTransaction transaction2 = OrderTransaction.builder()
            .transactionId("2")
            .customerId("1")
            .productId("2")
            .quantity(2)
            .totalAmount(BigDecimal.valueOf(200.0))
            .transactionDate(LocalDate.of(2024, Month.SEPTEMBER, 10))
            .build();
        when(transactionData.getTransactionData()).thenReturn(Arrays.asList(transaction1, transaction2));


        Map<String, Map<String, Integer>> rewards = calculateRewards.calculateRewardsBasedOnDate(startDate, endDate);


        assertEquals(3, rewards.get("1").size());
        assertEquals(50, rewards.get("1").get("AUGUST"));
        assertEquals(350, rewards.get("1").get("SEPTEMBER"));
    }
}
