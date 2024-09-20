package com.onlinedemoshop.demoshop.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@Builder
public class OrderTransaction {

    private String transactionId;
    private String customerId;
    private String productId;
    private int quantity;
    private BigDecimal totalAmount;
    private LocalDate transactionDate;

}
