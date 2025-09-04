package com.lldproject.splitwise.repository;

import com.lldproject.splitwise.model.Expense;
import com.lldproject.splitwise.model.Group;
import com.lldproject.splitwise.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    @Query("SELECT ex FROM Expense ex WHERE ex.paidBy = :person OR :person IN ex.whoHaveToPay")
    List<Expense> findAllByPaidByOrWhoHaveToPay(@Param("person")User person);
    @Query("SELECT ex FROM Expense ex WHERE (ex.paidBy = :person OR :person IN ex.whoHaveToPay) AND (ex.group = :group)")
    List<Expense> findAllByPaidByOrWhoHaveToPayAndGroup(@Param("person")User person, @Param("group") Group group);
}
