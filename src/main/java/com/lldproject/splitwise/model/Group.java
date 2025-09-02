package com.lldproject.splitwise.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity(name="groups")
@Getter
@Setter
public class Group extends BaseModel {
    private String name;
    private String description;
    @ManyToOne
    private User admin;
    @ManyToMany
    private List<User> members;
    @OneToMany
    private List<Expense> expenses;

}
