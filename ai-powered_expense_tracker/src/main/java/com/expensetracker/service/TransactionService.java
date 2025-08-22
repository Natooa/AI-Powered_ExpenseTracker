package com.expensetracker.service;

import com.expensetracker.entity.Category;
import com.expensetracker.entity.Expense;
import com.expensetracker.entity.Income;
import com.expensetracker.entity.Transaction;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Scanner;

public class TransactionService {
    private final Scanner scanner = new Scanner(System.in);

    private final HashMap<Long, Income> incomesMap = new HashMap<>();
    private final HashMap<Long, Expense> expenseMap = new HashMap<>();

    public HashMap<Long, Income> getIncomesMap() {
        return incomesMap;
    }

    public HashMap<Long, Expense> getExpenseMap() {
        return expenseMap;
    }
}

    //add and remove methods for each Map
    public void addIncome(Income income) {
        incomesMap.put(income.getId(), income);
    }

    public void addExpense(Expense expense) {
        expenseMap.put(expense.getId(),  expense);
    }

    public void removeIncomeById(Long id) {
        if (incomesMap.containsKey(id)) {
            incomesMap.remove(id);
        } else {
            System.out.println("There is no such income with id " + id);
        }
    }

    public void removeExpenseById(Long id) {
        if(expenseMap.containsKey(id)) {
            expenseMap.remove(id);
        } else {
            System.out.println("There is no such expense with id " + id);
        }
    }

    public void removeCategoryById(Long id) {
        if(categoryMap.containsKey(id)) {
            categoryMap.remove(id);
        } else {
            System.out.println("There is no such category with id " + id);
        }
    }

    //getters Methods
    public BigDecimal getBalance() {
        BigDecimal incomeSum = incomesMap.values().stream().map(Income::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal expenseSum = expenseMap.values().stream().map(Expense::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return incomeSum.subtract(expenseSum);
    }

    //updates methods
    public Transaction updateTransactionById(Long id) {
        Transaction transaction = null;
        if(expenseMap.containsKey(id)) {
            Expense expense = expenseMap.get(id);
            transaction = expense;
        } else if(incomesMap.containsKey(id)) {
            Income income = incomesMap.get(id);
            transaction = income;
        }

        if(transaction == null) {
            System.out.println("There is no such transaction with id " + id);
            return null;
        }

        System.out.println("transaction found" + transaction);

        boolean exit = false;
        while(!exit) {
            System.out.println("Updating transaction with id " + id + "\nwWhat do you want to update?"
                    + "\n1. Name\n2. Amount\n3. Category\n4. Notes\n5. Exit");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1" -> {
                    System.out.print("Please enter the name: ");
                    String name = scanner.nextLine();
                    transaction.setName(name);
                }
                case "2" -> {
                    System.out.print("Please enter the amount: ");
                    BigDecimal amount = new BigDecimal(scanner.nextLine());
                    transaction.setAmount(amount);
                }
                case "3" -> {
                    System.out.print("Please enter the category: ");
                    System.out.println("Sorry this function no working now");
                } case "4" -> {
                    System.out.print("Please enter the notes: ");
                    String notes = scanner.nextLine();
                    transaction.setNotes(notes);
                }
                case "5" -> {
                    System.out.println("Exiting...");
                    exit = true;
                }
                default -> {
                    System.out.println("There is no such transaction with id " + id);
                }
        }
    }
}
