package com.expensetracker.features.transaction.base;

import jakarta.persistence.EntityNotFoundException;

import java.util.*;

public class AbstractTransactionService <T extends Transaction> implements TransactionService<T> {
    private final TransactionRepository<T> transactionRepository;

    public  AbstractTransactionService(TransactionRepository<T> transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public T getTransactionById(Long id) {
        return transactionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Transaction with id " + id + " not found"));
    }

    @Override
    public List<T> getAllTransactions() {
        return transactionRepository.findAll();
    }

    @Override
    public T addTransaction(T transactionToCreate) {
        if(transactionToCreate.getId() != null) {
            throw new IllegalArgumentException("Transaction with id " + transactionToCreate.getId() + " already exists");
        }
        return transactionRepository.save(transactionToCreate);
    }

    @Override
    public void removeTransactionById(Long id) {
        if(!transactionRepository.existsById(id)) {
            throw new EntityNotFoundException("Transaction with id " + id + " not found");
        }
        transactionRepository.deleteById(id);
    }

    @Override
    public T updateTransaction(Long id, T transactionToUpdate) {
        T existing = transactionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Transaction with id " + id + " not found"));

        if(transactionToUpdate.getName() != null) {
            existing.setName(transactionToUpdate.getName());
        }
        if(transactionToUpdate.getAmount() != null) {
            existing.setAmount(transactionToUpdate.getAmount());
        }
        if(transactionToUpdate.getCategory() != null) {
            existing.setCategory(transactionToUpdate.getCategory());
        }
        if(transactionToUpdate.getNotes() != null) {
            existing.setNotes(transactionToUpdate.getNotes());
        }
        return transactionRepository.save(existing);
    }
}
