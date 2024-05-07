package de.dhbw.ase.timy.adapters.representations.mappers.project;

import de.dhbw.ase.timy.adapters.representations.ProjectDTO;
import de.dhbw.ase.timy.domain.entities.category.project.Project;
import de.dhbw.ase.timy.domain.values.Color;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class DTOToProjectMapper implements Function<ProjectDTO, Project> {

    @Override
    public Project apply(ProjectDTO projectDTO) {
        return new Project(
                projectDTO.getId(),
                projectDTO.getName(),
                projectDTO.getDescription(),
                Color.fromString(projectDTO.getColor()),
                projectDTO.isInternal(),
                projectDTO.isActive(),
                projectDTO.getCategoryId()
        );
    }

    public Project update(Project oldProject, ProjectDTO newProjectDTO) {
        oldProject.setId(newProjectDTO.getId());
        oldProject.setName(newProjectDTO.getName());
        oldProject.setDescription(newProjectDTO.getDescription());
        oldProject.setColor(Color.fromString(newProjectDTO.getColor()));
        oldProject.setInternal(newProjectDTO.isInternal());
        oldProject.setActive(newProjectDTO.isActive());
        oldProject.setCategoryId(newProjectDTO.getCategoryId());
        return oldProject;
    }
}
