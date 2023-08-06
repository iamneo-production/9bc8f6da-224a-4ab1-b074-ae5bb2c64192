package com.iamneo.microservices.portfolioservice.controller;

import com.iamneo.microservices.portfolioservice.model.Portfolio;
import com.iamneo.microservices.portfolioservice.model.PortfolioResponseDto;
import com.iamneo.microservices.portfolioservice.model.StockDto;
import com.iamneo.microservices.portfolioservice.service.PortfolioService;
import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("portfolio")
public class PortfolioController {

    @Autowired
    private PortfolioService portfolioservice;

    @PostMapping("/{userId}/add_stock")
    @CircuitBreaker(name = "portfolioServiceBreaker",fallbackMethod="portfolioDetailsFallbackMethod")
    @Retry(name = "portfolioServiceBreaker")
    public ResponseEntity<String> addStock(@PathVariable("userId") Long customerId, @RequestBody Portfolio portfolio) {

        portfolio.setCustomerId(customerId);
        String response = portfolioservice.addStock(portfolio);
        return new ResponseEntity<>(response, HttpStatusCode.valueOf(200));
    }

    @PostMapping("/{userId}/sell_stock")
    @CircuitBreaker(name = "portfolioServiceBreaker",fallbackMethod="portfolioDetailsFallbackMethod")
    @Retry(name = "portfolioServiceBreaker")
    public ResponseEntity<String> sellStock(@PathVariable("userId") Long customerId, @RequestBody Portfolio portfolio) {

        portfolio.setCustomerId(customerId);
        String response = portfolioservice.sellStock(portfolio);
        return new ResponseEntity<>(response, HttpStatusCode.valueOf(200));
    }

    public ResponseEntity<String> portfolioDetailsFallbackMethod(Long customerId, Portfolio portfolio, Exception ex){

        return new ResponseEntity<>("Service is down, please try again later!", HttpStatusCode.valueOf(503));
    }

    @GetMapping("/{userId}")
    @CircuitBreaker(name = "getStockDetails",fallbackMethod="getStockDetailsFallback")
    @Retry(name = "getStockDetails")
    public ResponseEntity<PortfolioResponseDto> getPortfolio(@PathVariable("userId") Long customerId) {

        PortfolioResponseDto response = portfolioservice.getClientPortfolio(customerId);
        return new ResponseEntity<>(response, HttpStatusCode.valueOf(200));
    }

    public ResponseEntity<PortfolioResponseDto> getStockDetailsFallback(Long customerId, Exception ex){

        System.out.println("Printing Fallback Method");

        return new ResponseEntity<>(new PortfolioResponseDto(customerId), HttpStatusCode.valueOf(503));
    }
}
