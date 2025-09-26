package com.expensetracker.entity;

import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
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
