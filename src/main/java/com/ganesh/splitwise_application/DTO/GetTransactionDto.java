package com.ganesh.splitwise_application.DTO;

import com.ganesh.splitwise_application.enums.TransactionType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.apache.catalina.authenticator.SavedRequest;

@Setter
@Getter
public class GetTransactionDto {
    String user;
    Double amount;
    TransactionType type;
    String createdBy;
    String expense;
}
