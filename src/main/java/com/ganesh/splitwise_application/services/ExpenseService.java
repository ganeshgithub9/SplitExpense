package com.ganesh.splitwise_application.services;

import com.ganesh.splitwise_application.DTO.CreateExpenseDto;
import com.ganesh.splitwise_application.DTO.GetExpenseDto;
import com.ganesh.splitwise_application.models.Expense;
import com.ganesh.splitwise_application.models.User;

import java.util.List;

public interface ExpenseService {
    public Expense createExpense(Long id, CreateExpenseDto d);
    public Expense getExpense(Long expenseId) throws NullPointerException;
    public double getAmount(Long id);

    Expense deleteExpense(Long expenseId);

    List<User> getUsers(Long expenseId);
}
