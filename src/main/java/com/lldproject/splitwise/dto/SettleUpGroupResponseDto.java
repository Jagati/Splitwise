package com.lldproject.splitwise.dto;

import com.lldproject.splitwise.model.Transaction;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SettleUpGroupResponseDto {
    private String message;
    private ResponseStatus responseStatus;
    private List<Transaction> transactions;
}
