package com.onlinedemoshop.demoshop.util;

import com.onlinedemoshop.demoshop.exception.InvalidMonthRangeException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.time.Month;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class AppUtilTest {

    private AppUtil appUtil;

    @BeforeEach
    public void setup() {
        appUtil = new AppUtil();
    }

    @Test
    public void testValidateDateRange() {
        LocalDate startDate = LocalDate.of(2025, Month.JULY, 1);
        LocalDate endDate = LocalDate.of(2025, Month.JUNE, 30);

        assertThrows(InvalidMonthRangeException.class, () -> {
            appUtil.validateDateRange(startDate, endDate);
        });
    }


@Test
public void testCalculatePointsLessThanFifty() {
    BigDecimal amount = BigDecimal.valueOf(49);
    int points = appUtil.calculatePoints(amount);
    assertEquals(0, points);
}

@Test
public void testCalculatePointsMoreThanFiftyLessThanHundred() {
    BigDecimal amount = BigDecimal.valueOf(75);
    int points = appUtil.calculatePoints(amount);
    assertEquals(25, points);
}

@Test
public void testCalculatePointsMoreThanHundred() {
    BigDecimal amount = BigDecimal.valueOf(120);
    int points = appUtil.calculatePoints(amount);
    assertEquals(90, points);
}

@Test
public void testValidateDateRangeValid() {
    LocalDate startDate = LocalDate.of(2025, Month.JULY, 1);
    LocalDate endDate = LocalDate.of(2025, Month.JULY, 31);
    assertDoesNotThrow(() -> appUtil.validateDateRange(startDate, endDate));
}

@Test
public void testValidateMonthRangeValid() {
    LocalDate startDate = LocalDate.of(2025, Month.JULY, 1);
    LocalDate endDate = LocalDate.of(2025, Month.SEPTEMBER, 30);
    assertDoesNotThrow(() -> appUtil.validateMonthRange(startDate, endDate));
}

@Test
public void testValidateMonthRangeInvalid() {
    LocalDate startDate = LocalDate.of(2025, Month.JULY, 1);
    LocalDate endDate = LocalDate.of(2025, Month.DECEMBER, 31);
    assertThrows(InvalidMonthRangeException.class, () -> appUtil.validateMonthRange(startDate, endDate));
}

@Test
public void testCalculatePointsExactlyFifty() {
    BigDecimal amount = BigDecimal.valueOf(50);
    int points = appUtil.calculatePoints(amount);
    assertEquals(0, points);
}

@Test
public void testCalculatePointsExactlyHundred() {
    BigDecimal amount = BigDecimal.valueOf(100);
    int points = appUtil.calculatePoints(amount);
    assertEquals(50, points);
}

@Test
public void testValidateDateRangeSameDate() {
    LocalDate startDate = LocalDate.of(2025, Month.JULY, 1);
    LocalDate endDate = LocalDate.of(2025, Month.JULY, 1);
    assertDoesNotThrow(() -> appUtil.validateDateRange(startDate, endDate));
}

@Test
public void testValidateMonthRangeSameMonth() {
    LocalDate startDate = LocalDate.of(2025, Month.JULY, 1);
    LocalDate endDate = LocalDate.of(2025, Month.JULY, 31);
    assertDoesNotThrow(() -> appUtil.validateMonthRange(startDate, endDate));
}

@Test
public void testValidateMonthRangeExactlyThreeMonths() {
    LocalDate startDate = LocalDate.of(2025, Month.JULY, 1);
    LocalDate endDate = LocalDate.of(2025, Month.OCTOBER, 1);
    assertDoesNotThrow(() -> appUtil.validateMonthRange(startDate, endDate));
}
}