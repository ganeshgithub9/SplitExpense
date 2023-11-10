package com.ganesh.splitwise_application.converters;

import com.ganesh.splitwise_application.DTO.*;
import com.ganesh.splitwise_application.models.Expense;
import com.ganesh.splitwise_application.models.Group;
import com.ganesh.splitwise_application.models.Transaction;
import com.ganesh.splitwise_application.models.User;
import org.modelmapper.internal.bytebuddy.utility.GraalImageCode;

public interface Converters {
    User createUserDtoToUser(CreateUserDTO dto);
    GetUserDTO userToGetUserDTO(User user);
    GetGroupDto groupToGetGroupDTO(Group g);
    Group createGroupDtoToGroup(CreateGroupDto dto);
    Expense createExpenseDtoToExpense(CreateExpenseDto dto);
    GetExpenseDto expenseToGetExpenseDto(Expense expense);
    Transaction createTransactionDtoToTransaction(CreateTransactionDto dto);
    GetTransactionDto transactionToGetTransactionDto(Transaction transaction);
}
