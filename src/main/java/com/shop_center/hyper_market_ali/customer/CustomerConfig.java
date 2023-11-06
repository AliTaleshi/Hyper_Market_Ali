package com.shop_center.hyper_market_ali.customer;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomerConfig {

    private final CustomerRepository customerRepository;

    public CustomerConfig(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Bean(name = "customerCommandLineRunner")
    CommandLineRunner commandLineRunner() {
        return arge -> {
            try {
                Customer customer1 = new Customer(
                        "Alex",
                        "Mason",
                        "alex@gmail.com",
                        "09123252496");

                Customer customer2 = new Customer(
                        "Sam",
                        "Jackson",
                        "sam@gmail.com",
                        "09124250945");

                customerRepository.saveAll(List.of(customer1, customer2));
            } catch (Exception e) {
                System.err.println("Error populating initial data: " + e.getMessage());
            }
        };
    }
}
