package com.lldproject.splitwise.service;

import com.lldproject.splitwise.exception.GroupNotFoundException;
import com.lldproject.splitwise.model.Expense;
import com.lldproject.splitwise.model.Group;
import com.lldproject.splitwise.model.Transaction;
import com.lldproject.splitwise.repository.GroupRepository;
import com.lldproject.splitwise.strategy.SettleUpStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SettleUpService {
    private final GroupRepository groupRepository;
    private final SettleUpStrategy settleUpStrategy;

    @Autowired
    public SettleUpService(GroupRepository groupRepository, SettleUpStrategy settleUpStrategy) {
        this.groupRepository = groupRepository;
        this.settleUpStrategy = settleUpStrategy;
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
}
