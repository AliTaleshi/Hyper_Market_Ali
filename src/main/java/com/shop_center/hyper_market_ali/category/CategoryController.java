package com.shop_center.hyper_market_ali.category;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public List<Category> getCategories() {
        return categoryService.getCategories();
    }

    @PostMapping
    public void addCategory(@RequestBody Category request) {
        Category category = new Category();
        category.setName(request.getName());
        categoryService.addCategory(category);
    }

    @DeleteMapping("{categoryId}")
    public void deleteCustomer(@PathVariable("categoryId") Long categoryId) {
        categoryService.deleteCategoryById(categoryId);
    }

    @PutMapping("{categoryId}")
    public void updateCategory(@PathVariable("categoryId") Long categoryId, @RequestBody Category request) {
        categoryService.updateCategory(categoryId, request.getName());
    }
}