package com.expensetracker.features.transaction.income;

import com.expensetracker.features.transaction.base.TransactionRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;

public interface IncomeRepository extends TransactionRepository<Income> {
    @Query("SELECT COALESCE(SUM(i.amount), 0) FROM Income i ")
    BigDecimal getTotalIncome();
}
