package com.ganesh.splitwise_application.DTO;

import com.ganesh.splitwise_application.enums.TransactionType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class CreateTransactionDto {
    @NotNull(message = "Please provide user id")
    Long userId;
    @Min(value = 1,message = "Amount should be greater than 0")
    Double amount;
    TransactionType type;
    Long createdBy;
}
