package com.expensetracker.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "transaction_type",  discriminatorType = DiscriminatorType.STRING)
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column(name = "id")
    protected Long id;

    @Column(name = "name",nullable = false)
    protected String name;

    @Column(name = "amount", nullable = false, precision = 16, scale = 2)
    protected BigDecimal amount;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id", nullable = false)
    protected Category category;

    @Column(name = "notes", length = 500)
    protected String notes;

    @Column(name = "createdAt",nullable = false, updatable = false)
    protected LocalDateTime createdAt;

    @PrePersist
    public void timeOnCreate() {
        this.createdAt = LocalDateTime.now();
    }

    public Transaction(String name, BigDecimal amount, Category category, String notes) {
        this.name = name;
        this.amount = amount;
        this.category = category;
        this.notes = notes;
    }

}
