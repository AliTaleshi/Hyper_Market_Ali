package com.shop_center.hyper_market_ali.category;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CategoryConfig {

    private final CategoryRepository categoryRepository;

    public CategoryConfig(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Bean(name = "categoryCommmandLineRunner")
    CommandLineRunner commandLineRunner() {
        return args -> {
            try {
                Category category1 = new Category("SeaFood");
                Category category2 = new Category("Test");
                categoryRepository.saveAll(List.of(category1, category2));
                System.out.println("Initial data populated successfully.");
            } catch (Exception e) {
                System.err.println("Error populating initial data: " + e.getMessage());
            }
        };
    }
}
