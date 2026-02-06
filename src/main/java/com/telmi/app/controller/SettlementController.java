 package com.telmi.app.controller;

import com.telmi.app.dto.settlement.SettleRequest;
import com.telmi.app.entity.Settlement;
import com.telmi.app.service.SettlementService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/settlements")
public class SettlementController {

    private final SettlementService settlementService;

    public SettlementController(SettlementService settlementService) {
        this.settlementService = settlementService;
    }

    @PostMapping
    public Settlement settle(@RequestBody SettleRequest request) {
        return settlementService.settle(request);
    }
}
