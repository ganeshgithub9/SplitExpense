package com.ganesh.splitwise_application.services;

import com.ganesh.splitwise_application.DTO.CreateTransactionDto;
import com.ganesh.splitwise_application.enums.TransactionType;
import com.ganesh.splitwise_application.models.Transaction;

import java.util.List;

public interface TransactionService {
    Transaction createTransaction(Long id, CreateTransactionDto ctd);
    Transaction getTransaction(Long transId);
    List<Transaction> getTransactionsByExpenseAndTransactionType(Long id, TransactionType t);
    double amountPaidPerExpense(Long exp_id);

    double billDistributionPerExpense(Long expenseId);
}
