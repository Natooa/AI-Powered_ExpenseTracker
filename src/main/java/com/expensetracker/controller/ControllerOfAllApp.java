package com.expensetracker.controller;

import com.expensetracker.service.CategoryServiceImpl;
import com.expensetracker.service.ExpenseServiceImpl;
import com.expensetracker.service.IncomeServiceImpl;
import com.expensetracker.service.BalanceServiceImpl;

import java.util.Scanner;

public class ControllerOfAllApp {
    private final Scanner scanner = new Scanner(System.in);
    private final CategoryController categoryContoller;
    private final IncomeTransactionController incomeTransactionController;
    private final ExpenseTransactionController expenseTransactionController;

    private final CategoryServiceImpl categoryServiceImpl = new CategoryServiceImpl();
    private final IncomeServiceImpl incomeServiceImpl = new IncomeServiceImpl();
    private final ExpenseServiceImpl expenseServiceImpl = new ExpenseServiceImpl();
    private final BalanceServiceImpl balanceServiceImpl;

    public ControllerOfAllApp() {
        this.categoryContoller = new CategoryController(scanner, categoryServiceImpl);
        this.incomeTransactionController = new IncomeTransactionController(scanner, categoryContoller, categoryServiceImpl, incomeServiceImpl);
        this.expenseTransactionController = new ExpenseTransactionController(scanner, categoryContoller, categoryServiceImpl, expenseServiceImpl);
        this.balanceServiceImpl = new BalanceServiceImpl(incomeServiceImpl, expenseServiceImpl);
    }

    public void start() {
        System.out.println("hello consumer");
        while (true) {
            System.out.println("What do you want to do?");
            System.out.println("(Create Category first)");
            System.out.println(
                    "1. Add Income\n" +
                            "2. Remove Income\n" +
                            "3. Update Income\n" +
                            "4. Print All Income\n" +
                            "5. Add Expense\n" +
                            "6. Remove Expense\n" +
                            "7. Update Expense\n" +
                            "8. Print All Expense\n" +
                            "9. Get balance\n" +
                            "10. Exit"
            );
            String choice = scanner.nextLine();
            switch (choice) {
                case "1" -> incomeTransactionController.createTransaction();
                case "2" -> incomeTransactionController.deleteTransactionById();
                case "3" -> {
                    incomeTransactionController.printMap();
                    incomeTransactionController.updateTransaction();
                }
                case "4" -> incomeTransactionController.printMap();
                case "5" -> expenseTransactionController.createTransaction();
                case "6" -> expenseTransactionController.deleteTransactionById();
                case "7" -> expenseTransactionController.updateTransaction();
                case "8" -> expenseTransactionController.printMap();
                case "9" -> System.out.println("Balance: " + balanceServiceImpl.getBalance());
                case "10" -> {
                    System.out.println("Exiting...");
                    System.exit(0);
                }
                default -> {
                    System.out.println("Wrong choice");
                    System.exit(0);
                }
            }
        }
    }
}
