package com.lldproject.splitwise.controller;

import com.lldproject.splitwise.dto.*;
import com.lldproject.splitwise.exception.GroupNotFoundException;
import com.lldproject.splitwise.exception.UserNotFoundException;
import com.lldproject.splitwise.model.Transaction;
import com.lldproject.splitwise.service.SettleUpService;
import com.lldproject.splitwise.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class SettleUpController {
    private final UserService userService;
    SettleUpService settleUpService;
    @Autowired
    public SettleUpController(SettleUpService settleUpService, UserService userService) {
        this.settleUpService = settleUpService;
        this.userService = userService;
    }

    public SettleUpGroupResponseDto settleUpGroup(SettleUpGroupRequestDto settleUpGroupRequestDto) {
        SettleUpGroupResponseDto responseDto = new SettleUpGroupResponseDto();
        try{
            List<Transaction> transactions = settleUpService.settleUpGroup(settleUpGroupRequestDto.getGroupId());
            responseDto.setMessage("Transactions generated for settlement of group with id: "+settleUpGroupRequestDto.getGroupId());
            responseDto.setResponseStatus(ResponseStatus.SUCCESS);
            responseDto.setTransactions(transactions);
        }
        catch(Exception e){
            responseDto.setMessage(e.getMessage());
            responseDto.setResponseStatus(ResponseStatus.FAILURE);
        }
        return responseDto;
    }

    public SettleUpUserResponseDto settleUpUser(SettleUpUserRequestDto settleUpUserRequestDto){
        SettleUpUserResponseDto responseDto = new SettleUpUserResponseDto();
        try {
            List<Transaction> transactions = settleUpService.settleUpUser(settleUpUserRequestDto.getUserId());
            responseDto.setMessage("Transactions generated for user with id: "+settleUpUserRequestDto.getUserId());
            responseDto.setResponseStatus(ResponseStatus.SUCCESS);
        }
        catch(UserNotFoundException e){
            responseDto.setMessage(e.getMessage());
            responseDto.setResponseStatus(ResponseStatus.FAILURE);
        }
        return responseDto;
    }

    public SettleUpUserForGroupResponseDto settleUpUserForAGroup(SettleUpUserForGroupRequestDto settleUpUserForGroupRequestDto){
        SettleUpUserForGroupResponseDto  responseDto = new SettleUpUserForGroupResponseDto();
        try{
            List<Transaction> transactions = settleUpService.settleUpUserForAGroup(settleUpUserForGroupRequestDto.getGroupId(), settleUpUserForGroupRequestDto.getUserId());
            responseDto.setTransactions(transactions);
            responseDto.setMessage("Transactions generated for user with id: "+settleUpUserForGroupRequestDto.getUserId() + "for group with id: "+settleUpUserForGroupRequestDto.getGroupId());
            responseDto.setResponseStatus(ResponseStatus.SUCCESS);
        }
        catch(GroupNotFoundException | UserNotFoundException e){
            responseDto.setMessage(e.getMessage());
            responseDto.setResponseStatus(ResponseStatus.FAILURE);
        }
        return responseDto;
    }
}
