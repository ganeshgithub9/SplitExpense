package com.ganesh.splitwise_application.services;

import com.ganesh.splitwise_application.DTO.SettleUpTransaction;
import com.ganesh.splitwise_application.interfaces.SettleUpStrategy;
import com.ganesh.splitwise_application.models.Transaction;
import com.ganesh.splitwise_application.enums.TransactionType;
import com.ganesh.splitwise_application.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SettleUpService {
    TransactionService transactionService;
    ExpenseService expenseService;
    SettleUpStrategy settleUpStrategy;
    @Autowired
    public SettleUpService(TransactionService ts,ExpenseService es,SettleUpStrategy ss){
        transactionService=ts;
        expenseService=es;
        settleUpStrategy=ss;
    }
    public List<SettleUpTransaction> settleUpTransactions(long id){
        List<Transaction> l1=transactionService.getTransactionsByExpenseAndTransactionType(id, TransactionType.BILL_OF_PERSON);
        List<Transaction> l2=transactionService.getTransactionsByExpenseAndTransactionType(id, TransactionType.PAID);
        User captain=expenseService.getExpense(id).getCreatedBy();
        return settleUpStrategy.settleUpTransactions(captain,l1,l2);
    }
}
