package com.lldproject.splitwise.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddExpenseResponseDto {
    ResponseStatus responseStatus;
    String message;
    int expenseId;
}
