package com.ganesh.splitwise_application.DTO;

import com.ganesh.splitwise_application.enums.TransactionType;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class GetTransactionDto {
    GetUserDTO user;
    Double amount;
    TransactionType type;
}
