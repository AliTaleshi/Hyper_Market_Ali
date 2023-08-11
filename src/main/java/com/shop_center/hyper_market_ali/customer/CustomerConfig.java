package com.shop_center.hyper_market_ali.customer;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomerConfig {

    @Bean
    CommandLineRunner commandLineRunner(CustomerRepository customerRepository) {
        return arge -> {
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
        };
    }
}
