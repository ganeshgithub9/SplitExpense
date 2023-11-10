package com.ganesh.splitwise_application.services;

import com.ganesh.splitwise_application.DTO.SettleUpTransaction;
import com.ganesh.splitwise_application.interfaces.SettleUpStrategy;
import com.ganesh.splitwise_application.models.Transaction;
import com.ganesh.splitwise_application.enums.TransactionType;
import com.ganesh.splitwise_application.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SelfSettleUpService implements SettleUpService{
    TransactionService transactionService;
    ExpenseService expenseService;
    SettleUpStrategy settleUpStrategy;
    @Autowired
    public SelfSettleUpService(TransactionService ts, ExpenseService es, @Qualifier("captainSettleUpStrategy") SettleUpStrategy ss){
        transactionService=ts;
        expenseService =es;
        settleUpStrategy=ss;
    }
    public List<SettleUpTransaction> settleUpTransactions(Long expenseId){
        System.out.println("settle up service");
        List<User> users=expenseService.getUsers(expenseId);
        List<Transaction> distributedTransactions=transactionService.getTransactionsByExpenseAndTransactionType(expenseId, TransactionType.BILL_OF_PERSON);
        List<Transaction> paidTransactions=transactionService.getTransactionsByExpenseAndTransactionType(expenseId, TransactionType.PAID);
        User captain= expenseService.getExpense(expenseId).getCreatedBy();
        return settleUpStrategy.settleUpTransactions(captain,users,distributedTransactions,paidTransactions);
    }
}
