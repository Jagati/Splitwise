package com.lldproject.splitwise.service;

import com.lldproject.splitwise.exception.GroupNotFoundException;
import com.lldproject.splitwise.exception.UserNotFoundException;
import com.lldproject.splitwise.model.*;
import com.lldproject.splitwise.repository.ExpensePayerRepository;
import com.lldproject.splitwise.repository.ExpenseRepository;
import com.lldproject.splitwise.repository.GroupRepository;
import com.lldproject.splitwise.repository.UserRepository;
import com.lldproject.splitwise.strategy.ExpenseSplitStrategy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ExpenseService {
    UserRepository userRepository;
    ExpenseRepository expenseRepository;
    ExpensePayerRepository  expensePayerRepository;
    GroupRepository groupRepository;
    ExpenseSplitStrategy  expenseSplitStrategy;

    public ExpenseService(UserRepository userRepository, ExpenseRepository expenseRepository, ExpensePayerRepository expensePayerRepository, GroupRepository groupRepository, ExpenseSplitStrategy expenseSplitStrategy) {
        this.userRepository = userRepository;
        this.expenseRepository = expenseRepository;
        this.expensePayerRepository = expensePayerRepository;
        this.groupRepository = groupRepository;
        this.expenseSplitStrategy = expenseSplitStrategy;
    }

    public Expense addExpense(int user_id, int amount, Date paidOn, int group_id, ExpenseSplitType splitType, List<Integer> payer_ids) throws UserNotFoundException, GroupNotFoundException {
        //Get the user (who paid/ made the expense)
        Optional<User> userOp = userRepository.findById(user_id);
        if(userOp.isEmpty()){
            throw new UserNotFoundException("User not found");
        }
        User user = userOp.get();

        //Get the group for which the expense is being recorded
        Optional<Group>  groupOp = groupRepository.findById(group_id);
        if(groupOp.isEmpty()){
            throw new GroupNotFoundException("Group not found");
        }
        Group group = groupOp.get();

        //Get all users who have to pay
        List<ExpensePayer> expensePayers = new ArrayList<>();
        for(int payer_id:  payer_ids){
            Optional<ExpensePayer> payerOp = expensePayerRepository.findById(payer_id);
            if(payerOp.isEmpty()){
                throw new UserNotFoundException("Payer not found");
            }
            ExpensePayer payer = payerOp.get();
            expensePayers.add(payer);
        }
        Expense expense = new Expense();
        expense.setPaidBy(user);
        expense.setDate(paidOn);
        expense.setAmount(amount);
        expense.setGroup(group);
        expense.setSplitType(splitType);
        expense.setWhoHaveToPay(expensePayers);

        //Based on the expense split-type, call suitable strategy to update balances of users
        expenseSplitStrategy.splitExpense(expense);

        return expenseRepository.save(expense);
    }
}
