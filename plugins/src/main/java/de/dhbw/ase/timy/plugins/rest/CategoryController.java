package de.dhbw.ase.timy.plugins.rest;

import de.dhbw.ase.timy.adapters.representations.CategoryDTO;
import de.dhbw.ase.timy.adapters.representations.mappers.category.CategoryToDTOMapper;
import de.dhbw.ase.timy.adapters.representations.mappers.category.DTOToCategoryMapper;
import de.dhbw.ase.timy.application.services.CategoryService;
import de.dhbw.ase.timy.domain.entities.category.category.Category;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "Category")
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService categoryService;
    private final CategoryToDTOMapper categoryToDTOMapper;
    private final DTOToCategoryMapper dtoToCategoryMapper;

    @Autowired
    public CategoryController(CategoryService categoryService, CategoryToDTOMapper categoryToDTOMapper, DTOToCategoryMapper dtoToCategoryMapper) {
        this.categoryService = categoryService;
        this.categoryToDTOMapper = categoryToDTOMapper;
        this.dtoToCategoryMapper = dtoToCategoryMapper;
    }

    @GetMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved Category"),
            @ApiResponse(responseCode = "404", description = "Category not found")
    })
    public ResponseEntity<CategoryDTO> getCategory(@PathVariable int id) {
        try{
            Category category = categoryService.findCategoryById(id);
            return ResponseEntity.ok(categoryToDTOMapper.apply(category));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Category created"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    public ResponseEntity<CategoryDTO> createCategory(@RequestBody CategoryDTO categoryDTO) {
        try{
            Category category = dtoToCategoryMapper.apply(categoryDTO);
            categoryService.createCategory(category);
            return ResponseEntity.ok(categoryToDTOMapper.apply(category));
        } catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Category updated"),
            @ApiResponse(responseCode = "404", description = "Category not found"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    public ResponseEntity<CategoryDTO> updateCategory(@RequestBody CategoryDTO categoryDTO) {
        try{
            Category category = dtoToCategoryMapper.apply(categoryDTO);
            categoryService.updateCategory(category);
            return ResponseEntity.ok(categoryToDTOMapper.apply(category));
        } catch (EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        } catch (Exception e ){
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Category successfully deleted"),
            @ApiResponse(responseCode = "404", description = "Category not found")
    })
    public ResponseEntity<Void> deleteCategory(@PathVariable int id) {
        try{
            categoryService.deleteCategoryById(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

}
