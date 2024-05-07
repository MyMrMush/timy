package de.dhbw.ase.timy.application.services;

import de.dhbw.ase.timy.domain.entities.category.category.Category;
import de.dhbw.ase.timy.domain.entities.category.category.CategoryBridgeRepository;
import de.dhbw.ase.timy.domain.entities.category.project.Project;
import de.dhbw.ase.timy.domain.entities.category.project.ProjectBridgeRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
    private final CategoryBridgeRepository categoryRepository;
    private final ProjectBridgeRepository projectRepository;

    @Autowired
    public CategoryService(CategoryBridgeRepository categoryRepository, ProjectBridgeRepository projectRepository) {
        this.categoryRepository = categoryRepository;
        this.projectRepository = projectRepository;
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

    public Project findProjectById(int projectId, int categoryId) {
        Category category = findCategoryById(categoryId);
        return category.getProjects().stream().filter(p -> p.getId() == projectId).findFirst().orElseThrow(() -> new EntityNotFoundException("Project with id " + projectId + " does not exist in category with id " + categoryId));
    }

    public void createProject(Project project) {
        Category category = findCategoryById(project.getCategoryId());
        category.addProject(project);
        categoryRepository.save(category);
    }

    public void updateProject(Project project) {
        Category category = findCategoryById(project.getCategoryId());
        category.getProjects().stream().filter(p -> p.getId() == project.getId()).findFirst()
                .ifPresentOrElse(p -> {
                    category.getProjects().remove(p);
                    category.getProjects().add(project);
                    categoryRepository.save(category);
                }, () -> {
                    throw new EntityNotFoundException("Project with id " + project.getId() + " does not exist in category with id " + project.getCategoryId());
                });
    }

    public void deleteProjectById(int projectId, int categoryId) {
        Category category = findCategoryById(categoryId);
        category.getProjects().stream().filter(p -> p.getId() == projectId).findFirst()
                .ifPresentOrElse(p -> {
                    category.getProjects().remove(p);
                    categoryRepository.save(category);
                    projectRepository.deleteById(projectId);
                }, () -> {
                    throw new EntityNotFoundException("Project with id " + projectId + " does not exist in category with id " + categoryId);
                });
    }
}
