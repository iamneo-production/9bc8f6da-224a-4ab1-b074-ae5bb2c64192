package com.iamneo.microservices.stockservice.model;

import lombok.Data;

@Data
public class StockDto {

private Long id;
private String symbol;
private String name;
private Double price;
private Integer quantity;
}