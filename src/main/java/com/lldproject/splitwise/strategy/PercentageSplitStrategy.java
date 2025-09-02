package com.lldproject.splitwise.strategy;

import com.lldproject.splitwise.model.Expense;
import org.springframework.stereotype.Component;

@Component
public class PercentageSplitStrategy implements ExpenseSplitStrategy {
    @Override
    public void splitExpense(Expense expense) {
        //Implement percentage splitting strategy
        System.out.println("Percentage-based splitting expense");
    }
}
