package com.shop_center.hyper_market_ali.shipment;

import java.util.List;
import java.util.Optional;

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

import com.shop_center.hyper_market_ali.customer.Customer;
import com.shop_center.hyper_market_ali.customer.CustomerService;

import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/api/v1/shipments")
public class ShipmentController {

    private final ShipmentService shipmentService;
    private final CustomerService customerService;
    private final Logger log = LoggerFactory.getLogger(HyperMarketAliApplication.class);

    public ShipmentController(ShipmentService shipmentService, CustomerService customerService) {
        this.shipmentService = shipmentService;
        this.customerService = customerService;
    }

    @GetMapping
    public List<Shipment> getShipments() {
        log.info("Shipments are listed");
        return this.shipmentService.getShipments();
    }

    @PostMapping("/{customerId}")
    public void addShipment(@RequestBody Shipment request, @PathVariable("customerId") Long customerId) {
        Shipment shipment = new Shipment();
        shipment.setShipmentDate(request.getShipmentDate());
        shipment.setAddress(request.getAddress());
        shipment.setCity(request.getCity());
        shipment.setCounty(request.getCounty());
        shipment.setCountry(request.getCountry());

        Optional<Customer> customerOptional = customerService.findCustomerById(customerId);

        if (customerOptional.isEmpty()) {
            throw new EntityNotFoundException("Customer with ID " + customerId + " not found");
        }

        Customer customer = customerOptional.get();
        shipmentService.addShipment(shipment, customer.getCustomerId());
        log.info("Shipment created with id {} and with related Customer id {}", request.getShipmentId(), customerId);
    }

    @DeleteMapping("/{shipmentId}")
    public void deleteShipment(@PathVariable("shipmentId") Long shipmentId) {
        shipmentService.deleteShipmentById(shipmentId);
        log.info("Shipment deleted with id {}", shipmentId);
    }

    @PutMapping("/{shipmentId}/{customerId}")
    public void updateShipment(@PathVariable("shipmentId") Long shipmentId, @PathVariable("customerId") Long customerId,
            @RequestBody Shipment request) {
        shipmentService.updateShipment(shipmentId, request.getShipmentDate(), request.getAddress(),
                request.getCity(), request.getCounty(), request.getCountry(), customerId);
        log.info("Shipment updated with id {} and with related Customer id {}", shipmentId, customerId);
    }
}
