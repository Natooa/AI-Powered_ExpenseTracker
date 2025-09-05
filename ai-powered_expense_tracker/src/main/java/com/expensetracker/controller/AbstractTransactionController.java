package com.expensetracker.controller;

import com.expensetracker.entity.Category;
import com.expensetracker.entity.Income;
import com.expensetracker.service.CategoryService;
import com.expensetracker.service.TransactionService;

import java.math.BigDecimal;
import java.util.Scanner;

public abstract class AbstractTransactionController {
    protected final Scanner scanner;
    protected final CategoryService categoryService;
    protected final CategoryController categoryController;
    protected final TransactionService transactionService;

    public AbstractTransactionController(Scanner scanner, CategoryController categoryController, CategoryService categoryService, TransactionService transactionService) {
        this.scanner = scanner;
        this.categoryController = categoryController;
        this.categoryService = categoryService;
        this.transactionService = transactionService;
    }

    protected abstract void createTransaction();

    protected abstract void updateTransaction();

    protected abstract void deleteTransactionById();

    protected abstract void printMap();

    protected String askForNote(){
        System.out.println("Do you want to add note?(yes/no)");
        String answer = scanner.nextLine();
        if(answer.equalsIgnoreCase("yes")){
            System.out.println("Write note: ");
            return  scanner.nextLine();
        }
        return null;
    }
}
