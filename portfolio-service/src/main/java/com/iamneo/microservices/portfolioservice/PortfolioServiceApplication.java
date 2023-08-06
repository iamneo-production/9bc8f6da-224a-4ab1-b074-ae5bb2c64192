package com.iamneo.microservices.portfolioservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableFeignClients
@EnableJpaRepositories("com.iamneo.microservices.repository")
@EntityScan("com.iamneo.microservices.model")
public class PortfolioServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PortfolioServiceApplication.class, args);
    }

}
