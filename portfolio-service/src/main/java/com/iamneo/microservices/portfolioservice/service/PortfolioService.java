package com.iamneo.microservices.portfolioservice.service;

import com.iamneo.microservices.portfolioservice.model.Portfolio;
import com.iamneo.microservices.portfolioservice.model.PortfolioResponseDto;
import com.iamneo.microservices.portfolioservice.model.StockDto;
import com.iamneo.microservices.portfolioservice.repository.PortfolioRepository;
import com.iamneo.microservices.portfolioservice.service.client.StocksFeignClient;
import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PortfolioService {

    @Autowired
    private PortfolioRepository portfolioRepository;

    @Autowired
    private StocksFeignClient stocksFeignClient;

    @Transactional
    public String addStock(Portfolio portfolio){

        Portfolio existingQuantity = portfolioRepository.findByCustomerIdAndStockId(portfolio.getCustomerId(), portfolio.getStockId());

        if (existingQuantity != null){

            existingQuantity.addQuantity(portfolio.getQuantity());
            portfolioRepository.save(existingQuantity);
        }else {

            portfolioRepository.save(portfolio);
        }

        return "Success";
    }

    @Transactional
    public String sellStock(Portfolio portfolio){

        Portfolio existingQuantity = portfolioRepository.findByCustomerIdAndStockId(portfolio.getCustomerId(), portfolio.getStockId());

        if (existingQuantity != null){

            if (existingQuantity.getQuantity() < portfolio.getQuantity()){

                return "Invalid Sell Quantity";
            }else if (existingQuantity.getQuantity().equals(portfolio.getQuantity())){

                portfolioRepository.deleteById(existingQuantity.getId());
            }else {
                existingQuantity.reduceQuantity(portfolio.getQuantity());
                portfolioRepository.save(existingQuantity);
            }
        }

        return "Success";
    }

    public PortfolioResponseDto getClientPortfolio(Long customerId) {

        List<Portfolio> getStocks = portfolioRepository.findAllByCustomerId(customerId);
        PortfolioResponseDto portfolioResponseDto = new PortfolioResponseDto(customerId);

        if (getStocks.isEmpty()){

            List<Long> stocksList = getStocks.stream().map(Portfolio::getStockId).toList();

            Map<Long,StockDto> stocksDetails = stocksFeignClient.getStocksDetails(stocksList).stream().
                    collect(Collectors.toMap(StockDto::getId, (i)->i));

            double totalAmount = 0.0;

            for (Portfolio stock : getStocks) {
                stocksDetails.get(stock.getStockId()).setQuantity(stock.getQuantity());
                totalAmount += (stock.getQuantity() * stocksDetails.get(stock.getStockId()).getPrice());
                portfolioResponseDto.getStockDto().add(stocksDetails.get(stock.getStockId()));
            }

            portfolioResponseDto.setTotalAmount(totalAmount);
        }

        return portfolioResponseDto;
    }
}
