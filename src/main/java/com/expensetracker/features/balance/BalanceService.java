package com.expensetracker.features.balance;

import java.math.BigDecimal;

public interface BalanceService {
    public BigDecimal getBalance();
    public BigDecimal getTotalIncome();
    public BigDecimal getTotalExpense();
    BalanceDTO getBalanceDTO();
}
