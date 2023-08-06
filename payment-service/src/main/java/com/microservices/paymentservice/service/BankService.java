package com.microservices.paymentservice.service;

import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.UUID;
import com.microservices.paymentservice.model.BankTransaction;

@Service
public class BankService {

    public BankTransaction initiateTransaction() {
        BankTransaction bankTransaction = new BankTransaction();

        bankTransaction.setTransactionId(UUID.randomUUID().toString());
        bankTransaction.setTransactionDate(new Date());

        // For simplicity, let's assume that all transactions are successful
        bankTransaction.setStatus("SUCCESS");

        return bankTransaction;
    }
}
