package com.lldproject.splitwise.repository;

import com.lldproject.splitwise.model.ExpensePayer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpensePayerRepository extends JpaRepository<ExpensePayer, Integer> {
}
