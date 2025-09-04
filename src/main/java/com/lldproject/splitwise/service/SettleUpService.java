package com.lldproject.splitwise.service;

import com.lldproject.splitwise.exception.GroupNotFoundException;
import com.lldproject.splitwise.exception.UserNotFoundException;
import com.lldproject.splitwise.model.Expense;
import com.lldproject.splitwise.model.Group;
import com.lldproject.splitwise.model.User;
import com.lldproject.splitwise.model.Transaction;
import com.lldproject.splitwise.repository.ExpenseRepository;
import com.lldproject.splitwise.repository.GroupRepository;
import com.lldproject.splitwise.repository.UserRepository;
import com.lldproject.splitwise.strategy.SettleUpStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SettleUpService {
    private final GroupRepository groupRepository;
    private final SettleUpStrategy settleUpStrategy;
    private final UserRepository userRepository;
    private final ExpenseRepository expenseRepository;

    @Autowired
    public SettleUpService(GroupRepository groupRepository, SettleUpStrategy settleUpStrategy, UserRepository userRepository, ExpenseRepository expenseRepository) {
        this.groupRepository = groupRepository;
        this.settleUpStrategy = settleUpStrategy;
        this.userRepository = userRepository;
        this.expenseRepository = expenseRepository;
    }

    public List<Transaction> settleUpGroup(int groupId) throws GroupNotFoundException {
        Optional<Group> groupOp = groupRepository.findById(groupId);
        if(groupOp.isEmpty()){
            throw new GroupNotFoundException("Group not found");
        }
        Group group = groupOp.get();
        //Extract all expenses from the group
        List<Expense> expenses = group.getExpenses();
        //Settle up based on strategy
        return settleUpStrategy.settleUp(expenses);
    }

    public List<Transaction> settleUpUser(int userId) throws UserNotFoundException {
        Optional<User> userOp = userRepository.findById(userId);
        if(userOp.isEmpty()){
            throw new UserNotFoundException("User not found");
        }
        User user = userOp.get();
        //Get the list of all expenses that the user is part of (either as a payer or as a receiver)
        List<Expense> allUserExpenses = expenseRepository.findAllByPaidByOrWhoHaveToPay(user);
        return settleUpStrategy.settleUp(allUserExpenses);
    }

    public List<Transaction> settleUpUserForAGroup(int groupId, int userId) throws GroupNotFoundException,  UserNotFoundException {
        Optional<Group> groupOp = groupRepository.findById(groupId);
        if(groupOp.isEmpty()){
            throw new GroupNotFoundException("Group not found");
        }
        Group group = groupOp.get();
        Optional<User> userOp = userRepository.findById(userId);
        if(userOp.isEmpty()){
            throw new UserNotFoundException("User not found");
        }
        User user = userOp.get();
        //Get the list of expenses for a particular group that the user is part of (either as payer or receiver)
        List<Expense> expenses = expenseRepository.findAllByPaidByOrWhoHaveToPayAndGroup(user, group);
        return settleUpStrategy.settleUp(expenses);
    }
}
