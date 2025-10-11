package com.expensetracker.features.transaction.expense;

import com.expensetracker.features.transaction.base.AbstractTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ExpenseServiceImpl extends AbstractTransactionService<Expense> {
    private final ExpenseRepository expenseRepository;

    @Autowired
    public ExpenseServiceImpl(ExpenseRepository repository) {
        super(repository);
        this.expenseRepository = repository;
    }

    public BigDecimal getTotalExpense() {
        return expenseRepository.getTotalExpense();
    }
}
