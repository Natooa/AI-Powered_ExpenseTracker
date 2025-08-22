package com.expensetracker.controller;

import java.util.Scanner;

import com.expensetracker.controller.CategoryContoller;
import com.expensetracker.service.CategoryService;

public class TransactionController {
    private CategoryContoller categoryContoller = new CategoryContoller();

    private final Scanner scanner = new Scanner(System.in);

    public void addIncomeTrasaction() {
        System.out.print("Write name: ");
        String name = scanner.nextLine();
        System.out.print("\nWrite amount: ");
        String amount = scanner.nextLine();
        String category = scanner.nextLine();
        categoryContoller.printCategoryMap();
        System.out.println("Write id in witch Category fo you want do add");
        String id = scanner.nextLine();
    }
}
