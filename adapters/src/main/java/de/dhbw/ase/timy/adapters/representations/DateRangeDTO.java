package de.dhbw.ase.timy.adapters.representations;

import java.time.LocalDate;

public class DateRangeDTO {
    private LocalDate start;
    private LocalDate end;

    public DateRangeDTO(LocalDate start, LocalDate end) {
        this.start = start;
        this.end = end;
    }

    public LocalDate getStart() {
        return start;
    }

    public LocalDate getEnd() {
        return end;
    }
}
