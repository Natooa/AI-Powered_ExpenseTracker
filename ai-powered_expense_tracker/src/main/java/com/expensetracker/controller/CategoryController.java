package com.expensetracker.controller;

import com.expensetracker.entity.Category;
import com.expensetracker.service.CategoryService;

import java.util.Scanner;

public class CategoryController {
    private final Scanner scanner;
    private final CategoryService categoryService;

    public CategoryController(Scanner scanner, CategoryService categoryService) {
        this.scanner = scanner;
        this.categoryService = categoryService;
    }

    public void addCategory() {
        System.out.print("Adding new category\nPlease enter the category name?");
        String categoryName = scanner.nextLine();
        System.out.print("Please enter the category description: ");
        String categoryDescription = scanner.nextLine();

        Category category = new Category(categoryName, categoryDescription);
        categoryService.addCategory(category);
    }

    public void removeCategoryById() {
        System.out.println("Which id do you want to remove?");
        categoryService.printCategoryMap();
        System.out.println("Please enter the id of the category you want to remove");
        String categoryId = scanner.nextLine();
        boolean removeDo = categoryService.removeCategoryById(Long.parseLong(categoryId));
        if(removeDo) {
            System.out.println("Category removed successfully");
        } else {
            System.out.println("Category id not found");
        }
    }

    public void printCategoryMap() {
        categoryService.printCategoryMap();
    }

    public Category addCategoryOrCreateNew() {
        System.out.println("Do you want to add new category? Or add to exist category?");
        String yesOrNo = scanner.nextLine();

        if(yesOrNo.equalsIgnoreCase("yes")) {
            addCategory();
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
