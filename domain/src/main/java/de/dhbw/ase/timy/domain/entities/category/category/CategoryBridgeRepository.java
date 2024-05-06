package de.dhbw.ase.timy.domain.entities.category.category;

import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryBridgeRepository {
    void save(Category category);
    Optional<Category> findById(int id);
    void deleteById(int id);
}
