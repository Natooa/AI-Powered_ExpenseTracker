package com.expensetracker.controller;

import com.expensetracker.entity.Category;
import com.expensetracker.entity.Expense;
import com.expensetracker.entity.Income;
import com.expensetracker.service.CategoryService;
import com.expensetracker.service.TransactionService;

import java.math.BigDecimal;
import java.util.Scanner;

public class ExpenseTransactionController extends AbstractTransactionController{
    public ExpenseTransactionController(Scanner scanner, CategoryController categoryController, CategoryService categoryService, TransactionService transactionService) {
        super(scanner, categoryController, categoryService, transactionService);
    }

    @Override
    protected void createTransaction() {
        System.out.print("Write name: ");
        String name = scanner.nextLine();
        System.out.print("\nWrite amount: ");
        String amount = scanner.nextLine();

        Category category = categoryController.addCategoryOrCreateNew();

        System.out.println("Do you want to add note?");
        String yesNo = scanner.nextLine();
        String note = askForNote();
        BigDecimal bigDecimal = new BigDecimal(amount);

        Expense expense = new Expense(name, bigDecimal, category, note);
        transactionService.addExpense(expense);
    }

    @Override
    protected void deleteTransactionById() {
        printMap();
        System.out.println("Write id of expense: ");
        String id = scanner.nextLine();
        boolean removeDo = transactionService.removeExpenseById(Long.parseLong(id));
        if(removeDo) {
            System.out.println("Expense has been removed successfully.");
        } else {
            System.out.println("Expense id not found.");
        }
    }

    @Override
    protected void printMap() {
        transactionService.getExpenseMap().entrySet().stream().forEach(entry -> {
            System.out.println("Expense id: " + entry.getKey() + " " + entry.getValue());
        });
    }

    @Override
    public void updateTransaction() {
        System.out.println("Write id of Expense: ");
        Long id = Long.parseLong(scanner.nextLine());
        System.out.println("Do you want to change name?(yes/no)");
        String name = null;
        if(scanner.nextLine().equalsIgnoreCase("yes")) {
            System.out.println("New name: ");
            name = scanner.nextLine();
        }

        BigDecimal amount = null;
        System.out.println("Do you want to change amount?(yes/no)");
        if(scanner.nextLine().equalsIgnoreCase("yes")) {
            System.out.println("New amount: ");
            amount = new BigDecimal(scanner.nextLine());
        }

        Category category = null;
        System.out.println("Do you want to change category?(yes/no)");
        if(scanner.nextLine().equalsIgnoreCase("yes")) {
            System.out.println("New category: ");
            category = categoryController.addCategoryOrCreateNew();
        }

        String note = null;
        System.out.println("Do you want to change note?(yes/no)");
        if(scanner.nextLine().equalsIgnoreCase("yes")) {
            System.out.println("New note: ");
            note = scanner.nextLine();
        }

        boolean update = transactionService.updateExpense(id, name, amount, category, note);
        if(update) {
            System.out.println("Income updated!");
        } else{
            System.out.println("Income with id " + id + " not found!");
        }
    }
}
