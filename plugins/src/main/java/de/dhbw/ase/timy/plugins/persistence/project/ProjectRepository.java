package de.dhbw.ase.timy.plugins.persistence.project;

import de.dhbw.ase.timy.domain.entities.category.project.Project;
import de.dhbw.ase.timy.domain.entities.category.project.ProjectBridgeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProjectRepository implements ProjectBridgeRepository {

    @Autowired
    ProjectSpringRepository projectSpringRepository;

    @Override
    public void save(Project project) {
        this.projectSpringRepository.save(project);
    }

    @Override
    public Optional<Project> findById(int id) {
        return this.projectSpringRepository.findById(id);
    }

    @Override
    public List<Project> findAll() {
        return this.projectSpringRepository.findAll();
    }

    @Override
    public void deleteById(int id) {
        this.projectSpringRepository.deleteById(id);
    }
}
