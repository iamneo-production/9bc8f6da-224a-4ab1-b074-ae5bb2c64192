package com.microservices.paymentservice.service;

import java.util.List;

import org.springframework.stereotype.Service;
import com.microservices.paymentservice.model.PaymentRequest;
import com.microservices.paymentservice.model.Stock;
import com.microservices.paymentservice.config.StockInformationFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import com.microservices.paymentservice.exception.NotEnoughStockException;

@Service
public class StockValidator{

    @Autowired
    private StockInformationFeignClient stockInformation;

    public void isValid(List<Stock> stocks) throws Exception {
        
        for (Stock stock : stocks) {
                
                Stock validStock=stockInformation.getStockDetails(stock.getSymbol());

                if (validStock == null) {
                    throw new IllegalArgumentException("provided stock symbol '"+stock.getSymbol()+"' is not available in our stock information");
                }
                 
                Boolean condition = stock.getId().equals(validStock.getId())
                                    && stock.getName().equals(validStock.getName())
                                    && Math.abs(stock.getPrice() - validStock.getPrice()) < 0.0001;

                if(!condition){
                    throw new IllegalArgumentException("Stock details of '"+stock.getName()+"' are invalid");
                }
                
                if(stock.getQuantity()>validStock.getQuantity()){
                    throw new NotEnoughStockException();
                }
        }
    }
}
