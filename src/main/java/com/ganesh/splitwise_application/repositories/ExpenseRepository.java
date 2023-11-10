package com.ganesh.splitwise_application.repositories;

import com.ganesh.splitwise_application.models.Expense;
import com.ganesh.splitwise_application.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense,Long> {
    Optional<Expense> findById(Long expenseId);
    @Query(value="select amount from expenses where id=:id",nativeQuery = true)
    Double findAmountById(Long id);

    @Query("select g.users from Expense e inner join e.group g inner join g.users where e.id=:expenseId")
    List<User> findUsers(Long expenseId);
}
