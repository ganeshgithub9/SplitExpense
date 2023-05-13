package com.ganesh.splitwise_application.DTO;

import com.ganesh.splitwise_application.enums.TransactionType;
import lombok.Getter;

@Getter
public class CreateTransactionDto {
    Long userId;
    Double amount;
    TransactionType type;
}
