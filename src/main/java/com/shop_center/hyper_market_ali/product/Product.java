package com.shop_center.hyper_market_ali.product;

import com.shop_center.hyper_market_ali.category.Category;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;

@Entity
public class Product {

    @Id
    @SequenceGenerator(name = "product_id_seqeunce", sequenceName = "product_id_seqeunce", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_id_sequence")
    private Long productId;

    @Column(unique = true)
    private String stockKeepingUnit;

    private String description;
    private Double price;
    private Integer stock;

    @ManyToOne
    @JoinColumn(name = "categoryId")
    private Category category;

    public Product(String stockKeepingUnit, String description, Double price, Integer stock, Category category) {
        this.stockKeepingUnit = stockKeepingUnit;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.category = category;
    }

    public Product() {
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getStockKeepingUnit() {
        return stockKeepingUnit;
    }

    public void setStockKeepingUnit(String stockKeepingUnit) {
        this.stockKeepingUnit = stockKeepingUnit;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

}
