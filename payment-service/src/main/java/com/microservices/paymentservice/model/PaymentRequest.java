package com.microservices.paymentservice.model;

import com.microservices.paymentservice.model.Stock;
import java.util.List;

public class PaymentRequest {
    
    private String userId;
    private List<Stock> stocks;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<Stock> getStocks() {
        return stocks;
    }

    public void setStocks(List<Stock> stocks) {
        this.stocks = stocks;
    }
}
