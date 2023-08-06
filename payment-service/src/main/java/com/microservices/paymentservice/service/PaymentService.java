package com.microservices.paymentservice.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.microservices.paymentservice.service.BankService;
import com.microservices.paymentservice.service.StockValidator;
import com.microservices.paymentservice.model.PaymentRequest;
import com.microservices.paymentservice.model.PaymentResponse;
import com.microservices.paymentservice.model.BankTransaction;
import com.microservices.paymentservice.model.Transaction;
import com.microservices.paymentservice.model.Stock;

@Service
public class PaymentService {

    @Autowired
    private BankService bankService;

    @Autowired
    private StockValidator stockValidator;

    private final Logger logger = LoggerFactory.getLogger(PaymentService.class);

    public PaymentResponse processPayment(PaymentRequest paymentRequest) {
        try {

            // Validate the stock details
            stockValidator.isValid(paymentRequest.getStocks());
            

            // Initiate a transaction with the bank
            BankTransaction bankTransaction = bankService.initiateTransaction();

            // Create a new transaction record
            Transaction transaction = new Transaction();
            transaction.setUserId(paymentRequest.getUserId());
            transaction.setTransactionDate(bankTransaction.getTransactionDate());
            transaction.setStatus(bankTransaction.getStatus());
            transaction.setTransactionId(bankTransaction.getTransactionId());
            double totalAmount=0;
            for(Stock stock : paymentRequest.getStocks()) {
                totalAmount=totalAmount+(stock.getPrice()*stock.getQuantity());
            }
            transaction.setAmount(totalAmount);

            //prepare a response
            PaymentResponse response = new PaymentResponse();
            response.setMessage("Payment processed successfully");
            response.setTransaction(transaction);
            response.setStocks(paymentRequest.getStocks());
            return response;
        } 
        
        catch (Exception e) {
            logger.error("Error processing payment : " + e.getMessage());
            PaymentResponse response = new PaymentResponse();
            response.setMessage("Error processing payment : " + e.getMessage());
            response.setTransaction(null);
            response.setStocks(paymentRequest.getStocks());
            return response;
        }
    }
}
