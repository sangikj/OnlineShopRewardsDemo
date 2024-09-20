package com.onlinedemoshop.demoshop.service;

import com.onlinedemoshop.demoshop.exception.InvalidMonthRangeException;
import com.onlinedemoshop.demoshop.util.TransactionData;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;
import static com.onlinedemoshop.demoshop.constant.CommonConstants.ENDDATE_INVALID;
import static com.onlinedemoshop.demoshop.constant.CommonConstants.INVALID_MONTH_RANGE_MESSAGE;

@Slf4j
@Service
public class CalculateRewards {

    @Autowired
    private TransactionData transactionData;


    public Map<String, Map<String, Integer>> calculateRewardsBasedOnDate(LocalDate startDate, LocalDate endDate) {
        Map<String, Map<String, Integer>> rewards = new HashMap<>();

        if (endDate.isBefore(startDate)) {
            log.error("End date %s is before start date %s", endDate, startDate);
            throw new InvalidMonthRangeException(ENDDATE_INVALID);
        }


        if (ChronoUnit.MONTHS.between(startDate, endDate) > 3) {
            log.error(INVALID_MONTH_RANGE_MESSAGE);
            throw new InvalidMonthRangeException(INVALID_MONTH_RANGE_MESSAGE);
        }

          transactionData.getTransactionData().stream()
                    .filter(transaction -> transaction.getTransactionDate().isAfter(startDate) && transaction.getTransactionDate().isBefore(endDate))
                    .forEach(transaction -> {
                        if (!rewards.containsKey(transaction.getCustomerId())) {
                            rewards.put(transaction.getCustomerId(), new HashMap<>());
                        }
                        Map<String, Integer> monthlyRewards = rewards.get(transaction.getCustomerId());
                        String month = transaction.getTransactionDate().getMonth().toString();
                        if (!monthlyRewards.containsKey(month)) {
                            monthlyRewards.put(month, 0);
                        }
                        int points = calculatePoints(transaction.getTotalAmount());
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
    private int calculatePoints(BigDecimal amount) {
        int points = 0;

        if (amount.compareTo(BigDecimal.valueOf(50)) >= 0) {
            points = BigDecimal.valueOf(50).intValue();

        }
        if (amount.compareTo(BigDecimal.valueOf(100)) >= 0) {
            points += amount.subtract(BigDecimal.valueOf(100)).intValue() * 2;

        }
        log.info("Points are calculated successfully :  {} for the amount {}", points, amount);
        return points;

    }

}
