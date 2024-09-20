package com.onlinedemoshop.demoshop.util;

import com.onlinedemoshop.demoshop.model.OrderTransaction;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

@Component
public class TransactionData {

    public List<OrderTransaction> getTransactionData() {
        List<OrderTransaction> transactions = new ArrayList<>();
        transactions.add(new OrderTransaction("1", "1", "1", 1, BigDecimal.valueOf(120.0), LocalDate.of(2024, Month.SEPTEMBER, 10)));
        transactions.add(new OrderTransaction("2", "2", "2", 2, BigDecimal.valueOf(200.0), LocalDate.of(2024, Month.AUGUST, 20)));
        transactions.add(new OrderTransaction("3", "3", "3", 3, BigDecimal.valueOf(300.0), LocalDate.of(2024, Month.AUGUST, 24)));
        transactions.add(new OrderTransaction("4", "4", "4", 4, BigDecimal.valueOf(400.0), LocalDate.of(2024, Month.JULY, 14)));
        transactions.add(new OrderTransaction("5", "5", "5", 5, BigDecimal.valueOf(500.0), LocalDate.of(2024, Month.JULY, 19)));
        transactions.add(new OrderTransaction("6", "5", "6", 6, BigDecimal.valueOf(600.0), LocalDate.of(2024, Month.JULY, 11)));
        transactions.add(new OrderTransaction("7", "7", "7", 7, BigDecimal.valueOf(700.0), LocalDate.of(2024, Month.SEPTEMBER, 2)));
        transactions.add(new OrderTransaction("8", "8", "8", 8, BigDecimal.valueOf(800.0), LocalDate.of(2024, Month.SEPTEMBER, 3)));
        transactions.add(new OrderTransaction("9", "9", "9", 9, BigDecimal.valueOf(900.0), LocalDate.of(2024, Month.SEPTEMBER, 24)));
        transactions.add(new OrderTransaction("10", "2", "10", 10, BigDecimal.valueOf(1000.0), LocalDate.of(2024, Month.AUGUST, 17)));
        transactions.add(new OrderTransaction("11", "11", "11", 11, BigDecimal.valueOf(1100.0), LocalDate.of(2024, Month.AUGUST, 18)));
        transactions.add(new OrderTransaction("12", "3", "12", 12, BigDecimal.valueOf(1200.0), LocalDate.of(2024, Month.AUGUST, 19)));
        transactions.add(new OrderTransaction("13", "12", "12", 12, BigDecimal.valueOf(1201.0), LocalDate.of(2024, Month.AUGUST, 19)));
        transactions.add(new OrderTransaction("14", "12", "12", 12, BigDecimal.valueOf(1202.0), LocalDate.of(2024, Month.AUGUST, 19)));
        transactions.add(new OrderTransaction("15", "12", "12", 12, BigDecimal.valueOf(1203.0), LocalDate.of(2024, Month.AUGUST, 19)));
        transactions.add(new OrderTransaction("16", "9", "12", 12, BigDecimal.valueOf(1204.0), LocalDate.of(2024, Month.AUGUST, 19)));
        transactions.add(new OrderTransaction("17", "10", "12", 12, BigDecimal.valueOf(1205.0), LocalDate.of(2024, Month.AUGUST, 19)));
        transactions.add(new OrderTransaction("17", "10", "12", 12, BigDecimal.valueOf(1206.0), LocalDate.of(2024, Month.AUGUST, 19)));
        transactions.add(new OrderTransaction("17", "10", "12", 12, BigDecimal.valueOf(1207.0), LocalDate.of(2024, Month.JULY, 19)));
        transactions.add(new OrderTransaction("17", "9", "12", 12, BigDecimal.valueOf(1208.0), LocalDate.of(2024, Month.AUGUST, 19)));




        return transactions;
    }


}
