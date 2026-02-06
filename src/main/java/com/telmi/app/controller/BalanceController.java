 package com.telmi.app.controller;

import com.telmi.app.service.BalanceService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/balances")
public class BalanceController {

    private final BalanceService balanceService;

    public BalanceController(BalanceService balanceService) {
        this.balanceService = balanceService;
    }

    @GetMapping
    public Map<Long, Double> getBalances() {
        return balanceService.calculateBalances();
    }
}
