package com.lldproject.splitwise.command;

import com.lldproject.splitwise.controller.ExpenseController;
import com.lldproject.splitwise.dto.AddExpenseRequestDto;
import com.lldproject.splitwise.dto.AddExpenseResponseDto;
import com.lldproject.splitwise.dto.SettleUpGroupRequestDto;
import com.lldproject.splitwise.dto.SettleUpGroupResponseDto;
import com.lldproject.splitwise.model.ExpenseSplitType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Component
public class AddExpenseCommand implements Command {
    @Autowired
    ExpenseController expenseController;

    @Override
    public boolean matches(String input) {
        String words[] = input.split(" ");
        return words[0].equals("addexpense") && words.length==5;
    }

    @Override
    public void execute(String input) {
        String words[]=input.split(" ");

        int userId=Integer.parseInt(words[1]);
        int amount=Integer.parseInt(words[2]);
        int groupId=Integer.parseInt(words[3]);
        int splitType= Integer.parseInt(words[4]);
        //List<Integer> payer_ids =

        AddExpenseRequestDto requestDTO=new AddExpenseRequestDto();
        requestDTO.setUser_id(userId);
        requestDTO.setAmount(amount);
        requestDTO.setGroup_id(groupId);
        //requestDTO.setSplitType(splitType);
        //requestDTO.getPaid_on(LocalDate.now());
        AddExpenseResponseDto responseDto = expenseController.addExpense(requestDTO);
        System.out.println(responseDto.getMessage());
    }

}
