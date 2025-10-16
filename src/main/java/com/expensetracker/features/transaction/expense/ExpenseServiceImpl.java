package com.expensetracker.features.transaction.expense;

import com.expensetracker.features.transaction.base.AbstractTransactionService;
import com.expensetracker.features.transaction.income.IncomeRepository;
import com.expensetracker.features.users.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ExpenseServiceImpl extends AbstractTransactionService<Expense> {
    private ExpenseRepository expenseRepository;
    @Autowired
    public ExpenseServiceImpl(ExpenseRepository expenseRepository, UsersRepository usersRepository) {
        super(expenseRepository, usersRepository);
        this.expenseRepository = expenseRepository;
    }

    public BigDecimal getTotalExpense() {
        return expenseRepository.getTotalExpense(getCurrentUser());
    }
}
