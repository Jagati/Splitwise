package com.lldproject.splitwise.strategy;

import com.lldproject.splitwise.model.Expense;
import org.springframework.stereotype.Component;

@Component
public class EqualSplitStrategy implements ExpenseSplitStrategy {
    @Override
    public void splitExpense(Expense expense) {
            //Implement equal splitting strategy
        System.out.println("Equally splitting expense");
    }
}
