package com.microservices.paymentservice.config;

import com.microservices.paymentservice.model.Stock;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient("stockinformation-service")
public interface StockInformationFeignClient {
    @GetMapping("/stock/{symbol}")
    Stock getStockDetails(@PathVariable("symbol") String symbol);
}