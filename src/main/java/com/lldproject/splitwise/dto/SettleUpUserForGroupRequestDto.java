package com.lldproject.splitwise.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SettleUpUserForGroupRequestDto {
    private int userId;
    private int groupId;
}
