package com.expensetracker.features.transaction.expense;

import com.expensetracker.features.transaction.base.TransactionRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;

public interface ExpenseRepository extends TransactionRepository<Expense> {
    @Query("SELECT COALESCE(SUM(e.amount), 0) FROM Expense e")
    BigDecimal getTotalExpense();
}
