package com.expensetracker.controller;

import java.math.BigDecimal;
import java.util.Scanner;

import com.expensetracker.controller.CategoryContoller;
import com.expensetracker.entity.Category;
import com.expensetracker.entity.Income;
import com.expensetracker.service.CategoryService;
import com.expensetracker.service.TransactionService;

public class TransactionController {
    private final CategoryContoller categoryController = new CategoryContoller();
    private final CategoryService categoryService = new CategoryService();
    private final TransactionService transactionService = new TransactionService();

    private final Scanner scanner = new Scanner(System.in);

    public void addIncomeTrasaction() {
        System.out.print("Write name: ");
        String name = scanner.nextLine();
        System.out.print("\nWrite amount: ");
        String amount = scanner.nextLine();

        Category category = categoryController.addCaegoryOrCreateNew();

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

}
