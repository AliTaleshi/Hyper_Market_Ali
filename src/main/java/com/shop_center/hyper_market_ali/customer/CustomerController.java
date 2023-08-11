package com.shop_center.hyper_market_ali.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController()
@RequestMapping(path = "api/v1/customers")
public class CustomerController {

    @Autowired
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public List<Customer> getCustomers() {
        return customerService.getCustomers();
    }

    @PostMapping
    public void addCustomer(@RequestBody Customer request) {
        Customer customer = new Customer();
        customer.setName(request.getName());
        customer.setEmail(request.getEmail());
        customer.setAge(request.getAge());
        customerService.addCustomer(customer);
    }

    @DeleteMapping("{customerId}")
    public void deleteCustomer(@PathVariable("customerId") Integer id) {
        customerService.deleteCustomerById(id);
    }

    @PutMapping("{customerId}")
    public void updateCustomer(@PathVariable("customerId") Integer id, @RequestBody Customer request) {
        Optional<Customer> optionalCustomer = customerService.findCustomerById(id);
        Customer customer = optionalCustomer.get();
        customer.setName(request.getName());
        customer.setEmail(request.getEmail());
        customer.setAge(request.getAge());
        customerService.saveCustomer(customer);
    }
}
