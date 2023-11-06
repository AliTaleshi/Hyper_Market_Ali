package com.shop_center.hyper_market_ali.category;

import java.util.List;

import com.shop_center.hyper_market_ali.product.Product;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table
public class Category {

    @Id
    @SequenceGenerator(name = "category_id_sequence", sequenceName = "category_id_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "category_id_sequence")
    private Long categoryId;
    private String name;

    @OneToMany(mappedBy = "category")
    private List<Product> products;

    public Category(String name) {
        categoryId = Long.valueOf(0);
        this.name = name;
    }

    public Category(Long categoryId, String name) {
        this.categoryId = categoryId;
        this.name = name;
    }

    public Category() {
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
