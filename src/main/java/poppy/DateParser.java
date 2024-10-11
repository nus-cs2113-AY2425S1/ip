package poppy;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


/**
 * The {@code DateParser} class provides utility methods to parse and format
 * date and time strings into {@code LocalDateTime} objects.
 */
public class DateParser {
    /**
     * Parses a date and time string into a {@code LocalDateTime} object.
     * The input string must follow the format "yyyy-MM-dd HH:mm:ss".
     *
     * @param strDate The date and time string to be parsed.
     * @return A {@code LocalDateTime} object representing the parsed date and time.
     * @throws DateTimeParseException If the string cannot be parsed into a valid {@code LocalDateTime}.
     */
    public static LocalDateTime parseDateTime(String strDate) throws DateTimeParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.parse(strDate, formatter);
    }

    /**
     * Formats a {@code LocalDateTime} object into a string with separate date and time components.
     * The date is formatted as "yyyy-MM-dd" and the time is formatted as "HH:mm:ss".
     *
     * @param dateTime The {@code LocalDateTime} object to be formatted.
     * @return A string representing the formatted date and time.
     */
    public static String formatDateTime(LocalDateTime dateTime) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

        String formattedDate = dateTime.format(dateFormatter);
        String formattedTime = dateTime.format(timeFormatter);

        return formattedDate + " " + formattedTime;
    }
}
