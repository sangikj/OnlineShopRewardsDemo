package com.onlinedemoshop.demoshop.service;

import com.onlinedemoshop.demoshop.entity.OrderTransaction;
import com.onlinedemoshop.demoshop.exception.CustomerDoesNotExistException;
import com.onlinedemoshop.demoshop.repository.OrderTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class OrderTransactionRepositoryImpl {

    @Autowired
    private OrderTransactionRepository  orderTransactionRepository;

    public List<OrderTransaction> fetchOrderTransactionDateBetween(LocalDate startDate, LocalDate endDate)
    {
        return orderTransactionRepository.findByTransactionDateBetween(startDate, endDate);
    }

    public List<OrderTransaction> getOrderTransactionByCustomerIdAndTransactionDate(String customerId, LocalDate startDate, LocalDate endDate){
        return orderTransactionRepository.findByCustomerIdAndTransactionDateBetween(customerId, startDate, endDate);
    }

    public void validateCustomerId(String customerId) {
        if (!orderTransactionRepository.existsByCustomerId(customerId)) {
            throw new CustomerDoesNotExistException("Customer Id does not exist");
        }
    }

}
