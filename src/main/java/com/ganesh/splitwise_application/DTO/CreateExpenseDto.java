package com.ganesh.splitwise_application.DTO;

import com.ganesh.splitwise_application.models.Expense;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateExpenseDto {
String name;
@Min(value = 1,message = "Amount should be greater than zero")
Double amount;
//Long groupId;
@NotNull(message = "Please give User Id")
Long userId;
}
