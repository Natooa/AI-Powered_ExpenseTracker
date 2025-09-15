package com.expensetracker.service;

import com.expensetracker.entity.Category;

import java.util.HashMap;

public class CategoryServiceImpl implements CategoryService {
    private final HashMap<Long, Category> categoryMap = new HashMap<>();

//    public HashMap<Long, Category> getCategoryMap() {
//        return categoryMap;
//    }

    @Override
    public void addCategory(Category category) {
        categoryMap.put(category.getId(), category);
    }

    @Override
    public boolean removeCategoryById(Long id) {
        if(categoryMap.containsKey(id)) {
            categoryMap.remove(id);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Category getCategoryById(Long id) {
        return categoryMap.get(id);
    }

    @Override
    public void printCategoryMap() {
        categoryMap.entrySet().stream().forEach(entry -> {
            System.out.println(entry.getKey() + " = " +  entry.getValue());
        });
    }
}
