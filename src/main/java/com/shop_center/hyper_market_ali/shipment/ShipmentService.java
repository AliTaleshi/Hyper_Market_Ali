package com.shop_center.hyper_market_ali.shipment;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.shop_center.hyper_market_ali.customer.Customer;
import com.shop_center.hyper_market_ali.customer.CustomerRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class ShipmentService {

    private final ShipmentRepository shipmentRepository;
    private final CustomerRepository customerRepository;

    public ShipmentService(ShipmentRepository shipmentRepository, CustomerRepository customerRepository) {
        this.shipmentRepository = shipmentRepository;
        this.customerRepository = customerRepository;
    }

    public List<Shipment> getShipments() {
        return shipmentRepository.findAll();
    }

    @Transactional
    public void updateShipment(Long shipmentId, Date shipmentDate, String address, String city, String county,
            String country, Long customerId) {

        Optional<Shipment> shipmentOptional = shipmentRepository.findById(shipmentId);

        if (shipmentOptional.isEmpty()) {
            throw new EntityNotFoundException("Shipment with ID " + shipmentId + " not found");
        }

        Shipment shipment = shipmentOptional.get();

        if (customerId != null) {
            Optional<Customer> customerOptional = customerRepository.findById(customerId);

            if (customerOptional.isEmpty()) {
                throw new EntityNotFoundException("Customer with ID " + customerId + " not found");
            }

            shipment.setCustomer(customerOptional.get());
        }

        if (shipmentDate != null) {
            shipment.setShipmentDate(shipmentDate);
        }
        if (address != null) {
            shipment.setAddress(address);
        }
        if (city != null) {
            shipment.setCity(city);
        }
        if (county != null) {
            shipment.setCounty(county);
        }
        if (country != null) {
            shipment.setCountry(country);
        }

        shipmentRepository.save(shipment);
    }

    public void deleteShipmentById(Long shipmentId) {
        Optional<Shipment> shipmeOptional = shipmentRepository.findById(shipmentId);
        if (shipmeOptional.isEmpty()) {
            throw new EntityNotFoundException("Shipment with ID " + shipmentId + " not found");
        }

        shipmentRepository.deleteById(shipmentId);
    }

    public Optional<Shipment> findShipmentById(Long shipmentId) {
        Optional<Shipment> shipmentOptional = shipmentRepository.findById(shipmentId);
        if (shipmentOptional.isEmpty()) {
            throw new EntityNotFoundException("Product with ID " + shipmentId);
        }

        return shipmentOptional;
    }

    public void addShipment(Shipment shipment, Long customerId) {
        Optional<Customer> customerOptional = customerRepository.findById(customerId);
        if (customerOptional.isEmpty()) {
            throw new EntityNotFoundException("Category with ID " + customerId + " not found");
        }

        shipment.setCustomer(customerOptional.get());
        shipmentRepository.save(shipment);
    }
}
