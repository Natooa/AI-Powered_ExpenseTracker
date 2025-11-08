package com.expensetracker.features.transaction.base;

import com.expensetracker.features.users.Users;
import com.expensetracker.features.users.UsersRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.*;

public class AbstractTransactionService <T extends Transaction> implements TransactionService<T> {
    private final TransactionRepository<T> transactionRepository;
    private final UsersRepository usersRepository;

    public  AbstractTransactionService(TransactionRepository<T> transactionRepository,  UsersRepository usersRepository) {
        this.transactionRepository = transactionRepository;
        this.usersRepository = usersRepository;
    }

    @Override
    public T getTransactionById(Long id) {
        Users currentUser = getCurrentUser();
        return transactionRepository.getTransactionByIdAndUser(id,  currentUser)
                .orElseThrow(() -> new EntityNotFoundException("Transaction with id " + id + " not found"));
    }

    @Override
    public List<T> getAllTransactions() {
        Users currentUser = getCurrentUser();
        return transactionRepository.findAllByUserOrderByCreatedAtDesc(currentUser);
    }

    @Override
    public T addTransaction(T transactionToCreate) {
        Users currentUser = getCurrentUser();
        if(transactionToCreate.getId() != null) {
            throw new IllegalArgumentException("Transaction with id " + transactionToCreate.getId() + " already exists");
        }
        transactionToCreate.setUser(currentUser);
        return transactionRepository.save(transactionToCreate);
    }

    @Override
    public void removeTransactionById(Long id) {
        Users currentUser = getCurrentUser();
        if(!transactionRepository.existsById(id)) {
            throw new EntityNotFoundException("Transaction with id " + id + " not found");
        }
        transactionRepository.removeTransactionByIdAndUser(id, currentUser);
    }

    @Override
    public T updateTransaction(Long id, T transactionToUpdate) {
        Users currentUser = getCurrentUser();
        T existing = transactionRepository.getTransactionByIdAndUser(id, currentUser)
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

    protected Users getCurrentUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return usersRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username " + username + " not found"));
    }
}
