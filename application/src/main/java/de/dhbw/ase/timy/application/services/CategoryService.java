package de.dhbw.ase.timy.application.services;

import de.dhbw.ase.timy.domain.entities.category.category.Category;
import de.dhbw.ase.timy.domain.entities.category.category.CategoryBridgeRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
    private final CategoryBridgeRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryBridgeRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public void createCategory(Category category) {
        categoryRepository.findById(category.getId()).ifPresent(c -> {
            throw new EntityExistsException("Category with id " + category.getId() + " already exists");
        });
        categoryRepository.save(category);
    }

    public Category findCategoryById(int id) {
        return categoryRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Category with id " + id + " does not exist"));
    }

    public void updateCategory(Category category) {
        categoryRepository.findById(category.getId()).orElseThrow(() -> new EntityNotFoundException("Category with id " + category.getId() + " does not exist"));
        categoryRepository.save(category);
    }

    public void deleteCategoryById(int id) {
        categoryRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Category with id " + id + " does not exist"));
        categoryRepository.deleteById(id);
    }
}
