package com.expensetracker.controller;

import com.expensetracker.service.TransactionService;

import java.util.Scanner;

public class ControllerOfAllApp {
    Scanner scanner = new Scanner(System.in);
    private final TransactionController transactionController = new TransactionController();
    private final TransactionService transactionService = new TransactionService();

    public void start() {
        System.out.println("hello consumer");
        while (true) {
            System.out.println("What do you want to do?");
            System.out.println("(Create Category first)");
            System.out.println("1. Add Income\n2. Remove Income\n3. Update Income\n4. Get balance\n5. Add Expense\n6. Remove Expense\n7. Update Expense\n8. Exit\n9. print All Income");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1" -> transactionController.addIncomeTrasaction();
                case "2" -> transactionController.removeIncomeTrasactionById();
                case "3" -> {
                    System.out.println("Write id of income to update");
                    String id = scanner.nextLine();
                    transactionService.updateTransactionById(Long.parseLong(id));
                }
                case "4" -> transactionController.getBalance();
                case "9" -> transactionController.printIncomeMap();
                default -> {
                    System.out.println("Wrong choice");
                    System.exit(0);
                }
            }
        }
    }
}
