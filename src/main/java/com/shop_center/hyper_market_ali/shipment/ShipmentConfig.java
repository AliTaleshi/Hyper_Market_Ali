package com.shop_center.hyper_market_ali.shipment;

import java.util.Date;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.shop_center.hyper_market_ali.category.Category;
import com.shop_center.hyper_market_ali.customer.Customer;
import com.shop_center.hyper_market_ali.product.Product;

@Configuration
public class ShipmentConfig {

    private final ShipmentRepository shipmentRepository;

    public ShipmentConfig(ShipmentRepository shipmentRepository) {
        this.shipmentRepository = shipmentRepository;
    }

    @Bean(name = "shipmentCommandLineRunner")
    CommandLineRunner commandLineRunner() {
        return args -> {
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

                Shipment shipment1 = new Shipment(
                    1,
                    
                );

                Shipment shipment2 = new Shipment();

                productRepository.saveAll(List.of(product1, product2));
            } catch (Exception e) {
                System.err.println("Error populating initial data: " + e.getMessage());
            }
        };

    }

}
