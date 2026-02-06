 package com.telmi.app.service;

import com.telmi.app.dto.settlement.SettleRequest;
import com.telmi.app.entity.Settlement;
import com.telmi.app.entity.User;
import com.telmi.app.repository.SettlementRepository;
import com.telmi.app.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class SettlementService {

    private final SettlementRepository settlementRepository;
    private final UserRepository userRepository;

    public SettlementService(SettlementRepository settlementRepository,
                             UserRepository userRepository) {
        this.settlementRepository = settlementRepository;
        this.userRepository = userRepository;
    }

    public Settlement settle(SettleRequest request) {

        User from = userRepository.findById(request.fromUserId)
                .orElseThrow();

        User to = userRepository.findById(request.toUserId)
                .orElseThrow();

        Settlement settlement = new Settlement();
        settlement.setFromUser(from);
        settlement.setToUser(to);
        settlement.setAmount(request.amount);
        settlement.setCreatedAt(LocalDateTime.now());

        return settlementRepository.save(settlement);
    }
}
