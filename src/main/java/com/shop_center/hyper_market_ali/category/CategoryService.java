package com.shop_center.hyper_market_ali.category;

import java.util.List;
import java.util.Optional;
import slfjd

import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    // @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    public void saveCategory(Category category) {
        categoryRepository.save(category);
    }

    @Transactional
    public void updateCategory(Long categoryId, String name) {
        Optional<Category> categoryOptional = categoryRepository.findById(categoryId);

        if (categoryOptional.isPresent()) {
            Category category = categoryOptional.get();

            if (name != null) {
                category.setName(name);
            }

            categoryRepository.save(category);
            log.info
        } else {
            throw new EntityNotFoundException("Category with ID " + categoryId + " not found");
        }
    }

    public void deleteCategoryById(Long categoryId) {
        boolean exists = categoryRepository.existsById(categoryId);
        if (!exists) {
            throw new IllegalStateException("Category with id " + categoryId + " doesn't exist!");
        }

        categoryRepository.deleteById(categoryId);
    }

    public Optional<Category> findCategoryById(Long categoryId) {
        Optional<Category> categoryOptional = categoryRepository.findById(categoryId);
        if (!categoryOptional.isPresent()) {
            throw new IllegalStateException("Category with id " + categoryId + " doesn't exist!");
        }

        return categoryOptional;
    }

    public void addCategory(Category category) {
        Optional<Category> categoryOptional = categoryRepository.findByName(category.getName());
        if (categoryOptional.isPresent()) {
            throw new IllegalStateException("Category doesn't exist!");
        }
        categoryRepository.save(category);
    }
}
