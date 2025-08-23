package com.expensetracker.controller;

import com.expensetracker.entity.Category;
import com.expensetracker.service.CategoryService;

import java.util.Scanner;

public class CategoryContoller {
    private final Scanner scanner = new Scanner(System.in);
    private final CategoryService categoryService = new CategoryService();

    public void addCategoryController() {
        System.out.print("Adding new category\nPlease enter the category name?");
        String categoryName = scanner.nextLine();
        System.out.print("Please enter the category description: ");
        String categoryDescription = scanner.nextLine();

        Category category = new Category(categoryName, categoryDescription);
        categoryService.addCategory(category);
    }

    public void removeCategoryByIdController() {
        System.out.println("Which id do you want to remove?");
        categoryService.printCategoryMap();
        System.out.println("Please enter the id of the category you want to remove");
        String categoryId = scanner.nextLine();
        categoryService.removeCategoryById(Long.parseLong(categoryId));
    }

    public void printCategoryMap() {
        categoryService.printCategoryMap();
    }

    public Category addCaegoryOrCreateNew() {
        System.out.println("Do you want to add new category? Or add to exist category?");
        String yesOrNo = scanner.nextLine();

        if(yesOrNo.equalsIgnoreCase("yes")) {
            addCategoryController();
            printCategoryMap();
            System.out.println("write id: ");
            String id = scanner.nextLine();
            return categoryService.getCategoryById(Long.parseLong(id));
        } else if(yesOrNo.equalsIgnoreCase("no")) {
            System.out.println("Write id in witch Category you want do add");
            printCategoryMap();
            String id = scanner.nextLine();
            return categoryService.getCategoryById(Long.parseLong(id));
        }
        return null;
    }
}
