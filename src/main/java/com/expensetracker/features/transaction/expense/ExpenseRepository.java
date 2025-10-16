package com.expensetracker.features.transaction.expense;

import com.expensetracker.features.transaction.base.TransactionRepository;
import com.expensetracker.features.users.Users;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;

public interface ExpenseRepository extends TransactionRepository<Expense> {
    @Query("SELECT COALESCE(SUM(e.amount), 0) FROM Expense e WHERE e.user = :user")
    BigDecimal getTotalExpense(@Param("user") Users user);
}
