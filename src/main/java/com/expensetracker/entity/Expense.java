package com.expensetracker.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Entity
@DiscriminatorValue("EXPENSE")
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
public class Expense extends Transaction {
    public Expense(String name, BigDecimal amount, Category category, String notes) {
        super(name, amount, category, notes);
    }
}
