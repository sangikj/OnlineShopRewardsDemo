package com.onlinedemoshop.demoshop.entity;



import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.math.BigDecimal;
import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "ORDER_TRANSACTION")
public class OrderTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String transactionId;
    private String customerId;
    private String productId;
    private int quantity;
    private BigDecimal totalAmount;
    private LocalDate transactionDate;
}