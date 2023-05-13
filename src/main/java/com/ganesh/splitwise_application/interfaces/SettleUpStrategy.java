package com.ganesh.splitwise_application.interfaces;

import com.ganesh.splitwise_application.DTO.SettleUpTransaction;
import com.ganesh.splitwise_application.models.Transaction;
import com.ganesh.splitwise_application.models.User;

import java.util.List;

public interface SettleUpStrategy {
    public List<SettleUpTransaction> settleUpTransactions(User captain, List<Transaction> l1, List<Transaction> l2);
}
