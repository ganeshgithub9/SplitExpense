package com.ganesh.splitwise_application.controllers;

import com.ganesh.splitwise_application.DTO.SettleUpTransaction;
import com.ganesh.splitwise_application.exceptions.AmountExceededException;
import com.ganesh.splitwise_application.services.*;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@AllArgsConstructor
@RestController
public class SettleUpController {
    SettleUpService settleUpService;
    TransactionService transactionService;
    ExpenseService expenseService;
    @GetMapping(value = "expenses/{expenseId}/settleUp")
    public List<SettleUpTransaction> generateSettleUpTransactions(@PathVariable Long expenseId) throws AmountExceededException{
        System.out.println("settle up controller");
        double expenseAmount= expenseService.getAmount(expenseId);
        double paidTotal = transactionService.amountPaidPerExpense(expenseId);
        double distributionTotal = transactionService.billDistributionPerExpense(expenseId);
        if(paidTotal<expenseAmount)
            throw new AmountExceededException("You have not paid total expense amount"); //checking amount paid equal to expense amount
        if(distributionTotal<expenseAmount)
            throw new AmountExceededException("You have not distributed total expense amount");
        return settleUpService.settleUpTransactions(expenseId);
    }
}
