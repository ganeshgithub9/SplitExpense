package com.ganesh.splitwise_application.services;

import com.ganesh.splitwise_application.DTO.CreateTransactionDto;
import com.ganesh.splitwise_application.converters.Converters;
import com.ganesh.splitwise_application.models.Expense;
import com.ganesh.splitwise_application.models.Transaction;
import com.ganesh.splitwise_application.enums.TransactionType;
import com.ganesh.splitwise_application.repositories.TransactionRepository;
import jakarta.persistence.Converter;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@AllArgsConstructor
@Service
public class SelfTransactionService implements TransactionService{
    TransactionRepository transactionRepository;
    UserService userService;
    ExpenseService expenseService;
    Converters converters;
//    @Autowired
//    public SelfTransactionService(TransactionRepository tr, UserService us, ExpenseService es){
//        transactionRepository=tr;
//        userService =us;
//        expenseService =es;
//    }
    public Transaction createTransaction(Long id, CreateTransactionDto ctd) {
        System.out.println("Transaction service");
        //Transaction t=new Transaction();
        Transaction t= converters.createTransactionDtoToTransaction(ctd);
        System.out.println("Transaction created");
        t.setUser(userService.getUser(ctd.getUserId()));
        t.setCreatedBy(userService.getUser(ctd.getCreatedBy()));
//        t.setAmount(ctd.getAmount());
//        t.setType(ctd.getType());
        t.setExpense(expenseService.getExpense(id));
        return transactionRepository.save(t);
    }

    public Transaction getTransaction(Long transId) {
        return transactionRepository.findById(transId).orElse(null);
    }

    public List<Transaction> getTransactionsByExpenseAndTransactionType(Long id, TransactionType t) {
        return transactionRepository.findAllByExpenseIdAndType(id,t);
    }

    public double amountPaidPerExpense(Long exp_id) {
        Expense e= expenseService.getExpense(exp_id);
         Double result=transactionRepository.findSumByExpenseAndType(e,TransactionType.PAID);
         if(result==null)
             return 0;
         return (double) result;
    }

    @Override
    public double billDistributionPerExpense(Long expenseId) {
        Expense e= expenseService.getExpense(expenseId);
        Double result=transactionRepository.findSumByExpenseAndType(e,TransactionType.BILL_OF_PERSON);
        if(result==null)
            return 0;
        return (double) result;
    }
}
