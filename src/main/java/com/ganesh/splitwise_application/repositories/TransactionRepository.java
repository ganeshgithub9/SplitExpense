package com.ganesh.splitwise_application.repositories;

import com.ganesh.splitwise_application.models.Transaction;
import com.ganesh.splitwise_application.enums.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Long> {

    List<Transaction> findAllByExpenseIdAndType(long id, TransactionType t);
}
