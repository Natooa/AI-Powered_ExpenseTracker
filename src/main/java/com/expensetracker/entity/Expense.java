package com.expensetracker.entity;

import java.math.BigDecimal;

public class Expense extends Transaction {
    public Expense() {
        super();
    }

    public Expense(String name, BigDecimal amount, Category category, String notes) {
        super(name, amount, category, notes);
    }
}
