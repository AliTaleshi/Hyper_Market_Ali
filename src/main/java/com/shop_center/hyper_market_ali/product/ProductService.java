package com.shop_center.hyper_market_ali.product;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.shop_center.hyper_market_ali.category.Category;
import com.shop_center.hyper_market_ali.category.CategoryRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    public void saveProduct(Product product) {
        productRepository.save(product);
    }

    @Transactional
    public void updateProduct(Long productId, String stockKeepingUnit, String description, Double price,
            Integer stock, Long categoryId) {
        Optional<Product> productOptional = productRepository.findById(productId);
        if (productOptional.isEmpty()) {
            throw new EntityNotFoundException("Product with ID " + productId + " not found");
        }

        Product product = productOptional.get();

        if (categoryId != null) {
            Optional<Category> categoryOptional = categoryRepository.findById(categoryId);
            if (categoryOptional.isEmpty()) {
                throw new EntityNotFoundException("Category with ID " + categoryId + " not found");
            }
            product.setCategory(categoryOptional.get());
        }
        if (stockKeepingUnit != null) {
            product.setStockKeepingUnit(stockKeepingUnit);
        }
        if (description != null) {
            product.setDescription(description);
        }
        if (price != null) {
            product.setPrice(price);
        }
        if (stock != null) {
            product.setStock(stock);
        }

        productRepository.save(product);
    }

    public void deleteProductById(Long productId) {

        if (!productRepository.existsById(productId)) {
            throw new EntityNotFoundException("Product with ID " + productId + " not found");
        }

        productRepository.deleteById(productId);
    }

    public Optional<Product> findProductById(Long productId) {
        Optional<Product> producOptional = productRepository.findById(productId);
        if (producOptional.isEmpty()) {
            throw new EntityNotFoundException("Product with ID " + productId);
        }

        return producOptional;
    }

    public void addProduct(Product product, Long categoryId) {
        Optional<Category> categoryOptional = categoryRepository.findById(categoryId);
        if (categoryOptional.isEmpty()) {
            throw new EntityNotFoundException("Category with ID " + categoryId + " not found");
        }

        product.setCategory(categoryOptional.get());
        productRepository.save(product);
    }
}
