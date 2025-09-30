package com.expensetracker.service.transaction;

import com.expensetracker.entity.Income;
import com.expensetracker.repository.IncomeRepository;
import com.expensetracker.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class IncomeServiceImpl extends AbstractTransactionService<Income> {
    private final IncomeRepository incomeRepository;

    @Autowired
    public IncomeServiceImpl(IncomeRepository repository) {
        super(repository);
        this.incomeRepository = repository;
    }

    public BigDecimal getTotalIncome() {
        return incomeRepository.getTotalIncome();
    }
}
