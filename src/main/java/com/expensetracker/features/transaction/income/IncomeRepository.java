package com.expensetracker.features.transaction.income;

import com.expensetracker.features.transaction.base.TransactionRepository;
import com.expensetracker.features.users.Users;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;

public interface IncomeRepository extends TransactionRepository<Income> {
    @Query("SELECT COALESCE(SUM(i.amount), 0) FROM Income i WHERE i.user = :user")
    BigDecimal getTotalIncome(@Param("user") Users user);
}
