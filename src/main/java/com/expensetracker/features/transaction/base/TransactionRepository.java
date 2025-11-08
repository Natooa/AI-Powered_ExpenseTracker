package com.expensetracker.features.transaction.base;

import com.expensetracker.features.users.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;
import java.util.Optional;


@NoRepositoryBean
public interface TransactionRepository<T extends Transaction> extends JpaRepository<T, Long> {
    @Query("SELECT t FROM Transaction t WHERE t.user = :user ORDER BY t.createdAt DESC")
    List<T> findAllByUser(Users user);
    void removeTransactionByIdAndUser(Long id,  Users user);
    Optional<T> getTransactionByIdAndUser(Long id, Users user);
}
