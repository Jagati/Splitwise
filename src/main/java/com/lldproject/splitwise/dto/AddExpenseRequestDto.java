package com.lldproject.splitwise.dto;

import com.lldproject.splitwise.model.ExpenseSplitType;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Setter
@Getter
public class AddExpenseRequestDto {
    int user_id;
    int amount;
    Date paid_on;
    int group_id;
    ExpenseSplitType splitType;
    List<Integer> payer_ids;
}
