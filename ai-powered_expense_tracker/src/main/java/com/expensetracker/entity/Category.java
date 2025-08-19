package com.expensetracker.entity;

import java.util.Objects;

public class Category {
    private long id;
    private String categoryName;
    private String categoryDescription;

    private static long nextId = 0;

    public Category(String categoryName, String categoryDescription) {
        if(categoryName == null || categoryName.trim().isEmpty()) {
            throw new IllegalArgumentException("Category Name cannot be null or empty");
        }
        if(categoryDescription == null || categoryDescription.trim().isEmpty()) {
            throw new IllegalArgumentException("Category Description cannot be null or empty");
        }
        this.categoryName = categoryName;
        this.categoryDescription = categoryDescription;
        this.id = nextId++;
    }

    //getters and setters

    public long getId() {
        return id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryDescription() {
        return categoryDescription;
    }

    public void setCategoryDescription(String categoryDescription) {
        this.categoryDescription = categoryDescription;
    }

    //to String

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", categoryName='" + categoryName + '\'' +
                ", categoryDescription='" + categoryDescription + '\'' +
                '}';
    }

    // equals and hashcode

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return id == category.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
