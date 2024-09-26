package com.onlinedemoshop.demoshop.repository;

import com.onlinedemoshop.demoshop.entity.OrderTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
public interface OrderTransactionRepository extends JpaRepository<OrderTransaction,String > {

    List<OrderTransaction> findByCustomerIdAndTransactionDateBetween(String customerId, LocalDate startDate, LocalDate endDate);

    boolean existsByCustomerId(String customerId);
    List<OrderTransaction> findByTransactionDateBetween(LocalDate startDate, LocalDate endDate);
}
