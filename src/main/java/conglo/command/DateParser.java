package conglo.command;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Utility class for parsing and formatting dates and times.
 */
public class DateParser {

    /**
     * Parses a date and time string into a {@link LocalDateTime} object.
     * The input string should follow the format "dd-MM-yyyy HHmm", where:
     * <ul>
     *     <li><b>dd</b>: Day of the month (e.g., 01 to 31)</li>
     *     <li><b>MM</b>: Month of the year (e.g., 01 for January, 12 for December)</li>
     *     <li><b>yyyy</b>: Year (e.g., 2023)</li>
     *     <li><b>HHmm</b>: Time in 24-hour format (e.g., 2359 for 11:59 PM)</li>
     * </ul>
     *
     * @param dateStr The date and time string to be parsed.
     * @return A {@link LocalDateTime} object representing the parsed date and time.
     * @throws DateTimeParseException If the input string is not in the correct format.
     */
    public static LocalDateTime parseDateTime(String dateStr) throws DateTimeParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
        return LocalDateTime.parse(dateStr, formatter);
    }

    /**
     * Formats a {@link LocalDateTime} object into a readable date and time string.
     * The formatted output will be in the format "d MMM yyyy hh:mm a", where:
     * <ul>
     *     <li><b>d MMM yyyy</b>: Day (without leading zero), month abbreviation, and year (e.g., 1 Jan 2023)</li>
     *     <li><b>hh:mm a</b>: Time in 12-hour format with AM/PM notation (e.g., 12:30 PM)</li>
     * </ul>
     *
     * @param dateTime The {@link LocalDateTime} object to be formatted.
     * @return A string representing the formatted date and time.
     */
    public static String formatDateTime(LocalDateTime dateTime) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("d MMM yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh:mm a");

        String formattedDate = dateTime.format(dateFormatter);
        String formattedTime = dateTime.format(timeFormatter);

        return formattedDate + " " + formattedTime;
    }
}
