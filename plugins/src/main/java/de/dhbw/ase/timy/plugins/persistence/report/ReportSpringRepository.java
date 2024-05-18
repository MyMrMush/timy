package de.dhbw.ase.timy.plugins.persistence.report;

import de.dhbw.ase.timy.domain.entities.report.report.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportSpringRepository extends JpaRepository<Report, Integer>{
}
