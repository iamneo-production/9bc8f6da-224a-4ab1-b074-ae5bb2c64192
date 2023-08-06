package com.iamneo.microservices.stockservice.services;

import com.iamneo.microservices.stockservice.model.StockDto;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class StockService {

private final OkHttpClient httpClient = new OkHttpClient();

@NotNull
public ResponseEntity<String> getStockData(String symbol) {
String apiUrl = "https://yahoo-finance-india1.p.rapidapi.com/market_india/news/?symbol="+ symbol;
Request request = new Request.Builder()
.url(apiUrl)
.get()
.addHeader("X-RapidAPI-Key", "b1b65adc4fmsh07d7c7deed06fcap1759fcjsn23117ca0b440")
.addHeader("X-RapidAPI-Host", "yahoo-finance-india1.p.rapidapi.com")
.build();

try {
Response response = httpClient.newCall(request).execute();
if (response.isSuccessful()) {
String responseBody = response.body().string();
return ResponseEntity.ok(responseBody);
} else {
return ResponseEntity.status(response.code()).body("Failed to fetch stock data.");
}
} catch (IOException e) {
return ResponseEntity.status(500).body("Error occurred while fetching stock data.");
}
}

public List<StockDto> getStocksDetails(List<String> stockList){

List<StockDto> response = new ArrayList<>();
for (String sym: stockList) {

String tempDetails = getStockData(sym).getBody();

}

return new ArrayList<>();
}

}