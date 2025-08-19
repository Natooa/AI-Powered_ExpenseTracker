package com.expensetracker.controller;

import java.util.Scanner;

public class Controller {
    private final Scanner scanner = new Scanner(System.in);

    public void start() {
        System.out.println("hello consumer");
        while (true) {
            System.out.println("What do you want to do?");
            System.out.println("(Create Category first)");
            System.out.println("1. Add Income\n2. Remove Income\n3. Update Income\n4. Get balance\n5. Add Expense\n6. Remove Expense\n7. Update Expense\n8. Exit");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1" ->
            }
        }
    }

    private void addIncomeController() {
        System.out.print("Write name: ");
        String name = scanner.nextLine();
        System.out.print("\nWrite amount: ");
        String amount = scanner.nextLine();

    }
}
