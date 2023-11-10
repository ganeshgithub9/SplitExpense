package com.ganesh.splitwise_application.services;

import com.ganesh.splitwise_application.DTO.CreateExpenseDto;
import com.ganesh.splitwise_application.DTO.GetExpenseDto;
import com.ganesh.splitwise_application.converters.Converters;
import com.ganesh.splitwise_application.models.Expense;
import com.ganesh.splitwise_application.models.Group;
import com.ganesh.splitwise_application.models.User;
import com.ganesh.splitwise_application.repositories.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SelfExpenseService implements ExpenseService{
    GroupService groupService;
    UserService userService;
     ExpenseRepository expenseRepository;
    Converters converters;
     @Autowired
     SelfExpenseService(GroupService gs, UserService us, ExpenseRepository er,Converters converters){
         groupService=gs;
         userService =us;
         expenseRepository=er;
         this.converters=converters;
     }

    public Expense createExpense(Long groupId,CreateExpenseDto dto) {
//        Expense e=new Expense();
//        e.setName(d.getName());
//        e.setAmount(d.getAmount());
//        User u= selfUserService.getUser(d.getUserId());
//        e.setCreatedBy(u);
//        Group g=groupService.getGroup(id);
//        e.setGroup(g);
//        return expenseRepository.save(e);
        System.out.println("expense dto to expense");
        Expense expense=converters.createExpenseDtoToExpense(dto);
        System.out.println("expense created");
        User createdBy=userService.findById(dto.getUserId());
        Group group=groupService.findById(groupId);
        expense.setCreatedBy(createdBy);
        expense.setGroup(group);
        expense=expenseRepository.save(expense);
        System.out.println("expense to expense dto");
        ///GetExpenseDto dto1=converters.expenseToGetExpenseDto(expense);
        System.out.println("completed");
        return expense;
    }

    public Expense getExpense(Long expenseId) throws NullPointerException {
         return expenseRepository.findById(expenseId).orElse(null);
//         if(expense==null)
//             throw new NullPointerException();
//         GetExpenseDto dto=converters.expenseToGetExpenseDto(expense);
//         return dto;
    }

    public double getAmount(Long id) {
         return expenseRepository.findAmountById(id);
    }

    @Override
    public Expense deleteExpense(Long expenseId) {
         Expense expense=expenseRepository.findById(expenseId).orElse(null);
         expenseRepository.deleteById(expenseId);
        return expense;
    }

    @Override
    public List<User> getUsers(Long expenseId) {
        return expenseRepository.findUsers(expenseId);
    }
}
