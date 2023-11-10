package com.ganesh.splitwise_application.interfaces;

import com.ganesh.splitwise_application.DTO.SettleUpTransaction;
import com.ganesh.splitwise_application.models.Transaction;
import com.ganesh.splitwise_application.models.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
//@Qualifier("leaderSettleUpStrategy")
public class LeaderSettleUpStrategy implements SettleUpStrategy{
    public List<SettleUpTransaction> settleUpTransactions(User leader, List<User> users, List<Transaction> l1, List<Transaction> l2){
        return null;
    }
}
