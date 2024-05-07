package de.dhbw.ase.timy.adapters.representations.mappers.project;

import de.dhbw.ase.timy.adapters.representations.ProjectDTO;
import de.dhbw.ase.timy.domain.entities.category.project.Project;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class ProjectToDTOMapper implements Function<Project, ProjectDTO> {

    @Override
    public ProjectDTO apply(Project project) {
        return new ProjectDTO(
                project.getId(),
                project.getName(),
                project.getDescription(),
                project.getColor().toString(),
                project.isInternal(),
                project.isActive(),
                project.getCategoryId()
        );
    }
}
