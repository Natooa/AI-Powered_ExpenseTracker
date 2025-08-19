package com.expensetracker.entity;

import java.math.BigDecimal;
import java.util.Objects;

public class Transaction {
    protected long id;
    protected String name;
    protected BigDecimal amount;
    protected Category category;

    protected static long nextId = 0;

    public Transaction(String name, BigDecimal amount, Category category) {
        if(name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        if(amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Amount cannot be negative");
        }
        if(category == null) {
            throw new IllegalArgumentException("Category cannot be null");
        }

        this.id = nextId++;
        this.name = name;
        this.amount = amount;
        this.category = category;
    }

    //getters and setters

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    //to String

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", amount=" + amount +
                ", category=" + category +
                '}';
    }


    //equals and hashcode

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
