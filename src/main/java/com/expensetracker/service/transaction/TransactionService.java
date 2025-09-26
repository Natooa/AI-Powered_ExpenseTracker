package com.expensetracker.service.transaction;

import com.expensetracker.entity.Transaction;

import java.util.List;

public interface TransactionService <T extends Transaction> {
    T getTransactionById(Long id);
    T addTransaction(T transaction);
    void removeTransactionById(Long id);
    T updateTransaction(Long id, T transactionToUpdate);
    List<T> getAllTransactions();
}
