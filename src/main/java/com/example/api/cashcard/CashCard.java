package com.example.api.cashcard;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class CashCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double amount;

    // ✅ JPA requires a no-arg constructor
    protected CashCard() {}

    public CashCard(Long id, Double amount) {
        this.id = id;
        this.amount = amount;
    }

    // ✅ Getters
    public Long getId() {
        return id;
    }

    public Double getAmount() {
        return amount;
    }
}
