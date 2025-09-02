package com.lldproject.splitwise.model;

import com.lldproject.splitwise.strategy.ExpenseSplitStrategy;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class ExpensePayer extends BaseModel {
    @ManyToOne
    User user;
    @ManyToOne
    Expense expense;
    int amount;
    public ExpensePayer(User user) {
        this.user = user;
    }
}
