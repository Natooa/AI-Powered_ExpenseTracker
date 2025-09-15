package com.expensetracker.service;

import com.expensetracker.entity.Category;
import com.expensetracker.entity.Income;
import com.expensetracker.entity.Transaction;

import java.math.BigDecimal;
import java.util.HashMap;

public class AbstractTransactionService <T extends Transaction> implements TransactionService<T> {
    private final HashMap<Long, T> transactionMap = new HashMap<>();

    @Override
    public void addTransaction(T transaction) {
        transactionMap.put(transaction.getId(), transaction);
    }

    @Override
    public boolean removeTransactionById(Long id) {
        if (transactionMap.containsKey(id)) {
            transactionMap.remove(id);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean updateTransaction(Long id, String name, BigDecimal amount, Category category, String note) {
        T transaction = transactionMap.get(id);
        if(transaction == null) {
            return false;
        }

        if(name != null) {
            transaction.setName(name);
        }
        if(amount != null) {
            transaction.setAmount(amount);
        }
        if(category != null) {
            transaction.setCategory(category);
        }
        if(note != null) {
            transaction.setNotes(note);
        }
        return true;
    }

    @Override
    public HashMap<Long, T> getTransactionMap() {
        return transactionMap;
    }
}
