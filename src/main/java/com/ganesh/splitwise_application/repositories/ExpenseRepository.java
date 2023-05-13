package com.ganesh.splitwise_application.repositories;

import com.ganesh.splitwise_application.models.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense,Long> {
    Optional<Expense> findById(long expenseId);
}
