package com.expensetracker.features.transaction.base;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransactionDTO {
    private String name;
    private BigDecimal amount;
    private Long categoryId;
    private String notes;
}
