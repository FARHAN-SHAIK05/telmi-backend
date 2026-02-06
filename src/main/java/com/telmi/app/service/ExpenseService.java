 package com.telmi.app.service;

import com.telmi.app.dto.expense.CreateExpenseRequest;
import com.telmi.app.entity.*;
import com.telmi.app.repository.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final ExpenseSplitRepository splitRepository;
    private final GroupRepository groupRepository;
    private final UserRepository userRepository;
    private final UserService userService;

    public ExpenseService(
            ExpenseRepository expenseRepository,
            ExpenseSplitRepository splitRepository,
            GroupRepository groupRepository,
            UserRepository userRepository,
            UserService userService) {

        this.expenseRepository = expenseRepository;
        this.splitRepository = splitRepository;
        this.groupRepository = groupRepository;
        this.userRepository = userRepository;
        this.userService = userService;
    }

    public Expense createExpense(CreateExpenseRequest request) {

        User payer = userService.getCurrentUser();

        Group group = groupRepository.findById(request.groupId)
                .orElseThrow();

        Expense expense = new Expense();
        expense.setDescription(request.description);
        expense.setAmount(request.amount);
        expense.setGroup(group);
        expense.setPaidBy(payer);
        expense.setCreatedAt(LocalDateTime.now());

        Expense saved = expenseRepository.save(expense);

        Double splitAmount =
                request.amount / request.userIds.size();

        List<ExpenseSplit> splits = new ArrayList<>();

        for (Long userId : request.userIds) {
            User user = userRepository.findById(userId).orElseThrow();

            ExpenseSplit split = new ExpenseSplit();
            split.setExpense(saved);
            split.setUser(user);
            split.setAmount(splitAmount);

            splits.add(split);
        }

        splitRepository.saveAll(splits);
        saved.setSplits(splits);

        return saved;
    }
}
