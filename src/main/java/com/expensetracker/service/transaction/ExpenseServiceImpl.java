package com.expensetracker.service.transaction;

import com.expensetracker.entity.Expense;
import com.expensetracker.repository.ExpenseRepository;
import com.expensetracker.repository.TransactionRepository;
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
