package de.dhbw.ase.timy.plugins.persistence.project;

import de.dhbw.ase.timy.domain.entities.category.project.Project;
import de.dhbw.ase.timy.domain.entities.category.project.ProjectBridgeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ProjectRepository implements ProjectBridgeRepository {

    @Autowired
    ProjectSpringRepository projectSpringRepository;

    @Override
    public void save(Project project) {
        this.projectSpringRepository.save(project);
    }

    @Override
    public void findById(int id) {
        this.projectSpringRepository.findById(id);
    }

    @Override
    public void deleteById(int id) {
        this.projectSpringRepository.deleteById(id);
    }
}
