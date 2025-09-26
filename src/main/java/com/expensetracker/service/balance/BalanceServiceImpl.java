//package com.expensetracker.service.balance;
//
//import com.expensetracker.entity.Expense;
//import com.expensetracker.entity.Income;
//import com.expensetracker.service.transaction.ExpenseServiceImpl;
//import com.expensetracker.service.transaction.IncomeServiceImpl;
//import org.springframework.stereotype.Service;
//import java.math.BigDecimal;
//
//@Service
//public class BalanceServiceImpl implements BalanceService {
//    private final IncomeServiceImpl incomeService;
//    private final ExpenseServiceImpl expenseService;
//
//    public BalanceServiceImpl(IncomeServiceImpl incomeService, ExpenseServiceImpl expenseService) {
//        this.incomeService = incomeService;
//        this.expenseService = expenseService;
//    }
//
//    @Override
//    public BigDecimal getBalance() {
//        BigDecimal incomeSum = incomeService.getAllTransactions().values().stream().map(Income::getAmount)
//                .reduce(BigDecimal.ZERO, BigDecimal::add);
//
//        BigDecimal expenseSum = expenseService.getAllTransactions().values().stream().map(Expense::getAmount)
//                .reduce(BigDecimal.ZERO, BigDecimal::add);
//
//        return incomeSum.subtract(expenseSum);
//    }
//}
