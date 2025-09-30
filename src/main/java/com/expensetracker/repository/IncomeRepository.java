package com.expensetracker.repository;

import com.expensetracker.entity.Income;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;

public interface IncomeRepository extends TransactionRepository<Income>{
    @Query("SELECT COALESCE(SUM(i.amount), 0) FROM Income i ")
    BigDecimal getTotalIncome();
}
