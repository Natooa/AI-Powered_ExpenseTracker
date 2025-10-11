package com.expensetracker.features.transaction.income;

import com.expensetracker.features.category.Category;
import com.expensetracker.features.transaction.base.Transaction;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Entity
@DiscriminatorValue("INCOME")
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
public class Income extends Transaction {
    public Income(String name, BigDecimal amount, Category category, String notes) {
        super(name, amount, category, notes);
    }
}
