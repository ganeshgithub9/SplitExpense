package com.ganesh.splitwise_application.controllers;

import com.ganesh.splitwise_application.DTO.CreateTransactionDto;
import com.ganesh.splitwise_application.DTO.GetTransactionDto;
import com.ganesh.splitwise_application.converters.Converters;
import com.ganesh.splitwise_application.enums.TransactionType;
import com.ganesh.splitwise_application.exceptions.AmountExceededException;
import com.ganesh.splitwise_application.models.Transaction;
import com.ganesh.splitwise_application.services.ExpenseService;
import com.ganesh.splitwise_application.services.TransactionService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@RestController
@RequestMapping("/expenses")
public class TransactionController {
    TransactionService transactionService;
    ExpenseService expenseService;
    Converters converters;
    @PostMapping(value = "/{expenseId}/transactions")
    public ResponseEntity<GetTransactionDto> createTransaction(@PathVariable Long expenseId,@Valid @RequestBody CreateTransactionDto ctd) throws AmountExceededException{
        System.out.println("Transaction controller");
        if(expenseId==null)
            throw new NullPointerException();
        double expAmount = expenseService.getAmount(expenseId);
        if(ctd.getType()== TransactionType.PAID) {
            double total = transactionService.amountPaidPerExpense(expenseId),futureTotal=total + ctd.getAmount();
            if(total==expAmount)
                throw new AmountExceededException("You have paid the entire expense");
            if (futureTotal> expAmount)
                throw new AmountExceededException("You are trying to pay more money than expected. Kindly check and pay remaining amount"); //checking if the amount is getting exceeded.
        }
        if(ctd.getType()== TransactionType.BILL_OF_PERSON) {
            double total = transactionService.billDistributionPerExpense(expenseId),futureTotal=total + ctd.getAmount();
            if(total==expAmount)
                throw new AmountExceededException("You have distributed the entire expense");
            if (total + ctd.getAmount() > expAmount)
                throw new AmountExceededException("Distributed amount exceeding the limit. Kindly enter remaining distribution amount"); //checking if the amount is getting exceeded.
        }
        Transaction t = transactionService.createTransaction(expenseId, ctd);
        return new ResponseEntity<>(converters.transactionToGetTransactionDto(t),HttpStatus.OK);
        //double expAmount = expenseService.getAmount(expenseId);
        //return new ResponseEntity<>("Transaction created",HttpStatus.OK);
        //return new ResponseEntity<>(GetTransactionDto.builder().user(GetUserDTO.builder().name(t.getPayer().getName()).build()).amount(t.getAmount()).type(t.getType()).build(), HttpStatus.CREATED);
    }
    @GetMapping(value = "/{expenseId}/transactions/{transId}")
    public ResponseEntity<GetTransactionDto> getTransaction(@PathVariable Long transId){
        Transaction t=transactionService.getTransaction(transId);
        if(t==null)
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND); //if no transaction id exists
        //return new ResponseEntity<>(GetTransactionDto.builder().user(GetUserDTO.builder().name(t.getUser().getName()).build()).amount(t.getAmount()).type(t.getType()).build(),HttpStatus.FOUND);
        return null;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        for (ObjectError error : ex.getBindingResult().getAllErrors()) {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        }
        return errors;
    }
}
