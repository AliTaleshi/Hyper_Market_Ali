package com.shop_center.hyper_market_ali.customer;

import com.shop_center.hyper_market_ali.HyperMarketAliApplication;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;

import java.util.List;

@RestController()
@RequestMapping(path = "api/v1/customers")
public class CustomerController {

    // @Autowired
    private final CustomerService customerService;
    private final Logger log = LoggerFactory.getLogger(HyperMarketAliApplication.class);

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public List<Customer> getCustomers() {
        log.info("Customers listed");
        return customerService.getCustomers();
    }

    @PostMapping
    public void addCustomer(@RequestBody Customer request) {

        Customer customer = new Customer();
        customer.setFirstName(request.getFirstName());
        customer.setLastName(request.getLastName());
        customer.setEmail(request.getEmail());
        customer.setPhoneNumber(request.getPhoneNumber());
        customerService.addCustomer(customer);
        log.info("Customer created with id {}", request.getCustomerId());
    }

    @DeleteMapping("{customerId}")
    public void deleteCustomer(@PathVariable("customerId") Long customerId) {
        customerService.deleteCustomerById(customerId);
        log.info("Customer deleted with id {}", customerId);
    }

    @PutMapping("{customerId}")
    public void updateCustomer(@PathVariable("customerId") Long customerId, @RequestBody Customer request) {
        customerService.updateCustomer(customerId, request.getFirstName(), request.getLastName(), request.getEmail(),
                request.getPhoneNumber());
        log.info("Customer is updated with id {}", customerId);
    }
}
