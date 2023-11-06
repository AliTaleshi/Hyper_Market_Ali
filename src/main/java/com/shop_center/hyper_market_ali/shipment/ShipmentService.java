package com.shop_center.hyper_market_ali.shipment;

import java.util.List;
import org.springframework.stereotype.Service;

import com.shop_center.hyper_market_ali.customer.CustomerRepository;

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
}
