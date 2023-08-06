package com.microservices.paymentservice.model;

import com.microservices.paymentservice.model.Transaction;

import java.util.List;

import com.microservices.paymentservice.model.Stock;

public class PaymentResponse {
    
    private String message;
    private Transaction transaction;
    private List<Stock> stocks;

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public List<Stock> getStocks() {
        return stocks;
    }

    public void setStocks(List<Stock> stocks) {
        this.stocks = stocks;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
