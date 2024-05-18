package de.dhbw.ase.timy.plugins.persistence.report;

import de.dhbw.ase.timy.domain.entities.report.report.Report;
import de.dhbw.ase.timy.domain.entities.report.report.ReportBridgeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ReportRepository implements ReportBridgeRepository {
    @Autowired
    ReportSpringRepository reportSpringRepository;


    @Override
    public void save(Report report) {
        this.reportSpringRepository.save(report);
    }

    @Override
    public List<Report> findAll() {
        return this.reportSpringRepository.findAll();
    }

    @Override
    public Optional<Report> findById(int id) {
        return this.reportSpringRepository.findById(id);
    }

    @Override
    public void deleteById(int id) {
        this.reportSpringRepository.deleteById(id);
    }
}
