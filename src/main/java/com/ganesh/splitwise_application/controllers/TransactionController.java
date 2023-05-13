package com.ganesh.splitwise_application.controllers;

import com.ganesh.splitwise_application.DTO.CreateTransactionDto;
import com.ganesh.splitwise_application.DTO.GetTransactionDto;
import com.ganesh.splitwise_application.DTO.GetUserDTO;
import com.ganesh.splitwise_application.models.Transaction;
import com.ganesh.splitwise_application.services.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@AllArgsConstructor
@RestController
public class TransactionController {
    TransactionService transactionService;
    @PostMapping(value = "/expense/{id}/transaction")
    public ResponseEntity<GetTransactionDto> createTransaction(@PathVariable long id, @RequestBody CreateTransactionDto ctd){
        Transaction t=transactionService.createTransaction(id,ctd);
        return new ResponseEntity<>(GetTransactionDto.builder().user(GetUserDTO.builder().name(t.getPayer().getName()).build()).amount(t.getAmount()).type(t.getType()).build(), HttpStatus.CREATED);
    }
    @GetMapping(value = "/transaction/{transId}")
    public ResponseEntity<GetTransactionDto> getTransaction(@PathVariable long transId){
        Transaction t=transactionService.getTransaction(transId);
        return new ResponseEntity<>(GetTransactionDto.builder().user(GetUserDTO.builder().name(t.getPayer().getName()).build()).amount(t.getAmount()).type(t.getType()).build(),HttpStatus.FOUND);
    }
}
