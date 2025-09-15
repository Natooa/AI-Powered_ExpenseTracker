package com.expensetracker.controller;

import com.expensetracker.entity.Category;
import com.expensetracker.service.CategoryServiceImpl;

import java.util.Scanner;

public class CategoryController {
    private final Scanner scanner;
    private final CategoryServiceImpl categoryServiceImpl;

    public CategoryController(Scanner scanner, CategoryServiceImpl categoryServiceImpl) {
        this.scanner = scanner;
        this.categoryServiceImpl = categoryServiceImpl;
    }

    public void addCategory() {
        System.out.print("Adding new category\nPlease enter the category name?");
        String categoryName = scanner.nextLine();
        System.out.print("Please enter the category description: ");
        String categoryDescription = scanner.nextLine();

        Category category = new Category(categoryName, categoryDescription);
        categoryServiceImpl.addCategory(category);
    }

    public void removeCategoryById() {
        System.out.println("Which id do you want to remove?");
        categoryServiceImpl.printCategoryMap();
        System.out.println("Please enter the id of the category you want to remove");
        String categoryId = scanner.nextLine();
        boolean removeDo = categoryServiceImpl.removeCategoryById(Long.parseLong(categoryId));
        if(removeDo) {
            System.out.println("Category removed successfully");
        } else {
            System.out.println("Category id not found");
        }
    }

    public void printCategoryMap() {
        categoryServiceImpl.printCategoryMap();
    }

    public Category addCategoryOrCreateNew() {
        System.out.println("Do you want to add new category? Or add to exist category?");
        String yesOrNo = scanner.nextLine();

        if(yesOrNo.equalsIgnoreCase("yes")) {
            addCategory();
            printCategoryMap();
            System.out.println("write id: ");
            String id = scanner.nextLine();
            return categoryServiceImpl.getCategoryById(Long.parseLong(id));
        } else if(yesOrNo.equalsIgnoreCase("no")) {
            System.out.println("Write id in witch Category you want do add");
            printCategoryMap();
            String id = scanner.nextLine();
            return categoryServiceImpl.getCategoryById(Long.parseLong(id));
        }
        return null;
    }
}
