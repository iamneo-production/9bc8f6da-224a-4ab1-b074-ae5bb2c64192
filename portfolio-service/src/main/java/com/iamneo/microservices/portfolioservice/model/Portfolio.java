package com.iamneo.microservices.portfolioservice.model;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Entity(name = "portfolio_detail")
@Getter
@Table(name = "portfolio_detail", schema = "portfolioDB")
public class Portfolio {
    
    @Column(name="id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name="customer_id")
    private Long customerId;

    @Column(name="stock_id")
    @NonNull
    private Long stockId;

    @Column(name="quantity")
    @NonNull
    private Integer quantity;

    public void addQuantity(Integer quantity) {
        this.quantity+= quantity;
    }

    public void reduceQuantity(Integer quantity){

        this.quantity -= quantity;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
}
