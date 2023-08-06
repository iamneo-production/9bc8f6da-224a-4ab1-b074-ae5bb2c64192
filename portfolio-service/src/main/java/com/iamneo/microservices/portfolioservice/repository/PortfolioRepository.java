package com.iamneo.microservices.portfolioservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import com.iamneo.microservices.portfolioservice.model.Portfolio;

import java.util.List;
import java.util.Optional;

@Repository
public interface PortfolioRepository extends JpaRepository<Portfolio, Long>{

    Portfolio findByCustomerIdAndStockId(Long customerId, Long stockId);

    List<Portfolio> findAllByCustomerId(Long customerId);
}
