package com.iamneo.microservices.portfolioservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.iamneo.microservices.portfolioservice.model.Portfolio;

@Repository
public interface PortfolioRepository extends JpaRepository<Portfolio, Long>{
    
}
