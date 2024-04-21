package de.dhbw.ase.timy.plugins.persistence.category;

import de.dhbw.ase.timy.domain.entities.category.category.Category;
import de.dhbw.ase.timy.domain.entities.category.category.CategoryBridgeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CategoryRepository implements CategoryBridgeRepository {

    @Autowired
    CategorySpringRepository categorySpringRepository;

    @Override
    public void save(Category category) {
        this.categorySpringRepository.save(category);
    }

    @Override
    public void findById(int id) {
        this.categorySpringRepository.findById(id);
    }

    @Override
    public void deleteById(int id) {
        this.categorySpringRepository.deleteById(id);
    }
}
