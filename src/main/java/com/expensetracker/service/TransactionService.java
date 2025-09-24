package com.expensetracker.service;

import com.expensetracker.entity.Category;
import com.expensetracker.entity.Income;
import com.expensetracker.entity.Transaction;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public interface TransactionService <T extends Transaction> {
    T getTransactionById(Long id);
    T addTransaction(T transaction);
    void removeTransactionById(Long id);
    T updateTransaction(Long id, T transactionToUpdate);
    Map<Long, T> getTransactionMap();
}
