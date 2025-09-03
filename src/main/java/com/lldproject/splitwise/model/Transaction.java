package com.lldproject.splitwise.model;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Transaction extends BaseModel{
    private User from;
    private User to;
    private int amount;
}
