package com.expensetracker.controller;

import com.expensetracker.entity.Category;
import com.expensetracker.entity.Expense;
import com.expensetracker.service.CategoryServiceImpl;
import com.expensetracker.service.ExpenseServiceImpl;

import java.math.BigDecimal;
import java.util.Scanner;

public class ExpenseTransactionController extends AbstractTransactionController<Expense> {
    public ExpenseTransactionController(
            Scanner scanner,
            CategoryController categoryController,
            CategoryServiceImpl categoryServiceImpl,
            ExpenseServiceImpl expenseServiceImpl) {
        super(scanner, categoryController, categoryServiceImpl, expenseServiceImpl);
    }

    @Override
    protected Expense createTransactiontObject(String name, BigDecimal amount, Category category, String note) {
        return new Expense(name, amount, category, note);
    }
}
