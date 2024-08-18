package com.shop_center.hyper_market_ali.category;

import java.util.List;

import com.shop_center.hyper_market_ali.HyperMarketAliApplication;
import lombok.extern.java.Log;
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

@RestController
@RequestMapping(path = "api/v1/categories")
public class CategoryController {

    private final CategoryService categoryService;
    private final Logger log = LoggerFactory.getLogger(HyperMarketAliApplication.class);

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public List<Category> getCategories() {
        log.info("Categories listed");
        return categoryService.getCategories();
    }

    @PostMapping
    public void addCategory(@RequestBody Category request) {
        Category category = new Category();
        category.setName(request.getName());
        categoryService.addCategory(category);
        log.info("Category created with id {}", request.getCategoryId());
    }

    @DeleteMapping("{categoryId}")
    public void deleteCustomer(@PathVariable("categoryId") Long categoryId) {
        categoryService.deleteCategoryById(categoryId);
        log.info("Category deleted with id {}", categoryId);
    }

    @PutMapping("{categoryId}")
    public void updateCategory(@PathVariable("categoryId") Long categoryId, @RequestBody Category request) {
        categoryService.updateCategory(categoryId, request.getName());
        log.info("Category updated with id {}", categoryId);
    }
}