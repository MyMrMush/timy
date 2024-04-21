package de.dhbw.ase.timy.plugins.persistence.category;

import de.dhbw.ase.timy.domain.entities.category.category.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategorySpringRepository extends JpaRepository<Category, Integer> {

}
