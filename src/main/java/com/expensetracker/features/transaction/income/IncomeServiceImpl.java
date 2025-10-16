package com.expensetracker.features.transaction.income;

import com.expensetracker.features.transaction.base.AbstractTransactionService;
import com.expensetracker.features.users.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class IncomeServiceImpl extends AbstractTransactionService<Income> {
    private IncomeRepository incomeRepository;
    @Autowired
    public IncomeServiceImpl(IncomeRepository incomeRepository, UsersRepository usersRepository) {
        super(incomeRepository, usersRepository);
        this.incomeRepository = incomeRepository;
    }

    public BigDecimal getTotalIncome() {
        return incomeRepository.getTotalIncome(getCurrentUser());
    }
}
