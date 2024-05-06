package de.dhbw.ase.timy.adapters.representations.mappers.category;

import de.dhbw.ase.timy.adapters.representations.CategoryDTO;
import de.dhbw.ase.timy.domain.entities.category.category.Category;
import de.dhbw.ase.timy.domain.values.Color;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class DTOToCategoryMapper implements Function<CategoryDTO, Category> {

    @Autowired
    public DTOToCategoryMapper() {
    }

    @Override
    public Category apply(CategoryDTO categoryDTO) {
        return new Category(
                categoryDTO.getId(),
                categoryDTO.getName(),
                categoryDTO.getDescription(),
                Color.fromString(categoryDTO.getColor())
        );
    }

    public Category update(Category oldCategory, CategoryDTO newCategoryDTO) {
        oldCategory.setId(newCategoryDTO.getId());
        oldCategory.setName(newCategoryDTO.getName());
        oldCategory.setDescription(newCategoryDTO.getDescription());
        oldCategory.setColor(Color.fromString(newCategoryDTO.getColor()));
        return oldCategory;
    }
}
