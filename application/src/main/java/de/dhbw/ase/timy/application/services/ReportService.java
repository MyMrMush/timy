package de.dhbw.ase.timy.application.services;

import de.dhbw.ase.timy.domain.entities.booking.booking.Booking;
import de.dhbw.ase.timy.domain.entities.category.category.Category;
import de.dhbw.ase.timy.domain.entities.report.bookingrep.BookingReportRepresentation;
import de.dhbw.ase.timy.domain.entities.report.report.Report;
import de.dhbw.ase.timy.domain.entities.report.report.ReportBridgeRepository;
import de.dhbw.ase.timy.domain.services.ReportBuilder;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReportService {
    private final ReportBridgeRepository reportRepository;
    private final CategoryService categoryService;
    private final BookingService bookingService;

    @Autowired
    public ReportService(ReportBridgeRepository reportRepository, CategoryService categoryService, BookingService bookingService) {
        this.reportRepository = reportRepository;
        this.categoryService = categoryService;
        this.bookingService = bookingService;
    }

    public Report createReport(String name, String description, LocalDate start, LocalDate end) throws Exception {
        return createReport(name, description, start, end, new int[0], new int[0]);
    }

    public Report createReport(String name, String description, LocalDate start, LocalDate end, int[] categoryIds, int[] projectIds) throws Exception {
        List<Category> categories = categoryService.findAllCategories();
        List<Booking> bookings;
        if (categoryIds != null && categoryIds.length > 0 && projectIds != null && projectIds.length > 0) {
            bookings = bookingService.findByStartBetweenAndCategoryInAndProjectIn(start, end, categoryIds, projectIds);
        } else if (categoryIds != null && categoryIds.length > 0) {
            bookings = bookingService.findByStartBetweenAndCategoryIn(start, end, categoryIds);
        } else if (projectIds.length > 0) {
            throw new Exception("Project filter without category filter is not allowed");
        } else {
            bookings = bookingService.findByStartBetween(start, end);
        }
        if (bookings.isEmpty()) {
            throw new EntityNotFoundException("No bookings found for the given filter");
        }
        Report report = ReportBuilder.fromBookings(name, description, start, end, bookings, categories);
        reportRepository.save(report);
        return report;
    }

    public List<Report> findAllReports() {
        return reportRepository.findAll();
    }

    public Report findReportById(int id) {
        return reportRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Report with id " + id + " does not exist"));
    }

    public List<BookingReportRepresentation> getBookingFromReportFiltered(int reportId, int categoryId, int projectId) {
        Report report =  reportRepository.findById(reportId).orElseThrow(() -> new EntityNotFoundException("Report with id " + reportId + " does not exist"));
        List<BookingReportRepresentation> bookings =new ArrayList<>();

        for (BookingReportRepresentation booking: report){
            if(booking.getCategoryId() == categoryId && booking.getProjectId() == projectId){
                bookings.add(booking);
            }
        }

        return bookings;
    }
}
