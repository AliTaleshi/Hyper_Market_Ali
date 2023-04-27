package com.shop_center.hyper_market_ali.customer;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    public List<Customer> getCustomers() {
        return List.of(
                new Customer(
                        3,
                        "mmm",
                        "mmm@gmail.com",
                        14));
    }
}
