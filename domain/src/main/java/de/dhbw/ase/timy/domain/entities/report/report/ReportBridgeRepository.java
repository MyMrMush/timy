package de.dhbw.ase.timy.domain.entities.report.report;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReportBridgeRepository {
    void save(Report report);
    List<Report> findAll();
    Optional<Report> findById(int id);
    void deleteById(int id);
}
