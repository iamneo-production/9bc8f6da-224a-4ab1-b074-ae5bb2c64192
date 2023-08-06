package com.iamneo.microservices.portfolioservice.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class PortfolioResponseDto {

    private Long customerId;
    private List<StockDto> stockDto;
    private Double totalAmount;

    public PortfolioResponseDto(Long customerId) {
        this.customerId = customerId;
        this.stockDto = new ArrayList<>();
        this.totalAmount = 0.0;
    }


}
