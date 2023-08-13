package com.shop_center.hyper_market_ali.customer;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    // @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    public void saveCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    @Transactional
    public void updateCustomer(Long customerId, String firstName, String lastName, String email, String phoneNumber) {
        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);

        if (optionalCustomer.isPresent()) {
            Customer customer = optionalCustomer.get();

            if (firstName != null) {
                customer.setFirstName(firstName);
            }
            if (lastName != null) {
                customer.setLastName(lastName);
            }
            if (email != null) {
                customer.setEmail(email);
            }
            if (phoneNumber != null) {
                customer.setPhoneNumber(phoneNumber);
            }

            customerRepository.save(customer);
        } else {
            throw new EntityNotFoundException("Customer with ID " + customerId + " not found");
        }
    }

    public void deleteCustomerById(Long id) {
        boolean exists = customerRepository.existsById(id);
        if (!exists) {
            throw new IllegalStateException("customer with id " + id + " doesn't exist!");
        }

        customerRepository.deleteById(id);
    }

    public Optional<Customer> findCustomerById(Long id) {
        Optional<Customer> customerOptional = customerRepository.findById(id);
        if (!customerOptional.isPresent()) {
            throw new IllegalStateException("customer with id " + id + " doesn't exist!");
        }

        return customerOptional;
    }

    public void addCustomer(Customer customer) {
        Optional<Customer> customerOptional = customerRepository.findCustomerByEmail(customer.getEmail());
        if (customerOptional.isPresent()) {
            throw new IllegalStateException("email taken");
        }
        customerRepository.save(customer);
    }
}
