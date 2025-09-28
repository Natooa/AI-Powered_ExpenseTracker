package com.expensetracker.service.balance;

import com.expensetracker.dto.BalanceDTO;
import com.expensetracker.entity.Expense;
import com.expensetracker.entity.Income;
import com.expensetracker.service.transaction.ExpenseServiceImpl;
import com.expensetracker.service.transaction.IncomeServiceImpl;
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
        BigDecimal income = incomeService.getAllTransactions().stream()
                .map(Income::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal expense = expenseService.getAllTransactions().stream()
                .map(Expense::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal totalBalance = income.subtract(expense);
        return new BalanceDTO(income, expense, totalBalance);
    }
}
