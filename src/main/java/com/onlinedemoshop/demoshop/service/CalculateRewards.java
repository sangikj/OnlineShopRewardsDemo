package com.onlinedemoshop.demoshop.service;


import com.onlinedemoshop.demoshop.exception.DataDoesNotExistException;
import com.onlinedemoshop.demoshop.util.AppUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;


@Slf4j
@Service
public class CalculateRewards {


    @Autowired
    private OrderTransactionRepositoryImpl orderTransactionRepositoryImpl;

    @Autowired
    private AppUtil appUtil;

    public Map<String, Map<String, Integer>> calculateRewardsBasedOnDate(LocalDate startDate, LocalDate endDate) {
        appUtil.validateDateRange(startDate, endDate);
        appUtil.validateMonthRange(startDate, endDate);
        Map<String, Map<String, Integer>> rewards = new HashMap<>();
        orderTransactionRepositoryImpl.fetchOrderTransactionDateBetween(startDate, endDate).stream()
                    .forEach(transaction -> {
                        if (!rewards.containsKey(transaction.getCustomerId())) {
                            rewards.put(transaction.getCustomerId(), new HashMap<>());
                        }
                        Map<String, Integer> monthlyRewards = rewards.get(transaction.getCustomerId());
                        String month = transaction.getTransactionDate().getMonth().toString();
                        if (!monthlyRewards.containsKey(month)) {
                            monthlyRewards.put(month, 0);
                        }
                        int points = appUtil.calculatePoints(transaction.getTotalAmount());
                        monthlyRewards.put(month, monthlyRewards.get(month) + points);
                    });

        // Calculate total rewards
        rewards.forEach((customerId, monthlyRewards) -> {
            int totalPoints = monthlyRewards.values().stream()
                    .mapToInt(Integer::intValue)
                    .sum();
            monthlyRewards.put("total", totalPoints);
        });

        return rewards;
    }

    public Map<String, Map<String, Integer>> calculateRewardsBasedOnCustomer(String customerId, LocalDate startDate, LocalDate endDate) {
        appUtil.validateDateRange(startDate, endDate);
        appUtil.validateMonthRange(startDate, endDate);
        orderTransactionRepositoryImpl.validateCustomerId(customerId);
        Map<String, Map<String, Integer>> rewards = new HashMap<>();
        orderTransactionRepositoryImpl.getOrderTransactionByCustomerIdAndTransactionDate(customerId, startDate,endDate).stream()
                .forEach(transaction -> {
                    if (!rewards.containsKey(transaction.getCustomerId())) {
                        rewards.put(transaction.getCustomerId(), new HashMap<>());
                    }
                    Map<String, Integer> monthlyRewards = rewards.get(transaction.getCustomerId());
                    String month = transaction.getTransactionDate().getMonth().toString();
                    if (!monthlyRewards.containsKey(month)) {
                        monthlyRewards.put(month, 0);
                    }
                    int points = appUtil.calculatePoints(transaction.getTotalAmount());
                    monthlyRewards.put(month, monthlyRewards.get(month) + points);
                });
        rewards.forEach((customer_Id, monthlyRewards) -> {
            int totalPoints = monthlyRewards.values().stream()
                    .mapToInt(Integer::intValue)
                    .sum();
            monthlyRewards.put("total", totalPoints);
        });
        if(rewards.isEmpty())
            throw new DataDoesNotExistException("No transactions found for the customer");




        return rewards;
    }



}
