 package com.iamneo.microservices.portfolioservice.service.client;

import com.iamneo.microservices.portfolioservice.model.StockDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient("stocks")
public interface StocksFeignClient {

    @RequestMapping(method = RequestMethod.POST,path = "stocks/getStockDetails",value = "getStocksList", consumes = "application/json")
    List<StockDto> getStocksDetails(List<Long> stockList);
}
