package com.shop_center.hyper_market_ali.product;

import java.util.List;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.shop_center.hyper_market_ali.category.Category;

@Configuration
public class ProductConfig {

    private final ProductRepository productRepository;

    public ProductConfig(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Bean(name = "productCommandLineRunner")
    CommandLineRunner commandLineRunner() {
        return args -> {
            // List<Category> categories = categoryRepository.findAll();
            try {
                Category category1 = new Category(Long.valueOf(1), "SeaFood");
                Category category2 = new Category(Long.valueOf(2), "Test");

                Product product1 = new Product(
                        "test1",
                        "this is description 1.",
                        19.00,
                        3,
                        category1);

                Product product2 = new Product(
                        "test2",
                        "this is description 2.",
                        20.00,
                        5,
                        category2);

                productRepository.saveAll(List.of(product1, product2));
            } catch (Exception e) {
                System.err.println("Error populating initial data: " + e.getMessage());
            }
        };
    }
}
