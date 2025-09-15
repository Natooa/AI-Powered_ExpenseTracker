package com.expensetracker.controller;

import java.math.BigDecimal;
import java.util.Scanner;

import com.expensetracker.entity.Category;
import com.expensetracker.entity.Income;
import com.expensetracker.service.CategoryServiceImpl;
import com.expensetracker.service.IncomeServiceImpl;

public class IncomeTransactionController extends AbstractTransactionController<Income>{
    public IncomeTransactionController(
            Scanner scanner,
            CategoryController categoryController,
            CategoryServiceImpl categoryServiceImpl,
            IncomeServiceImpl incomeServiceImpl) {
        super(scanner, categoryController, categoryServiceImpl, incomeServiceImpl);
    }

    @Override
    protected Income createTransactiontObject(String name, BigDecimal amount, Category category, String note) {
        return new Income(name, amount, category, note);
    }
}
