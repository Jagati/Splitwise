package com.lldproject.splitwise.strategy;

import com.lldproject.splitwise.model.Expense;
import org.springframework.stereotype.Component;

@Component
public interface ExpenseSplitStrategy {
    void splitExpense(Expense expense);
}
