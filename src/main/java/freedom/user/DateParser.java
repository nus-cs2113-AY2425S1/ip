package freedom.user;

import freedom.exceptions.InvalidDateTime;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;

/**
 * Class for converting dates and times between <code>LocalDateTime</code> and <code>String</code>.
 */
public class DateParser {
    private final static List<DateTimeFormatter> formatters = Arrays.asList(
            DateTimeFormatter.ofPattern("dd MMM yyyy HHmm"),
            DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm")
    );

    /**
     * Converts date and time in <code>String</code> format to <code>LocalDateTime</code> format.
     * <code>String</code> has to be in the format: dd/MM/yyyy HHmm
     *
     * @param dateTimeString date and time in <code>String</code> format.
     * @return date and time in <code>LocalDateTime</code> format.
     * @throws InvalidDateTime If <code>String</code> is not in an appropriate format.
     */
    public static LocalDateTime convertToDateTime(String dateTimeString) throws InvalidDateTime {
        LocalDateTime date;
        for (int i = 0; i < formatters.size(); i++) {
            try {
                date = LocalDateTime.parse(dateTimeString, formatters.get(i));
                return date;
            } catch (DateTimeParseException e) {
                continue;
            }
        }
        throw new InvalidDateTime();
    }

    /**
     * Converts date and time in <code>LocalDateTime</code> format to <code>String</code> format.
     *
     * @param dateTime date and time in <code>LocalDateTime</code> format.
     * @return date and time in <code>String</code> format.
     */
    public static String convertToString(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy HHmm");
        String dateTimeString;
        try {
            dateTimeString = dateTime.format(formatter);
            return dateTimeString;
        } catch (DateTimeException e) {
            return "No String";
        }
    }
}
