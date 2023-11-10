package com.ganesh.splitwise_application.services;

import com.ganesh.splitwise_application.DTO.SettleUpTransaction;

import java.util.List;

public interface SettleUpService {
    List<SettleUpTransaction> settleUpTransactions(Long expenseId);
}
