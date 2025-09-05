package com.expensetracker.controller;

import java.math.BigDecimal;
import java.util.Scanner;

import com.expensetracker.controller.CategoryContoller;
import com.expensetracker.entity.Category;
import com.expensetracker.entity.Income;
import com.expensetracker.service.CategoryService;
import com.expensetracker.service.TransactionService;

public class TransactionController {
    private final CategoryService categoryService;
    private final CategoryContoller categoryContoller;
    private final TransactionService transactionService;
    private final Scanner scanner;

    public TransactionController(Scanner scanner, CategoryContoller categoryContoller, CategoryService categoryService, TransactionService transactionService) {
        this.scanner = scanner;
        this.categoryContoller = categoryContoller;
        this.categoryService = categoryService;
        this.transactionService = transactionService;
    }

    public void addIncomeTrasaction() {
        System.out.print("Write name: ");
        String name = scanner.nextLine();
        System.out.print("\nWrite amount: ");
        String amount = scanner.nextLine();

        Category category = categoryContoller.addCaegoryOrCreateNew();

        System.out.println("Do you want to add note?");
        String yesNo = scanner.nextLine();
        String note = null;
        if(yesNo.equalsIgnoreCase("yes")) {
            System.out.println("Write note: ");
            note = scanner.nextLine();
        } else {
            note = null;
        }


        BigDecimal bigDecimal = new BigDecimal(amount);

        Income income = new Income(name, bigDecimal, category, note);
        transactionService.addIncome(income);
    }

    public void removeIncomeTrasactionById() {
        printIncomeMap();
        System.out.println("Write id of income: ");
        String id = scanner.nextLine();
        transactionService.removeIncomeById(Long.parseLong(id));
    }

    public void printIncomeMap() {
        transactionService.getIncomesMap().entrySet().stream().forEach(entry -> {
            System.out.println("Income id: " + entry.getKey() + " " + entry.getValue());
        });
    }

    public void addExpenseTrasaction() {
        System.out.print("Write name: ");
        String name = scanner.nextLine();
    }

    public void removeExpenseTrasactionById() {
        printExpenseMap();
    }

    public void printExpenseMap() {
        transactionService.getExpenseMap().entrySet().stream().forEach(entry -> {
            System.out.println("Expense id: " + entry.getKey() + " " + entry.getValue());
        });
    }

    public void getBalance() {
        System.out.println("Balance: " + transactionService.getBalance());
    }


    public void updateIncomeTransaction() {
        System.out.println("Write id of income: ");
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
            category = categoryContoller.addCaegoryOrCreateNew();
        }

        String note = null;
        System.out.println("Do you want to change note?(yes/no)");
        if(scanner.nextLine().equalsIgnoreCase("yes")) {
            System.out.println("New note: ");
            note = scanner.nextLine();
        }

        boolean update = transactionService.updateIncome(id, name, amount, category, note);
        if(update) {
            System.out.println("Income updated!");
        } else{
            System.out.println("Income with id " + id + " not found!");
        }
    }
}
