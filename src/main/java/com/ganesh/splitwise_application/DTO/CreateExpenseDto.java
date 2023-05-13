package com.ganesh.splitwise_application.DTO;

import com.ganesh.splitwise_application.models.Expense;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateExpenseDto {
String name;
Double amount;
//Long groupId;
Long userId;
}
