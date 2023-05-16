package com.ganesh.splitwise_application.controllers;

import com.ganesh.splitwise_application.DTO.CreateTransactionDto;
import com.ganesh.splitwise_application.DTO.GetTransactionDto;
import com.ganesh.splitwise_application.DTO.GetUserDTO;
import com.ganesh.splitwise_application.enums.TransactionType;
import com.ganesh.splitwise_application.models.Transaction;
import com.ganesh.splitwise_application.services.ExpenseService;
import com.ganesh.splitwise_application.services.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@AllArgsConstructor
@RestController
public class TransactionController {
    TransactionService transactionService;
    ExpenseService expenseService;
    @PostMapping(value = "/expense/{id}/transaction")
    public ResponseEntity<String> createTransaction(@PathVariable long id, @RequestBody CreateTransactionDto ctd){
        if(ctd.getType()== TransactionType.PAID) {
            double total = transactionService.amountFilledPerExpense(id);
            double expAmount = expenseService.getAmount(id);
            if (total + ctd.getAmount() > expAmount)
                return new ResponseEntity<>("Amount exceeding. Sorry", HttpStatus.NOT_ACCEPTABLE); //checking if the amount is getting exceeded.
        }
        Transaction t = transactionService.createTransaction(id, ctd);
        double expAmount = expenseService.getAmount(id);
        return new ResponseEntity<>("Transaction created",HttpStatus.OK);
        //return new ResponseEntity<>(GetTransactionDto.builder().user(GetUserDTO.builder().name(t.getPayer().getName()).build()).amount(t.getAmount()).type(t.getType()).build(), HttpStatus.CREATED);
    }
    @GetMapping(value = "/transaction/{transId}")
    public ResponseEntity<GetTransactionDto> getTransaction(@PathVariable long transId){
        Transaction t=transactionService.getTransaction(transId);
        if(t==null)
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND); //if no transaction id exists
        return new ResponseEntity<>(GetTransactionDto.builder().user(GetUserDTO.builder().name(t.getPayer().getName()).build()).amount(t.getAmount()).type(t.getType()).build(),HttpStatus.FOUND);
    }
}
