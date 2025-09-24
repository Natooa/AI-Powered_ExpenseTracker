package com.expensetracker.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;

public class Transaction {
    protected final long id;
    protected String name;
    protected BigDecimal amount;
    protected Category category;
    protected String notes;

    protected LocalDateTime createdAt;
    protected static final AtomicLong COUNTER_ID = new AtomicLong(1);

    public Transaction() {
        this.id = COUNTER_ID.getAndIncrement();
    }

    public Transaction(String name, BigDecimal amount, Category category, String notes) {
        if(name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        if(amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Amount must be greater than zero");
        }
        if(category == null) {
            throw new IllegalArgumentException("Category cannot be null");
        }

        this.id = COUNTER_ID.getAndIncrement();
        this.name = name;
        this.amount = amount;
        this.category = category;
        this.createdAt = LocalDateTime.now();
        this.notes = notes == null ? "" : notes;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    //to String

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", amount=" + amount +
                ", category=" + category +
                ", createdAt=" + createdAt +
                (notes != null ? ", notes=" +  notes + '\'' : "") +
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
