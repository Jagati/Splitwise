package com.lldproject.splitwise.strategy;

import com.lldproject.splitwise.model.Expense;
import org.springframework.stereotype.Component;

@Component
public class ExactSplitStrategy implements ExpenseSplitStrategy{
    @Override
    public void splitExpense(Expense expense){
        //Implement exact split strategy
        System.out.println("Exact value (no-split type) of expense");
    }
}
