 package com.telmi.app.controller;

import com.telmi.app.dto.expense.CreateExpenseRequest;
import com.telmi.app.entity.Expense;
import com.telmi.app.service.ExpenseService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/expenses")
public class ExpenseController {

    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @PostMapping
    public Expense createExpense(@RequestBody CreateExpenseRequest request) {
        return expenseService.createExpense(request);
    }
}
