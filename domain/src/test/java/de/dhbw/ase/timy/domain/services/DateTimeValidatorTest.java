package de.dhbw.ase.timy.domain.services;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DateTimeValidatorTest {

    /**
     * Tests the isValidDateTime method of the DateTimeValidator class with valid date times.
     * <p>
     * This test checks if the method correctly identifies date times that are on the quarter hour
     * (i.e., the minute is a multiple of 15) as valid.
     */
    @Test
    void isValidDateTimeValid() {
        LocalDateTime dateTime = LocalDateTime.parse("2021-06-01T12:00:00");
        assertTrue(DateTimeValidator.isValidDateTime(dateTime));
        dateTime = LocalDateTime.parse("2021-06-01T12:15:00");
        assertTrue(DateTimeValidator.isValidDateTime(dateTime));
    }

    /**
     * Tests the isValidDateTime method of the DateTimeValidator class with invalid date times.
     * <p>
     * This test checks if the method correctly identifies date times that are not on the quarter-hour
     * (i.e., the minute is not a multiple of 15) as invalid.
     */
    @Test
    void isValidDateTimeInvalid() {
        LocalDateTime dateTime = LocalDateTime.parse("2021-06-01T12:01:00");
        assertFalse(DateTimeValidator.isValidDateTime(dateTime));
        dateTime = LocalDateTime.parse("2021-06-01T12:00:01");
        assertFalse(DateTimeValidator.isValidDateTime(dateTime));
    }

    /**
     * Tests the isEndAfterStart method of the DateTimeValidator class with valid start and end times.
     * <p>
     * This test checks if the method correctly identifies a valid range where the end time is after the start time.
     */
    @Test
    void isEndAfterStartValid() {
        LocalDateTime start = LocalDateTime.parse("2021-06-01T12:00:00");
        LocalDateTime end = LocalDateTime.parse("2021-06-01T12:15:00");
        assertTrue(DateTimeValidator.isEndAfterStart(start, end));
    }

    /**
     * Tests the isEndAfterStart method of the DateTimeValidator class with invalid start and end times.
     * <p>
     * This test checks if the method correctly identifies an invalid range where the end time is before the start time.
     */
    @Test
    void isEndAfterStartInvalid() {
        LocalDateTime start = LocalDateTime.parse("2021-06-01T12:15:00");
        LocalDateTime end = LocalDateTime.parse("2021-06-01T12:00:00");
        assertFalse(DateTimeValidator.isEndAfterStart(start, end));
    }

    /**
     * Tests the isEndAfterStart method of the DateTimeValidator class with equal start and end times.
     * <p>
     * This test checks if the method correctly identifies an invalid range where the start time is equal to the end time.
     */
    @Test
    void isEndAfterStartInvalidEqual() {
        LocalDateTime start = LocalDateTime.parse("2021-06-01T12:00:00");
        LocalDateTime end = LocalDateTime.parse("2021-06-01T12:00:00");
        assertFalse(DateTimeValidator.isEndAfterStart(start, end));
    }
}