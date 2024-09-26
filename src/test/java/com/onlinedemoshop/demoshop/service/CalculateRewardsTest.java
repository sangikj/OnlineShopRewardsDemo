package com.onlinedemoshop.demoshop.service;


import com.onlinedemoshop.demoshop.entity.OrderTransaction;
import com.onlinedemoshop.demoshop.exception.DataDoesNotExistException;
import com.onlinedemoshop.demoshop.util.AppUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class CalculateRewardsTest {

    @InjectMocks
    private CalculateRewards calculateRewards;

    @Mock
    private OrderTransactionRepositoryImpl orderTransactionRepositoryImpl;

    @Mock
    private AppUtil appUtil;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCalculateRewardsBasedOnDate() {

    LocalDate startDate = LocalDate.of(2025, Month.JULY, 1);
    LocalDate endDate = LocalDate.of(2025, Month.SEPTEMBER, 30);
    when(orderTransactionRepositoryImpl.fetchOrderTransactionDateBetween(startDate, endDate)).thenReturn(Arrays.asList(new OrderTransaction() {{
        setTransactionDate(startDate.plusDays(1));
        setTotalAmount(new BigDecimal(120));
    }}));
    when(appUtil.calculatePoints(new BigDecimal(120))).thenReturn(90);
    assertDoesNotThrow(() -> appUtil.validateDateRange(startDate, endDate));
    assertDoesNotThrow(() -> appUtil.validateMonthRange(startDate, endDate));

    Map<String, Map<String, Integer>> rewards = calculateRewards.calculateRewardsBasedOnDate(startDate, endDate);
    System.out.println(rewards);
    assertNotNull(rewards);

    }

    @Test
    public void testCalculateRewardsBasedOnCustomer() {
        LocalDate startDate = LocalDate.of(2024, Month.JULY, 1);
        LocalDate endDate = LocalDate.of(2024, Month.SEPTEMBER, 30);
        String customerId = "1";
        when(orderTransactionRepositoryImpl.getOrderTransactionByCustomerIdAndTransactionDate(customerId, startDate, endDate)).thenReturn(Arrays.asList(new OrderTransaction() {{
            setTransactionDate(startDate.plusDays(1));
            setTotalAmount(new BigDecimal(120));
        }}));
        when(appUtil.calculatePoints(new BigDecimal(120))).thenReturn(90);
        assertDoesNotThrow(() -> appUtil.validateDateRange(startDate, endDate));
        assertDoesNotThrow(() -> appUtil.validateMonthRange(startDate, endDate));

        Map<String, Map<String, Integer>> rewards = calculateRewards.calculateRewardsBasedOnCustomer(customerId, startDate, endDate);
        System.out.println(rewards);
        assertNotNull(rewards);
    }


    @Test
    public void testCalculateRewardsBasedOnDateEmptyList() {
        LocalDate startDate = LocalDate.of(2025, Month.JULY, 1);
        LocalDate endDate = LocalDate.of(2025, Month.SEPTEMBER, 30);
        when(orderTransactionRepositoryImpl.fetchOrderTransactionDateBetween(startDate, endDate)).thenReturn(Collections.emptyList());

        Map<String, Map<String, Integer>> rewards = calculateRewards.calculateRewardsBasedOnDate(startDate, endDate);

        assertTrue(rewards.isEmpty());
    }

    @Test
    public void testCalculateRewardsBasedOnCustomerEmptyList() {
        LocalDate startDate = LocalDate.of(2025, Month.JULY, 1);
        LocalDate endDate = LocalDate.of(2025, Month.SEPTEMBER, 30);
        String customerId = "1";
        when(orderTransactionRepositoryImpl.getOrderTransactionByCustomerIdAndTransactionDate(customerId, startDate, endDate)).thenReturn(Collections.emptyList());

        assertThrows(DataDoesNotExistException.class, () -> calculateRewards.calculateRewardsBasedOnCustomer(customerId, startDate, endDate));
    }

    @Test
    public void testCalculateRewardsBasedOnCustomerNonEmptyList() {
        LocalDate startDate = LocalDate.of(2025, Month.JULY, 1);
        LocalDate endDate = LocalDate.of(2025, Month.SEPTEMBER, 30);
        String customerId = "1";
        OrderTransaction transaction = new OrderTransaction();
        transaction.setCustomerId(customerId);
        transaction.setTransactionDate(startDate.plusDays(1));
        transaction.setTotalAmount(new BigDecimal(120));
        when(orderTransactionRepositoryImpl.getOrderTransactionByCustomerIdAndTransactionDate(customerId, startDate, endDate)).thenReturn(Arrays.asList(transaction));
        when(appUtil.calculatePoints(transaction.getTotalAmount())).thenReturn(90);

        Map<String, Map<String, Integer>> rewards = calculateRewards.calculateRewardsBasedOnCustomer(customerId, startDate, endDate);

        assertFalse(rewards.isEmpty());
        assertEquals(90, rewards.get(customerId).get(startDate.plusDays(1).getMonth().toString()).intValue());
        assertEquals(90, rewards.get(customerId).get("total").intValue());
    }



@Test
public void testFetchOrderTransactionDateBetweenNoMatch() {
    LocalDate startDate = LocalDate.of(2025, Month.JULY, 1);
    LocalDate endDate = LocalDate.of(2025, Month.SEPTEMBER, 30);
    when(orderTransactionRepositoryImpl.fetchOrderTransactionDateBetween(startDate, endDate)).thenReturn(Collections.emptyList());

    List<OrderTransaction> transactions = orderTransactionRepositoryImpl.fetchOrderTransactionDateBetween(startDate, endDate);

    assertTrue(transactions.isEmpty());
}

@Test
public void testGetOrderTransactionByCustomerIdAndTransactionDateNoMatch() {
    LocalDate startDate = LocalDate.of(2025, Month.JULY, 1);
    LocalDate endDate = LocalDate.of(2025, Month.SEPTEMBER, 30);
    String customerId = "1";
    when(orderTransactionRepositoryImpl.getOrderTransactionByCustomerIdAndTransactionDate(customerId, startDate, endDate)).thenReturn(Collections.emptyList());

    List<OrderTransaction> transactions = orderTransactionRepositoryImpl.getOrderTransactionByCustomerIdAndTransactionDate(customerId, startDate, endDate);

    assertTrue(transactions.isEmpty());
}

@Test
public void testGetOrderTransactionByCustomerIdAndTransactionDateMatch() {
    LocalDate startDate = LocalDate.of(2025, Month.JULY, 1);
    LocalDate endDate = LocalDate.of(2025, Month.SEPTEMBER, 30);
    String customerId = "1";
    OrderTransaction transaction = new OrderTransaction();
    transaction.setCustomerId(customerId);
    transaction.setTransactionDate(startDate.plusDays(1));
    transaction.setTotalAmount(new BigDecimal(120));
    when(orderTransactionRepositoryImpl.getOrderTransactionByCustomerIdAndTransactionDate(customerId, startDate, endDate)).thenReturn(Arrays.asList(transaction));

    List<OrderTransaction> transactions = orderTransactionRepositoryImpl.getOrderTransactionByCustomerIdAndTransactionDate(customerId, startDate, endDate);

    assertFalse(transactions.isEmpty());
    assertEquals(1, transactions.size());
    assertEquals(transaction, transactions.get(0));
}
}