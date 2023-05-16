package com.ganesh.splitwise_application.controllers;

import com.ganesh.splitwise_application.DTO.SettleUpTransaction;
import com.ganesh.splitwise_application.models.Expense;
import com.ganesh.splitwise_application.services.ExpenseService;
import com.ganesh.splitwise_application.services.SettleUpService;
import com.ganesh.splitwise_application.services.TransactionService;
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
    @GetMapping(value = "expense/{id}/settleup")
    public List<SettleUpTransaction> generateSettleUpTransactions(@PathVariable long id){
        double total = transactionService.amountFilledPerExpense(id);
        double exp_amount=expenseService.getAmount(id);
        if(total!=exp_amount)
            throw new RuntimeException("You missed paying total amount"); //checking amount paid equal to expense amount
        return settleUpService.settleUpTransactions(id);
    }
}
