package com.ganesh.splitwise_application.services;

import com.ganesh.splitwise_application.DTO.CreateExpenseDto;
import com.ganesh.splitwise_application.models.Expense;
import com.ganesh.splitwise_application.models.Group;
import com.ganesh.splitwise_application.models.User;
import com.ganesh.splitwise_application.repositories.ExpenseRepository;
import com.ganesh.splitwise_application.repositories.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExpenseService {
    GroupService groupService;
    UserService userService;
     ExpenseRepository expenseRepository;

     @Autowired
     ExpenseService(GroupService gs, UserService us, ExpenseRepository er){
         groupService=gs;
         userService=us;
         expenseRepository=er;
     }

    public Expense createExpense(long id,CreateExpenseDto d) {
        Expense e=new Expense();
        e.setName(d.getName());
        e.setAmount(d.getAmount());
        User u=userService.getUser(d.getUserId());
        e.setCreatedBy(u);
        Group g=groupService.getGroup(id);
        e.setGroup(g);
        return expenseRepository.save(e);
    }

    public Expense getExpense(long expenseId) {
         return expenseRepository.findById(expenseId).orElse(null);
    }
}
