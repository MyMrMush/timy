package de.dhbw.ase.timy.plugins.rest;

import de.dhbw.ase.timy.adapters.representations.ProjectDTO;
import de.dhbw.ase.timy.adapters.representations.mappers.project.DTOToProjectMapper;
import de.dhbw.ase.timy.adapters.representations.mappers.project.ProjectToDTOMapper;
import de.dhbw.ase.timy.application.services.CategoryService;
import de.dhbw.ase.timy.domain.entities.category.project.Project;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "Project")
@RequestMapping("/project")
public class ProjectController {

    private final CategoryService categoryService;
    private final ProjectToDTOMapper projectToDTOMapper;
    private final DTOToProjectMapper dtoToProjectMapper;

    @Autowired
    public ProjectController(CategoryService categoryService, ProjectToDTOMapper projectToDTOMapper, DTOToProjectMapper dtoToProjectMapper) {
        this.categoryService = categoryService;
        this.projectToDTOMapper = projectToDTOMapper;
        this.dtoToProjectMapper = dtoToProjectMapper;
    }

    @GetMapping("/{projectId}/{categoryId}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved Project"),
            @ApiResponse(responseCode = "404", description = "Project not found")
    })
    public ResponseEntity<ProjectDTO> getProject(@PathVariable int projectId, @PathVariable int categoryId) {
        try{
            Project project = categoryService.findProjectById(projectId, categoryId);
            return ResponseEntity.ok(projectToDTOMapper.apply(project));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Project created"),
            @ApiResponse(responseCode = "404", description = "Category not found"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    public ResponseEntity<ProjectDTO> createProject(@RequestBody ProjectDTO projectDTO){
        try{
            Project project = dtoToProjectMapper.apply(projectDTO);
            categoryService.createProject(project);
            return ResponseEntity.ok(projectToDTOMapper.apply(project));
        } catch (EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        } catch (Exception e){
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Project updated"),
            @ApiResponse(responseCode = "404", description = "Project not found"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    public ResponseEntity<ProjectDTO> updateProject(@RequestBody ProjectDTO projectDTO){
        try{
            Project project = categoryService.findProjectById(projectDTO.getId(), projectDTO.getCategoryId());
            project = dtoToProjectMapper.update(project, projectDTO);
            categoryService.updateProject(project);
            return ResponseEntity.ok(projectToDTOMapper.apply(project));
        } catch (EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{projectId}/{categoryId}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Project deleted"),
            @ApiResponse(responseCode = "404", description = "Project not found")
    })
    public ResponseEntity<Void> deleteProject(@PathVariable int projectId, @PathVariable int categoryId){
        try{
            categoryService.deleteProjectById(projectId, categoryId);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }



}
