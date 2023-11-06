package com.shop_center.hyper_market_ali.product;

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

import com.shop_center.hyper_market_ali.category.Category;
import com.shop_center.hyper_market_ali.category.CategoryService;

import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping(path = "api/v1/products")
public class ProductController {

    private final ProductService productService;
    private final CategoryService categoryService;

    public ProductController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @GetMapping
    public List<Product> getProducts() {
        return this.productService.getProducts();
    }

    @PostMapping("/{categoryId}")
    public void addProduct(@RequestBody Product request, @PathVariable("categoryId") Long categoryId) {
        Product product = new Product();
        product.setStockKeepingUnit(request.getStockKeepingUnit());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setStock(request.getStock());

        Optional<Category> categoryOptional = categoryService.findCategoryById(categoryId);

        if (categoryOptional.isEmpty()) {
            throw new EntityNotFoundException("Category with ID " + categoryId + " not found");
        }

        Category category = categoryOptional.get();
        productService.addProduct(product, category.getCategoryId());
    }

    @DeleteMapping("/{productId}")
    public void deleteProduct(@PathVariable("productId") Long productId) {
        productService.deleteProductById(productId);
    }

    @PutMapping("/{productId}/{categoryId}")
    public void updateProduct(@PathVariable("productId") Long productId, @PathVariable("categoryId") Long categoryId,
            @RequestBody Product request) {
        productService.updateProduct(productId, request.getStockKeepingUnit(), request.getDescription(),
                request.getPrice(), request.getStock(), categoryId);
    }

}
