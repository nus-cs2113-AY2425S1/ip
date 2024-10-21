package bosco.parser;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import java.util.Arrays;
import java.util.List;

/**
 * Represents a date time parser that parses strings into LocalDateTime objects,
 * and formats LocalDateTime objects into strings for UI output.
 */
public class DateTimeParser {
    // List of acceptable patterns for date-time input
    private static final List<DateTimeFormatter> dateTimeFormatters = Arrays.asList(
            DateTimeFormatter.ofPattern("MMM dd yyyy h.mma"),
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"),
            DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"),
            DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm"));
    // List of acceptable patterns for date only input
    private static final List<DateTimeFormatter> dateOnlyFormatters = Arrays.asList(
            DateTimeFormatter.ofPattern("MMM dd yyyy"),
            DateTimeFormatter.ofPattern("yyyy-MM-dd"),
            DateTimeFormatter.ofPattern("dd/MM/yyyy"),
            DateTimeFormatter.ofPattern("MM-dd-yyyy"));

    /**
     * Parses input string into LocalDateTime object according to provided patterns.
     * If hour and minute not provided, the method autofills with 23:59.
     *
     * @param input String of datetime to be parsed.
     * @return LocalDateTime object.
     * @throws IllegalArgumentException If input string doesn't match any of the provided patterns.
     */
    public static LocalDateTime parseDateTime(String input) {
        for (DateTimeFormatter formatter: dateTimeFormatters) {
            try {
                return LocalDateTime.parse(input, formatter);
            } catch (DateTimeException e) {
                // If input doesn't match current formatter, ignore and try next formatter
            }
        }
        for (DateTimeFormatter formatter: dateOnlyFormatters) {
            try {
                LocalDate date = LocalDate.parse(input, formatter);
                return date.atTime(LocalTime.MAX);
            } catch (DateTimeParseException e) {
                // If input doesn't match current formatter, ignore and try next formatter
            }
        }
        throw new IllegalArgumentException();
    }

    /**
     * Formats the LocalDateTime object into a string.
     *
     * @param dateTime LocalDateTime object to be formatted.
     * @return String representation with format "MMM dd yyyy h.mma".
     */
    public static String formatDateTime(LocalDateTime dateTime) {
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy h.mma");
        return dateTime.format(outputFormatter);
    }
}
