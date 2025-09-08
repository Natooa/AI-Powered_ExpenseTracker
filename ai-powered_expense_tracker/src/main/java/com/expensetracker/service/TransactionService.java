package com.expensetracker.service;

import com.expensetracker.entity.Category;
import com.expensetracker.entity.Transaction;

import java.math.BigDecimal;
import java.util.HashMap;

public interface TransactionService <T extends Transaction> {
    void addTransaction(T transaction);
    boolean removeTransactionById(Long id);
    boolean updateTransaction(Long id, String name, BigDecimal amount, Category category, String notes);
    HashMap<Long, T> getTransactionMap();
}
