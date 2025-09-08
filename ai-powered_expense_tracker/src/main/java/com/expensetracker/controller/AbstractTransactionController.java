package com.expensetracker.controller;

import com.expensetracker.entity.Category;
import com.expensetracker.entity.Transaction;
import com.expensetracker.service.CategoryServiceImpl;
import com.expensetracker.service.TransactionService;

import java.math.BigDecimal;
import java.util.Scanner;

public abstract class AbstractTransactionController <T extends Transaction> {
    protected final Scanner scanner;
    protected final CategoryServiceImpl categoryServiceImpl;
    protected final CategoryController categoryController;
    protected final TransactionService<T> transactionService;

    public AbstractTransactionController(Scanner scanner, CategoryController categoryController, CategoryServiceImpl categoryServiceImpl,  TransactionService<T> transactionService) {
        this.scanner = scanner;
        this.categoryController = categoryController;
        this.categoryServiceImpl = categoryServiceImpl;
        this.transactionService = transactionService;
    }

    protected abstract T createTransactiontObject(String name, BigDecimal amount, Category category, String note);

    protected void createTransaction(){
        String name = askForName();
        BigDecimal amount = askForAmount();
        Category category = askForCategory();
        String note = askForNote();

        T transaction = createTransactiontObject(name, amount, category, note);
        transactionService.addTransaction(transaction);
    }

    protected void deleteTransactionById(){
        printMap();
        Long id = askForIdTransaction();
        boolean removeDo = transactionService.removeTransactionById(id);
        if(removeDo) {
            System.out.println("Transaction has been removed successfully.");
        } else {
            System.out.println("Transaction id not found.");
        }
    }

    protected void updateTransaction(){
        Long id = askForIdTransaction();

        String name = askForUpdate("name");
        BigDecimal amount = askForAmountUpdate();
        Category category = askForCategory();
        String note = askForUpdate("note");

        boolean update = transactionService.updateTransaction(id, name, amount, category, note);
        if(update) {
            System.out.println("Transaction updated!");
        } else{
            System.out.println("Transaction with id " + id + " not found!");
        }
    }

    protected void printMap(){
        transactionService.getTransactionMap().forEach((key, value) ->
                System.out.println(key + ": " + value)
        );
    }

    protected String askForUpdate(String field) {
        System.out.printf("Do you want to change %s?(yes/no)%n", field);
        if(scanner.nextLine().equalsIgnoreCase("yes")) {
            System.out.println("New %s: " + field);
            return scanner.nextLine();
        }
        return null;
    }

    protected BigDecimal askForAmountUpdate() {
        System.out.println("Do you want to change Amount?(yes/no)");
        if(scanner.nextLine().equalsIgnoreCase("yes")) {
            System.out.print("New Amount: ");
            return new BigDecimal(scanner.nextLine());
        }
        return null;
    }

    protected Category askForCategoryUpdate() {
        System.out.println("Do you want to change Category?(yes/no)");
        if(scanner.nextLine().equalsIgnoreCase("yes")) {
            return categoryController.addCategoryOrCreateNew();
        }
        return null;
    }

    protected Long askForIdTransaction(){
        System.out.println("Enter transaction id: ");
        String transactionId = scanner.nextLine();
        return Long.valueOf(transactionId);
    }

    protected String askForName() {
        System.out.print("Write name: ");
        return scanner.nextLine();
    }

    protected BigDecimal askForAmount() {
        System.out.print("\nWrite amount: ");
        String bigDecimalAmount = scanner.nextLine();
        return new BigDecimal(bigDecimalAmount);
    }

    protected Category askForCategory() {
        return categoryController.addCategoryOrCreateNew();
    }

    protected String askForNote() {
        System.out.println("Do you want to add note?(yes/no)");
        String answer = scanner.nextLine();
        if (answer.equalsIgnoreCase("yes")) {
            System.out.println("Write note: ");
            return scanner.nextLine();
        }
        return null;
    }

}
