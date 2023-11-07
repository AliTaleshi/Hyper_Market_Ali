package com.shop_center.hyper_market_ali.shipment;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shop_center.hyper_market_ali.customer.Customer;
import com.shop_center.hyper_market_ali.customer.CustomerService;

import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/api/v1/shipments")
public class ShipmentController {

    private final ShipmentService shipmentService;
    private final CustomerService customerService;

    public ShipmentController(ShipmentService shipmentService, CustomerService customerService) {
        this.shipmentService = shipmentService;
        this.customerService = customerService;
    }

    @GetMapping
    public List<Shipment> getShipments() {
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
    }

    @DeleteMapping("/{shipmentId}")
    public void deleteShipment(@PathVariable("shipmentId") Long shipmentId) {
        shipmentService.deleteShipmentById(shipmentId);
    }

    @PutMapping("/{shipmentId}/{customerId}")
    public void updateShipment(@PathVariable("shipmentId") Long shipmentId, @PathVariable("customerId") Long customerId,
            @RequestBody Shipment request) {
        shipmentService.updateShipment(shipmentId, request.getShipmentDate(), request.getAddress(),
                request.getCity(), request.getCounty(), request.getCountry(), customerId);
    }
}
