package com.expensetracker.features.balance;

import com.expensetracker.features.transaction.expense.ExpenseServiceImpl;
import com.expensetracker.features.transaction.income.IncomeServiceImpl;
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
        return getBalanceDTO().getTotalBalance();
    }

    @Override
    public BigDecimal getTotalIncome() {
        return getBalanceDTO().getTotalIncome();
    }

    @Override
    public BigDecimal getTotalExpense() {
        return getBalanceDTO().getTotalExpense();
    }

    @Override
    public BalanceDTO  getBalanceDTO() {
        BigDecimal income = incomeService.getTotalIncome();
        BigDecimal expense = expenseService.getTotalExpense();
        BigDecimal totalBalance = income.subtract(expense);
        return new BalanceDTO(income, expense, totalBalance);
    }
}
