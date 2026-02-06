 package com.telmi.app.dto.expense;

import java.util.List;

public class CreateExpenseRequest {

    public Long groupId;
    public String description;
    public Double amount;
    public List<Long> userIds;
}
