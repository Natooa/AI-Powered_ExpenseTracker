package com.expensetracker.entity;

import java.math.BigDecimal;

public class Expense extends Transaction {
    public Expense(String name, BigDecimal amount, Category category) {
        super(name, amount, category );
    }
}
