package de.dhbw.ase.timy.adapters.representations.mappers.category;

import de.dhbw.ase.timy.adapters.representations.CategoryDTO;
import de.dhbw.ase.timy.domain.entities.category.category.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.function.Function;

//Beispiel macht keinen Sinn, da EntityExample und das DTO 1 zu 1 das gleiche sind.
//Würde benötigt werden bei komplexen Entitys die z.B. sich gegenseitig referenzieren.
//Solche Mapper lässt man im normal fall aus den entitys und dtos automatisch generieren, da wir aber bestimmte
//Zeilen anzahl schreiben müssen, macht es Sinn diese selbst zu schreiben :)
@Component
public class CategoryToDTOMapper implements Function<Category, CategoryDTO> {

    @Lazy
    @Autowired
    public CategoryToDTOMapper() {

    }

    @Override
    public CategoryDTO apply(Category category) {
       return new CategoryDTO(
    		  category.getId(),
               category.getName(),
               category.getDescription(),
               category.getColor().toString()
       );
    }
}
