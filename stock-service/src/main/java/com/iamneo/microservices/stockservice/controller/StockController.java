package com.iamneo.microservices.stockservice.controller;

import com.iamneo.microservices.stockservice.services.StockService;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/stock")
public class StockController {


@Autowired
private StockService stockService;

@GetMapping("/{symbol}")
public ResponseEntity<String> fetchStockData(@PathVariable("symbol") String symbol) {
return stockService.getStockData(symbol);
}

@PostMapping("/getStockDetails")
public ResponseEntity<String> fetchStockDataList(@RequestBody List<String> symbolList) {
return null;
}
}