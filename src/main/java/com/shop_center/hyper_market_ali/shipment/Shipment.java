package com.shop_center.hyper_market_ali.shipment;

import java.time.LocalDateTime;

import com.shop_center.hyper_market_ali.customer.Customer;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;

@Entity
public class Shipment {

    @Id
    @SequenceGenerator(name = "customer_id_sequence", sequenceName = "customer_id_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_id_sequence")
    private Long shipmentId;
    private LocalDateTime shipmentDate;
    private String address;
    private String city;
    private String county;
    private String country;

    @ManyToOne
    private Customer customer;

    public Shipment() {
    }

    public Shipment(LocalDateTime shipmentDate, String address, String city, String county, String country,
            Customer customer) {
        this.shipmentDate = shipmentDate;
        this.address = address;
        this.city = city;
        this.county = county;
        this.country = country;
        this.customer = customer;
    }

    public Long getShipmentId() {
        return shipmentId;
    }

    public void setShipmentId(Long shipmentId) {
        this.shipmentId = shipmentId;
    }

    public LocalDateTime getShipmentDate() {
        return this.shipmentDate;
    }

    public void setShipmentDate(LocalDateTime shipmentDate) {
        this.shipmentDate = shipmentDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

}
