package com.expensetracker.entity;

import java.math.BigDecimal;

public class Income extends Transaction {
    public Income(String name, BigDecimal amount, Category category) {
        super(name, amount, category );
    }
}
