package com.lldproject.splitwise.strategy;

import com.lldproject.splitwise.model.Expense;
import com.lldproject.splitwise.model.Transaction;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SettleUpStrategy1 implements SettleUpStrategy {
    @Override
    public List<Transaction> settleUp(List<Expense> expenses){
        System.out.println("Implementing Strategy 1 for settling up group");
        return null;
    }
}
