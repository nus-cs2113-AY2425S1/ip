package akshan.handler;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;

public class DateTime {
    private static final DateTimeFormatter DATE_ONLY_OUTPUT_FORMATTER =
            DateTimeFormatter.ofPattern("MMM dd yyyy");

    private static final DateTimeFormatter DATE_TIME_OUTPUT_FORMATTER =
            DateTimeFormatter.ofPattern("MMM dd yyyy hh.mma");

    private static final List<DateTimeFormatter> INPUT_FORMATTERS = Arrays.asList(
        // ISO formats
        DateTimeFormatter.ISO_LOCAL_DATE,
        DateTimeFormatter.ISO_LOCAL_DATE_TIME,

        // Custom formats in order of priority
        DateTimeFormatter.ofPattern("yyyy-MM-dd[ HH:mm]"),
        DateTimeFormatter.ofPattern("yyyy/MM/dd[ HH:mm]"),
        DateTimeFormatter.ofPattern("dd-MM-yyyy[ HH:mm]"),
        DateTimeFormatter.ofPattern("dd/MM/yyyy[ HH:mm]"),
        DateTimeFormatter.ofPattern("MM-dd-yyyy[ HH:mm]"),
        DateTimeFormatter.ofPattern("MM/dd/yyyy[ HH:mm]"),
        DateTimeFormatter.ofPattern("d-MM-yyyy[ HH:mm]"),
        DateTimeFormatter.ofPattern("d/MM/yyyy[ HH:mm]"),

        DateTimeFormatter.ofPattern("yyyy MMM dd[ HH:mm]"),
        DateTimeFormatter.ofPattern("dd MMM yyyy[ HH:mm]"),
        DateTimeFormatter.ofPattern("MMM dd yyyy[ HH:mm]"),

        DateTimeFormatter.ofPattern("yyyy-MMM-dd[ HH:mm]"),
        DateTimeFormatter.ofPattern("dd-MMM-yyyy[ HH:mm]"),
        DateTimeFormatter.ofPattern("MMM-dd-yyyy[ HH:mm]"),

        // 12-hour formats
        DateTimeFormatter.ofPattern("yyyy-MM-dd[ hh:mma]"),
        DateTimeFormatter.ofPattern("yyyy/MM/dd[ hh:mma]"),
        DateTimeFormatter.ofPattern("dd-MM-yyyy[ hh:mma]"),
        DateTimeFormatter.ofPattern("dd/MM/yyyy[ hh:mma]"),
        DateTimeFormatter.ofPattern("MM-dd-yyyy[ hh:mma]"),
        DateTimeFormatter.ofPattern("MM/dd/yyyy[ hh:mma]"),
        DateTimeFormatter.ofPattern("d-MM-yyyy[ HH:mma]"),
        DateTimeFormatter.ofPattern("d/MM/yyyy[ HH:mma]"),

        DateTimeFormatter.ofPattern("yyyy MMM dd[ hh:mma]"),
        DateTimeFormatter.ofPattern("dd MMM yyyy[ hh:mma]"),
        DateTimeFormatter.ofPattern("MMM dd yyyy[ hh:mma]"),

        DateTimeFormatter.ofPattern("yyyy-MMM-dd[ hh:mma]"),
        DateTimeFormatter.ofPattern("dd-MMM-yyyy[ hh:mma]"),
        DateTimeFormatter.ofPattern("MMM-dd-yyyy[ hh:mma]")
    );

    /**
     * If string is a valid date/time or date, returns string in appropriate date/time format.
     * Else, return the original string.
     *
     * @param input The input string.
     * @return The processed string.
     */
    public static String convertToString(String input) {
        String tempString = input;
        for (DateTimeFormatter formatter : INPUT_FORMATTERS) {
            try {
                LocalDate date = LocalDate.parse(input, formatter);
                tempString = date.atStartOfDay().format(DATE_ONLY_OUTPUT_FORMATTER);
                break;
            } catch (DateTimeParseException e) {
                try {
                    LocalDateTime dateTime = LocalDateTime.parse(input, formatter);
                    tempString = dateTime.format(DATE_TIME_OUTPUT_FORMATTER);
                } catch (DateTimeParseException e2) {
                    tempString = input;
                }
            }
        }
        return tempString;
    }
}
