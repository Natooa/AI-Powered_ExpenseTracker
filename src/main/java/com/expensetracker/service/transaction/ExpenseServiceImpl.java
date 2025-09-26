package com.expensetracker.service.transaction;

import com.expensetracker.entity.Expense;
import com.expensetracker.repository.ExpenseRepository;
import com.expensetracker.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExpenseServiceImpl extends AbstractTransactionService<Expense> {
    @Autowired
    public ExpenseServiceImpl(ExpenseRepository repository) {
        super(repository);
    }
}
