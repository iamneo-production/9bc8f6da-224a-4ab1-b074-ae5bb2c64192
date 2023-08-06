package com.microservices.paymentservice.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.microservices.paymentservice.service.PaymentService;
import com.microservices.paymentservice.model.PaymentRequest;
import com.microservices.paymentservice.model.PaymentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;


@RestController
@RequestMapping("/payment")
public class PaymentController {

     @Autowired
     private PaymentService paymentservice;

     @PostMapping("/buy")
     @CircuitBreaker(name = "default",fallbackMethod="paymentFallbackMethod")
     public PaymentResponse processPayment(@RequestBody PaymentRequest paymentRequest) throws Exception{
          PaymentResponse response=paymentservice.processPayment(paymentRequest);
          return response;
     }

     public String paymentFallbackMethod(PaymentRequest paymentRequest, Throwable t){
          return "Service is down, please try again later!";
     }
}