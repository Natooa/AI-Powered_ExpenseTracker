package com.expensetracker.service;

import com.expensetracker.entity.Expense;
import com.expensetracker.entity.Income;

import java.math.BigDecimal;

public interface BalanceService {
    public BigDecimal getBalance();
}
