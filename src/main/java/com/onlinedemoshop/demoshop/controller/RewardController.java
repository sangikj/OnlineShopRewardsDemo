package com.onlinedemoshop.demoshop.controller;

import com.onlinedemoshop.demoshop.service.CalculateRewards;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/onlineshop")
public class RewardController {

    @Autowired
    private CalculateRewards calculateRewards;

    @GetMapping("/rewards")
    public ResponseEntity<?> publishRewards(
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate)
    {
        log.info("Request has been received to calculate rewards for the date range : {} to {}", startDate, endDate);
        Map<String, Map<String, Integer>> rewards = calculateRewards.calculateRewardsBasedOnDate(startDate, endDate);
        log.info("Rewards are calculated successfully : {}", rewards);
        return new ResponseEntity<>(rewards, HttpStatus.OK);
    }
}
