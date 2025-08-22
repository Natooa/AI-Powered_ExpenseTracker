package com.expensetracker.controller;

public class ControllerOfAllApp {
    private final TransactionController transactionController = new TransactionController();

    public void start() {
        System.out.println("hello consumer");
        while (true) {
            System.out.println("What do you want to do?");
            System.out.println("(Create Category first)");
            System.out.println("1. Add Income\n2. Remove Income\n3. Update Income\n4. Get balance\n5. Add Expense\n6. Remove Expense\n7. Update Expense\n8. Exit");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1" -> transactionController.addIncomeTrasaction();
                case "2" ->
            }
        }
    }
}
