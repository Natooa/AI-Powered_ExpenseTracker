package com.expensetracker.service.balance;

import com.expensetracker.dto.BalanceDTO;

import java.math.BigDecimal;

public interface BalanceService {
    public BigDecimal getBalance();
    public BigDecimal getTotalIncome();
    public BigDecimal getTotalExpense();
    BalanceDTO getBalanceDTO();
}
