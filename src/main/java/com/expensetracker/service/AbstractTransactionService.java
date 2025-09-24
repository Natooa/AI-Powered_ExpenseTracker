package com.expensetracker.service;

import com.expensetracker.entity.Category;
import com.expensetracker.entity.Income;
import com.expensetracker.entity.Transaction;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

public class AbstractTransactionService <T extends Transaction> implements TransactionService<T> {
    private final HashMap<Long, T> transactionMap = new HashMap<>();

    @Override
    public T getTransactionById(Long id) {
        if(!transactionMap.containsKey(id)) {
            throw new NoSuchElementException("Transaction with id " + id + " does not exist");
        }
        return transactionMap.get(id);
    }

    @Override
    public Map<Long, T> getTransactionMap() {
        return Map.copyOf(transactionMap);
    }

    @Override
    public T addTransaction(T transaction) {
        transactionMap.put(transaction.getId(), transaction);
        return transaction;
    }

    @Override
    public void removeTransactionById(Long id) {
        if (!transactionMap.containsKey(id)) {
            throw new NoSuchElementException("Transaction with id " + id + " does not exist");
        }
        transactionMap.remove(id);
    }

    @Override
    public T updateTransaction(Long id, T transactionToUpdate) {
        if(!transactionMap.containsKey(id)) {
            throw new NoSuchElementException("Transaction with id " + id + " does not exist");
        }
        var transaction = transactionMap.get(id);

        if(transactionToUpdate.getName() != null) {
            transaction.setName(transactionToUpdate.getName());
        }
        if(transactionToUpdate.getAmount() != null) {
            transaction.setAmount(transactionToUpdate.getAmount());
        }
        if(transactionToUpdate.getCategory() != null) {
            transaction.setCategory(transactionToUpdate.getCategory());
        }
        if(transactionToUpdate.getNotes() != null) {
            transaction.setNotes(transactionToUpdate.getNotes());
        }
        return transaction;
    }
}
