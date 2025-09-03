package com.lldproject.splitwise.strategy;

import com.lldproject.splitwise.model.Expense;
import com.lldproject.splitwise.model.Transaction;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface SettleUpStrategy {
    public List<Transaction> settleUp(List<Expense> expenses);
}
