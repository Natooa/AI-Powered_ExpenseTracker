package com.expensetracker.service;

import com.expensetracker.entity.Expense;
import com.expensetracker.entity.Income;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;

@Service
public class BalanceServiceImpl implements BalanceService {
    private final IncomeServiceImpl incomeService;
    private final ExpenseServiceImpl expenseService;

    public BalanceServiceImpl(IncomeServiceImpl incomeService, ExpenseServiceImpl expenseService) {
        this.incomeService = incomeService;
        this.expenseService = expenseService;
    }

    @Override
    public BigDecimal getBalance() {
        BigDecimal incomeSum = incomeService.getTransactionMap().values().stream().map(Income::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal expenseSum = expenseService.getTransactionMap().values().stream().map(Expense::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return incomeSum.subtract(expenseSum);
    }
}
