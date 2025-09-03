package com.lldproject.splitwise.controller;

import com.lldproject.splitwise.dto.ResponseStatus;
import com.lldproject.splitwise.dto.SettleUpGroupRequestDto;
import com.lldproject.splitwise.dto.SettleUpGroupResponseDto;
import com.lldproject.splitwise.model.Transaction;
import com.lldproject.splitwise.service.SettleUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class SettleUpController {
    SettleUpService settleUpService;
    @Autowired
    public SettleUpController(SettleUpService settleUpService) {
        this.settleUpService = settleUpService;
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
}
