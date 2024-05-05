package de.dhbw.ase.timy.domain.services;

import java.time.LocalDateTime;

public abstract class DateTimeValidator {

    /**
     * Checks if the given LocalDateTime is valid based on the following conditions:
     * - The minute of the hour is a multiple of 15.
     * - The second of the minute is 0.
     * - The nanosecond of the second is 0.
     *
     * @param dateTime the LocalDateTime to validate
     * @return true if the dateTime meets all the conditions, false otherwise
     */
    public static boolean isValidDateTime(LocalDateTime dateTime){
        boolean is15Min = dateTime.getMinute() % 15 == 0;
        boolean hasNoSeconds = dateTime.getSecond() == 0;
        boolean hasNoMills = dateTime.getNano() == 0;
        return is15Min && hasNoSeconds && hasNoMills;
    }

    /**
     * Checks if the given end LocalDateTime is after the start LocalDateTime and both are valid.
     *
     * @param start the start LocalDateTime
     * @param end the end LocalDateTime
     * @return true if the end is after the start and both are valid, false otherwise
     */
    public static boolean isEndAfterStart(LocalDateTime start, LocalDateTime end){
       return isValidDateTime(start) && isValidDateTime(end) && end.isAfter(start);
    }

}
