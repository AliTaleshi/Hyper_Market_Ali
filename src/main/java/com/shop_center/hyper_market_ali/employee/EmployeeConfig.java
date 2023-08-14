package com.shop_center.hyper_market_ali.employee;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EmployeeConfig {

    @Bean
    CommandLineRunner commandLineRunner(EmployeeRepository employeeRepository) {
        return arge -> {
            Employee employee1 = new Employee(
                    "Ali",
                    "Mmdi",
                    "ali@gmail.com",
                    "09126552496",
                    "Cashier",
                    1400.0);

            Employee employee2 = new Employee(
                    "Salar",
                    "Salari",
                    "salar@gmail.com",
                    "09124250985",
                    "Cashier",
                    1400.0);

            employeeRepository.saveAll(List.of(employee1, employee2));
        };
    }
}
