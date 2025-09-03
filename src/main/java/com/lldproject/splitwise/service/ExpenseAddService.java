package com.lldproject.splitwise.service;

import com.lldproject.splitwise.model.Expense;
import com.lldproject.splitwise.model.ExpenseSplitType;
import com.lldproject.splitwise.model.Group;
import com.lldproject.splitwise.model.User;
import com.lldproject.splitwise.repository.ExpenseRepository;
import com.lldproject.splitwise.repository.GroupRepository;
import com.lldproject.splitwise.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ExpenseAddService {
    private ExpenseRepository expenseRepository;
    private GroupRepository groupRepository;
    private UserRepository userRepository;
    @Autowired
    public ExpenseAddService(ExpenseRepository expenseRepository, GroupRepository groupRepository, UserRepository userRepository) {
        this.expenseRepository = expenseRepository;
        this.groupRepository = groupRepository;
        this.userRepository = userRepository;
    }

    public void addExpense(ExpenseSplitType expenseSplitType, double amount, Group group, User user, List<User> whoHaveToPay) {

    }
}
