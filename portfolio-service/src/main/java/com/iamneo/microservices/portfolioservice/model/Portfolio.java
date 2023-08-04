package com.iamneo.microservices.portfolioservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import lombok.Getter;

@Entity
@Getter
public class Portfolio {
    
    @Column(name="id")
    @Id
    private long id;
    
    @Column(name="customer_id")
    private long customerId;

    @Column(name="stock_id")
    private long stockId;

    @Column(name="quantity")
    private int quantity;
}
