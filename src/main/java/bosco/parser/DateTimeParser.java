package bosco.parser;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import java.util.Arrays;
import java.util.List;

public class DateTimeParser {
    // List of acceptable patterns for date-time input
    private static final List<DateTimeFormatter> dateTimeFormatters = Arrays.asList(
            DateTimeFormatter.ofPattern("MMM dd yyyy h.mma"),
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"),
            DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"),
            DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm"));
    // List of acceptable patterns for date only input
    private static final List<DateTimeFormatter> dateOnlyFormatters = Arrays.asList(
            DateTimeFormatter.ofPattern("yyyy-MM-dd"),
            DateTimeFormatter.ofPattern("dd/MM/yyyy"),
            DateTimeFormatter.ofPattern("MM-dd-yyyy"));

    public static LocalDateTime parseDateTime(String input) {
        for (DateTimeFormatter formatter: dateTimeFormatters) {
            try {
                return LocalDateTime.parse(input, formatter);
            } catch (DateTimeException e) {
                // Ignore and try next formatter
            }
        }
        for (DateTimeFormatter formatter: dateOnlyFormatters) {
            try {
                LocalDate date = LocalDate.parse(input, formatter);
                return date.atTime(LocalTime.MAX);
            } catch (DateTimeParseException e) {
                // Ignore and try next formatter
            }
        }
        throw new IllegalArgumentException();
    }

    public static String formatDateTime(LocalDateTime dateTime) {
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy h.mma");
        return dateTime.format(outputFormatter);
    }
}
