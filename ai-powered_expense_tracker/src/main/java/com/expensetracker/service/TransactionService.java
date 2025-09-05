package com.expensetracker.service;

import com.expensetracker.controller.CategoryContoller;
import com.expensetracker.entity.Category;
import com.expensetracker.entity.Expense;
import com.expensetracker.entity.Income;
import com.expensetracker.entity.Transaction;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Scanner;

public class TransactionService {
    private final HashMap<Long, Income> incomesMap = new HashMap<>();
    private final HashMap<Long, Expense> expenseMap = new HashMap<>();

    public HashMap<Long, Income> getIncomesMap() {
        return incomesMap;
    }

    public HashMap<Long, Expense> getExpenseMap() {
        return expenseMap;
    }

    //add and remove methods for each Map
    public void addIncome(Income income) {
        incomesMap.put(income.getId(), income);
    }

    public void addExpense(Expense expense) {
        expenseMap.put(expense.getId(),  expense);
    }

    public void removeIncomeById(Long id) {
        if (incomesMap.containsKey(id)) {
            incomesMap.remove(id);
        } else {
            System.out.println("There is no such income with id " + id);
        }
    }

    public void removeExpenseById(Long id) {
        if(expenseMap.containsKey(id)) {
            expenseMap.remove(id);
        } else {
            System.out.println("There is no such expense with id " + id);
        }
    }

    //getters Methods
    public BigDecimal getBalance() {
        BigDecimal incomeSum = incomesMap.values().stream().map(Income::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal expenseSum = expenseMap.values().stream().map(Expense::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return incomeSum.subtract(expenseSum);
    }

    //updates methods
    public boolean updateIncome(Long id, String name, BigDecimal amount, Category category, String note) {
        Income income = incomesMap.get(id);
        if(income == null) {
            return false;
        }

        if(name != null) {
            income.setName(name);
        }
        if(amount != null) {
            income.setAmount(amount);
        }
        if(category != null) {
            income.setCategory(category);
        }
        if(note != null) {
            income.setNotes(note);
        }
        return true;
    }

    public boolean updateExpense(Long id, String name, BigDecimal amount, Category category, String note) {
        Expense expense = expenseMap.get(id);
        if(expense == null) {
            return false;
        }

        if(name != null) {
            expense.setName(name);
        }
        if(amount != null) {
            expense.setAmount(amount);
        }
        if(category != null) {
            expense.setCategory(category);
        }
        if(note != null) {
            expense.setNotes(note);
        }
        return true;
    }
}
