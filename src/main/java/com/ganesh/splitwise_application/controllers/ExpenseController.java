package com.ganesh.splitwise_application.controllers;

import com.ganesh.splitwise_application.DTO.CreateExpenseDto;
import com.ganesh.splitwise_application.DTO.GetExpenseDto;
import com.ganesh.splitwise_application.converters.Converters;
import com.ganesh.splitwise_application.models.Expense;
import com.ganesh.splitwise_application.models.Group;
import com.ganesh.splitwise_application.models.User;
import com.ganesh.splitwise_application.services.ExpenseService;
import com.ganesh.splitwise_application.services.SelfExpenseService;
import jakarta.persistence.Converter;
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


@RestController
@RequestMapping("/groups")
public class ExpenseController {
    ExpenseService service;
    Converters converters;
    ExpenseController(ExpenseService service,Converters converters){
        this.service=service;
        this.converters=converters;
    }

    @PostMapping(value ="/{groupId}/expenses")
    public ResponseEntity<GetExpenseDto> createExpense(@PathVariable Long groupId, @RequestBody @Valid CreateExpenseDto dto){
        if(groupId==null)
            throw new NullPointerException();
        Expense expense=service.createExpense(groupId,dto);
        return new ResponseEntity<>(converters.expenseToGetExpenseDto(expense),HttpStatus.CREATED);
    }

    @GetMapping(value = "/{groupId}/expenses/{expenseId}")
    public ResponseEntity<GetExpenseDto> getExpense(@PathVariable Long groupId,@PathVariable Long expenseId){
        if(groupId==null||expenseId==null)
            throw new NullPointerException();
        Expense expense=service.getExpense(expenseId);
        if(expense==null)
            throw new NullPointerException();
        //GetExpenseDto ged=new GetExpenseDto();
        //ged.setName(e.getName());ged.setAmount(e.getAmount());
        //Group g=e.getGroup();
        //ged.setGetGroupDto(GetGroupDto.builder().name(g.getName()).about(g.getAbout()).build());
        //User u=e.getCreatedBy();
        //ged.setGetUserDTO(GetUserDTO.builder().name(u.getName()).phone(u.getPhone()).mail(u.getMail()).build());
        GetExpenseDto dto=converters.expenseToGetExpenseDto(expense);
        return new ResponseEntity<>(dto, HttpStatus.FOUND);
    }

    @DeleteMapping("/{groupId}/expenses/{expenseId}")
    public ResponseEntity<GetExpenseDto> deleteExpense(@PathVariable Long expenseId){
        if(expenseId==null)
            throw new NullPointerException();
        Expense expense=service.deleteExpense(expenseId);
        GetExpenseDto dto=converters.expenseToGetExpenseDto(expense);
        return new ResponseEntity<>(dto,HttpStatus.OK);
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
