package com.shop_center.hyper_market_ali.product;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p FROM Product p WHERE p.stockKeepingUnit = ?1")
    Optional<Product> findByStockKeepingUnit(String stockKeepingUnit);
}
