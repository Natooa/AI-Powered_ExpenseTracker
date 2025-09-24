package com.expensetracker.entity;

import java.math.BigDecimal;

public class Income extends Transaction {
    public Income() {
        super();
    }

    public Income(String name, BigDecimal amount, Category category, String notes) {
        super(name, amount, category, notes);
    }
}
