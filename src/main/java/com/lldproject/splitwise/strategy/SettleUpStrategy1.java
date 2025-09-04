package com.lldproject.splitwise.strategy;

import com.lldproject.splitwise.model.Expense;
import com.lldproject.splitwise.model.Transaction;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

@Component
public class SettleUpStrategy1 implements SettleUpStrategy {
    //Max heap
    PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(Collections.reverseOrder());
    @Override
    public List<Transaction> settleUp(List<Expense> expenses){
        System.out.println("Implementing Strategy 1 for settling up group");
        return null;
    }
}
