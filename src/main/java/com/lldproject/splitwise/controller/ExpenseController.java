package com.lldproject.splitwise.controller;

import com.lldproject.splitwise.dto.AddExpenseRequestDto;
import com.lldproject.splitwise.dto.AddExpenseResponseDto;
import com.lldproject.splitwise.dto.ResponseStatus;
import com.lldproject.splitwise.exception.GroupNotFoundException;
import com.lldproject.splitwise.exception.UserNotFoundException;
import com.lldproject.splitwise.model.Expense;
import com.lldproject.splitwise.service.ExpenseService;
import org.springframework.stereotype.Controller;

@Controller
public class ExpenseController {
    private final ExpenseService expenseService;
    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    public AddExpenseResponseDto addExpense(AddExpenseRequestDto addExpenseRequestDto){
        AddExpenseResponseDto responseDto = new AddExpenseResponseDto();
        try{
            Expense expense = expenseService.addExpense(addExpenseRequestDto.getUser_id(), addExpenseRequestDto.getAmount(), addExpenseRequestDto.getPaid_on(), addExpenseRequestDto.getGroup_id(), addExpenseRequestDto.getSplitType(), addExpenseRequestDto.getPayer_ids());
            responseDto.setExpenseId(expense.getId());
            responseDto.setResponseStatus(ResponseStatus.SUCCESS);
            responseDto.setMessage("Expense Added Successfully");
        }
        catch(UserNotFoundException | GroupNotFoundException e){
            responseDto.setResponseStatus(ResponseStatus.FAILURE);
            responseDto.setMessage(e.getMessage());
        }
        return responseDto;
    }
}
