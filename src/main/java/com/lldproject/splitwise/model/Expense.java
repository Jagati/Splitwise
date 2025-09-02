package com.lldproject.splitwise.model;

import com.lldproject.splitwise.strategy.ExpenseSplitStrategy;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Expense extends BaseModel {
    private String description;
    private int amount;
    private Date date;
    @ManyToOne
    private Group group;
    @ManyToOne
    private User paidBy;
    @OneToMany(mappedBy = "expense")
    private List<ExpensePayer> whoHaveToPay;
    private ExpenseSplitType splitType;

    public Expense(ExpenseSplitType splitType) {
        this.splitType = splitType;
    }
}
