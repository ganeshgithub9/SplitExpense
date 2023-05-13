package com.ganesh.splitwise_application.controllers;

import com.ganesh.splitwise_application.DTO.CreateExpenseDto;
import com.ganesh.splitwise_application.DTO.GetExpenseDto;
import com.ganesh.splitwise_application.DTO.GetGroupDto;
import com.ganesh.splitwise_application.DTO.GetUserDTO;
import com.ganesh.splitwise_application.models.Expense;
import com.ganesh.splitwise_application.models.Group;
import com.ganesh.splitwise_application.models.User;
import com.ganesh.splitwise_application.services.ExpenseService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
public class ExpenseController {
    ExpenseService service;

    @PostMapping(value ="/group/{id}/expense")
    public Expense createExpense(@PathVariable long id, @RequestBody CreateExpenseDto d){
        return service.createExpense(id,d);
    }

    @GetMapping(value = "/group/{groupId}/expense/{expenseId}")
    public ResponseEntity<GetExpenseDto> getExpense(@PathVariable long expenseId){
        Expense e=service.getExpense(expenseId);
        GetExpenseDto ged=new GetExpenseDto();
        ged.setName(e.getName());ged.setAmount(e.getAmount());
        Group g=e.getGroup();
        ged.setGetGroupDto(GetGroupDto.builder().name(g.getName()).about(g.getAbout()).build());
        User u=e.getCreatedBy();
        ged.setGetUserDTO(GetUserDTO.builder().name(u.getName()).phone(u.getPhone()).mail(u.getMail()).build());
        return new ResponseEntity<>(ged, HttpStatus.FOUND);
    }
}
