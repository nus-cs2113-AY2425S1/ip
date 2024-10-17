package niwa.utils;

import niwa.exception.NiwaException;
import niwa.messages.NiwaExceptionMessages;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Utility class for handling date-time parsing and format validation.
 */
public class NiwaUtils {

    // DateTimeFormatter for reading date-time strings in the specified format.
    private static final DateTimeFormatter DATETIME_READ_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    // DateTimeFormatter for printing date-time strings in a user-friendly format.
    private static final DateTimeFormatter DATETIME_PRINT_FORMAT
            = DateTimeFormatter.ofPattern("EEEE, yyyy-MM-dd hh.mm a");

    /**
     * Checks if the input string matches the specified format.
     *
     * @param input The input string to be checked.
     * @param format The regex format to match against the input.
     * @return true if the input matches the format; false otherwise.
     */
    public static boolean isMatch(String input, String format) {
        // Compile the regex pattern for matching the command format
        Pattern pattern = Pattern.compile(format);

        // Create a matcher for the input command string
        Matcher matcher = pattern.matcher(input);

        return matcher.matches(); // Return the result of the match
    }

    /**
     * Parses a date-time string into a LocalDateTime object.
     *
     * @param datetime The date-time string to be parsed.
     * @return A LocalDateTime representing the parsed date and time.
     * @throws NiwaException If the date-time format is invalid.
     */
    public static LocalDateTime parseDateTime(String datetime) throws NiwaException {
        String[] datetimeParts = datetime.trim().split(" ", 2);

        // If only the date is provided, append time as "2359" (11:59 PM)
        if (datetimeParts.length == 1) {
            datetime += " 2359";
        }

        LocalDateTime result;
        try {
            result = LocalDateTime.parse(datetime, DATETIME_READ_FORMAT); // Parse the date-time string
        } catch (DateTimeParseException e) {
            throw new NiwaException(NiwaExceptionMessages.MESSAGE_INVALID_DATE_FORMAT); // Throw custom exception
        }

        return result;
    }

    /**
     * Formats a LocalDateTime object into a user-friendly string.
     *
     * @param dateTime The LocalDateTime to format.
     * @return A formatted string representing the date and time.
     */
    public static String getDateTimeString(LocalDateTime dateTime) {
        return dateTime.format(DATETIME_PRINT_FORMAT); // Format and return the date-time string
    }

    /**
     * Formats a LocalDateTime object into a string suitable for saving.
     *
     * @param dateTime The LocalDateTime to format.
     * @return A formatted string representing the date and time for saving.
     */
    public static String getDateTimeSaveString(LocalDateTime dateTime) {
        return dateTime.format(DATETIME_READ_FORMAT); // Format and return the save string
    }
}
