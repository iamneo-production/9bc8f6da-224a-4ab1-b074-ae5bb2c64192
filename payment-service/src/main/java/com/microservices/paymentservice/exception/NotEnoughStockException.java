package com.microservices.paymentservice.exception;

public class NotEnoughStockException extends Exception{

    private static final String DEFAULT_MESSAGE="Provided quantity of stocks is exceeded than available. Please check and try again.";

    public NotEnoughStockException() {
        super(DEFAULT_MESSAGE);
    }
}