package de.dhbw.ase.timy.adapters.representations;

import java.time.LocalDate;

public class ReportRequestDTO {
    private String name;
    private String description;
    private LocalDate start;
    private LocalDate end;
    private int[] categoryIds;
    private int[] projectIds;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getStart() {
        return start;
    }

    public LocalDate getEnd() {
        return end;
    }

    public int[] getCategoryIds() {
        return categoryIds;
    }

    public int[] getProjectIds() {
        return projectIds;
    }

    public boolean isFiltered() {
        return (categoryIds != null || projectIds != null) && (categoryIds != null && categoryIds.length > 0 || projectIds != null && projectIds.length > 0);
    }
}
