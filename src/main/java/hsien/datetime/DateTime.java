package hsien.datetime;

import hsien.exception.HsienException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a DateTime utility for checking and formatting date and time.
 */
public class DateTime
{
    /**
     * Checks if the given dateTime string is in the correct format.
     *
     * @param dateTime the date and time string to check
     * @throws HsienException if the dateTime is not in the correct format
     */
    public void checkDate(String dateTime) throws HsienException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            LocalDateTime.parse(dateTime, formatter);
        } catch (DateTimeParseException e) {
            throw new HsienException("Please enter date in proper format! (YYYY-MM-DD HHMM)");
        }
    }

    /**
     * Formats the given dateTime string from the input format to a more readable format.
     *
     * @param dateTime the date and time string to format
     * @return a formatted date and time string in the format "d MMM yyyy HHmm"
     */
    public String formatDate(String dateTime) {
        LocalDateTime date = LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        return date.format(DateTimeFormatter.ofPattern("d MMM yyyy HHmm"));
    }
}
