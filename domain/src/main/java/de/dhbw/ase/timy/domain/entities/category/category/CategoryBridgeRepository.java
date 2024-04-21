package de.dhbw.ase.timy.domain.entities.category.category;

import org.springframework.stereotype.Repository;

@Repository
public interface CategoryBridgeRepository {
    void save(Category category);
    void findById(int id);
    void deleteById(int id);
}
