package com.onlinedemoshop.demoshop.service;

import com.onlinedemoshop.demoshop.entity.OrderTransaction;
import com.onlinedemoshop.demoshop.exception.CustomerDoesNotExistException;
import com.onlinedemoshop.demoshop.repository.OrderTransactionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.Month;
import java.util.Collections;
import java.util.List;
import java.util.Arrays;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class OrderTransactionRepositoryImplTest {
    @InjectMocks
    private OrderTransactionRepositoryImpl orderTransactionRepositoryImpl;

    @Mock
    private OrderTransactionRepository orderTransactionRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
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
@Test
public void testFetchOrderTransactionDateBetweenMatch() {
    LocalDate startDate = LocalDate.of(2025, Month.JULY, 1);
    LocalDate endDate = LocalDate.of(2025, Month.SEPTEMBER, 30);
    OrderTransaction transaction = new OrderTransaction();
    transaction.setTransactionDate(startDate.plusDays(1));
    transaction.setTotalAmount(new BigDecimal(120));
    when(orderTransactionRepositoryImpl.fetchOrderTransactionDateBetween(startDate, endDate)).thenReturn(Arrays.asList(transaction));

    List<OrderTransaction> transactions = orderTransactionRepositoryImpl.fetchOrderTransactionDateBetween(startDate, endDate);

    assertFalse(transactions.isEmpty());
    assertEquals(1, transactions.size());
    assertEquals(transaction, transactions.get(0));
}

@Test
public void testGetOrderTransactionByCustomerIdAndTransactionDateInvalidCustomerId() {
    LocalDate startDate = LocalDate.of(2025, Month.JULY, 1);
    LocalDate endDate = LocalDate.of(2025, Month.SEPTEMBER, 30);
    String customerId = "invalid";
    when(orderTransactionRepositoryImpl.getOrderTransactionByCustomerIdAndTransactionDate(customerId, startDate, endDate)).thenReturn(Collections.emptyList());

    List<OrderTransaction> transactions = orderTransactionRepositoryImpl.getOrderTransactionByCustomerIdAndTransactionDate(customerId, startDate, endDate);

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
public void testValidateCustomerIdExists() {
    String customerId = "1";
    when(orderTransactionRepository.existsByCustomerId(customerId)).thenReturn(true);

    assertDoesNotThrow(() -> orderTransactionRepositoryImpl.validateCustomerId(customerId));
}

@Test
public void testValidateCustomerIdDoesNotExist() {
    String customerId = "invalid";
    when(orderTransactionRepository.existsByCustomerId(customerId)).thenReturn(false);

    assertThrows(CustomerDoesNotExistException.class, () -> orderTransactionRepositoryImpl.validateCustomerId(customerId));
}
}
