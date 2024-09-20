package com.onlinedemoshop.demoshop.controller;


import com.onlinedemoshop.demoshop.service.CalculateRewards;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.time.Month;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class RewardControllerTest {

    @InjectMocks
    private RewardController rewardController;

    @Mock
    private CalculateRewards calculateRewards;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testPublishRewards() {

        LocalDate startDate = LocalDate.of(2024, Month.JULY, 1);
        LocalDate endDate = LocalDate.of(2024, Month.SEPTEMBER, 30);
        Map<String, Map<String, Integer>> rewardsMap = new HashMap<>();
        when(calculateRewards.calculateRewardsBasedOnDate(startDate, endDate)).thenReturn(rewardsMap);


        ResponseEntity<?> responseEntity = rewardController.publishRewards(startDate, endDate);


        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(rewardsMap, responseEntity.getBody());
    }
}
