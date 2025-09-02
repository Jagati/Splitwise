package com.lldproject.splitwise.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSignUpResponseDto {
    private String message;
    private int userId;
    private ResponseStatus responseStatus;
}
