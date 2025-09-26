package com.expensetracker.service.transaction;

import com.expensetracker.entity.Income;
import com.expensetracker.repository.IncomeRepository;
import com.expensetracker.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IncomeServiceImpl extends AbstractTransactionService<Income> {
    @Autowired
    public IncomeServiceImpl(IncomeRepository repository) {
        super(repository);
    }
}
