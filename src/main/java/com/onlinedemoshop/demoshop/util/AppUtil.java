package com.onlinedemoshop.demoshop.util;

import com.onlinedemoshop.demoshop.exception.InvalidMonthRangeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import java.time.temporal.ChronoUnit;
import java.math.BigDecimal;
import java.time.LocalDate;
import static com.onlinedemoshop.demoshop.constant.CommonConstants.ENDDATE_INVALID;
import static com.onlinedemoshop.demoshop.constant.CommonConstants.INVALID_MONTH_RANGE_MESSAGE;

@Slf4j
@Component
public class AppUtil {

    public int calculatePoints(BigDecimal amount) {
        int points = 0;
        //more than 50 and less than 100
        if (amount.compareTo(BigDecimal.valueOf(50)) >= 0 && amount.compareTo(BigDecimal.valueOf(100)) < 0) {
            points = amount.subtract(BigDecimal.valueOf(50)).intValue();

        }

        //more than 100
        if (amount.compareTo(BigDecimal.valueOf(100)) >= 0) {
            points = amount.subtract(BigDecimal.valueOf(100)).intValue() * 2 + 50;

        }
        log.info("Points are calculated successfully :  {} for the amount {}", points, amount);
        return points;

    }

    public void validateDateRange(LocalDate startDate, LocalDate endDate) {
        if (endDate.isBefore(startDate)) {
            log.error("End date {} is before start date {}", endDate, startDate);
            throw new InvalidMonthRangeException(ENDDATE_INVALID);
        }
    }

    public void validateMonthRange(LocalDate startDate, LocalDate endDate) {
    if (ChronoUnit.MONTHS.between(startDate, endDate) > 3) {
        log.error(INVALID_MONTH_RANGE_MESSAGE);
        throw new InvalidMonthRangeException(INVALID_MONTH_RANGE_MESSAGE);
    }
}


}
