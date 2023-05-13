package com.ganesh.splitwise_application.services;

import com.ganesh.splitwise_application.DTO.CreateTransactionDto;
import com.ganesh.splitwise_application.models.Transaction;
import com.ganesh.splitwise_application.enums.TransactionType;
import com.ganesh.splitwise_application.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {
    TransactionRepository transactionRepository;
    UserService userService;
    ExpenseService expenseService;
    @Autowired
    public TransactionService(TransactionRepository tr, UserService us,ExpenseService es){
        transactionRepository=tr;
        userService=us;
        expenseService=es;
    }
    public Transaction createTransaction(long id, CreateTransactionDto ctd) {
        Transaction t=new Transaction();
        t.setPayer(userService.getUser(ctd.getUserId()));
        t.setAmount(ctd.getAmount());
        t.setType(ctd.getType());
        t.setExpense(expenseService.getExpense(id));
        return transactionRepository.save(t);
    }

    public Transaction getTransaction(long transId) {
        return transactionRepository.findById(transId).orElse(null);
    }

    public List<Transaction> getTransactionsByExpenseAndTransactionType(long id, TransactionType t) {
        return transactionRepository.findAllByExpenseIdAndType(id,t);
    }
}
