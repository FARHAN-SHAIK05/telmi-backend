 package com.telmi.app.service;

import com.telmi.app.entity.ExpenseSplit;
import com.telmi.app.repository.ExpenseSplitRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BalanceService {

    private final ExpenseSplitRepository splitRepository;

    public BalanceService(ExpenseSplitRepository splitRepository) {
        this.splitRepository = splitRepository;
    }

    public Map<Long, Double> calculateBalances() {

        List<ExpenseSplit> splits = splitRepository.findAll();
        Map<Long, Double> balances = new HashMap<>();

        for (ExpenseSplit split : splits) {

            Long userId = split.getUser().getId();
            Double amount = split.getAmount();

            balances.put(userId,
                    balances.getOrDefault(userId, 0.0) - amount);

            Long payerId = split.getExpense()
                    .getPaidBy().getId();

            balances.put(payerId,
                    balances.getOrDefault(payerId, 0.0) + amount);
        }

        return balances;
    }
}
