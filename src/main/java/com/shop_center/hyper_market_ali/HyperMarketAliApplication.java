package com.shop_center.hyper_market_ali;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shop_center.hyper_market_ali.customer.Customer;
import com.shop_center.hyper_market_ali.customer.CustomerRepository;

@SpringBootApplication
@RestController
@RequestMapping("api/v1/customers")
public class HyperMarketAliApplication {

	@Autowired
	private CustomerRepository customerRepository;

	public static void main(String[] args) {
		SpringApplication.run(HyperMarketAliApplication.class, args);
	}

	@GetMapping
	public List<Customer> getCustomers() {
		return customerRepository.findAll();
	}

	/**
	 * NewCustomerRequest
	 * String name, String email, Integer age
	 */
	public record NewCustomerRequest(
			String name, String email, Integer age) {
	}

	@PostMapping
	public void addCustomer(@RequestBody NewCustomerRequest request) {
		Customer customer = new Customer();
		customer.setName(request.name());
		customer.setEmail(request.email());
		customer.setAge(request.age());
		customerRepository.save(customer);
	}

	@DeleteMapping("{customerId}")
	public void deleteCustomer(@PathVariable("customerId") Integer id) {
		customerRepository.deleteById(id);
	}

	@PutMapping("{customerId}")
	public void updateCustomer(@PathVariable("customerId") Integer id, @RequestBody NewCustomerRequest request) {
		Optional<Customer> optionalCustomer = customerRepository.findById(id);
		Customer customer = optionalCustomer.get();
		customer.setName(request.name());
		customer.setEmail(request.email());
		customer.setAge(request.age());
		customerRepository.save(customer);
	}
}
