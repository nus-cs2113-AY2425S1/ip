package joe;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;

/**
 * Encapsulates all methods for parsing user input Strings into LocalDateTime objects
 * and for formatting LocalDateTime objects back into Strings for the UI
 */
public class TimeParser {

    private static DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HHmm");

    /**
     * Parses an Array of two Strings into a LocalDateTime object
     * @param dateStrings an Array of Strings of the format [YYYY-MM-DD, HHmm] or [tomorrow, HHmm] or [today, HHmm]
     * @return Optional<LocalDateTime></LocalDateTime> the parsed LocalDateTime object.
     *     Empty if dateToken is neither today, tomorrow or in the YYYY-MM-DD format
     */
    public static Optional<LocalDateTime> extractDateTimeFromDouble(String[] dateStrings) {
        String dateToken = dateStrings[0];
        String timeToken = dateStrings[1];
        Optional<LocalDateTime> dateDate = extractDateTimeFromSingle(dateToken);
        LocalTime time = LocalTime.parse(timeToken, timeFormatter);
        return dateDate.map(date -> date.with(time));
    }

    /**
     * Parses an String of one token into a LocalDateTime object
     * @param dateString a string in the format 'HHmm' or 'tomorrow' or 'today' or 'YYYY-MM-DD'
     * @return Optional<LocalDateTime></LocalDateTime> the parsed DateTime object. If only the date was specified
     *     but no time, then the default time is set to 23:59 at the specified date.
     */
    public static Optional<LocalDateTime> extractDateTimeFromSingle(String dateString) {
        LocalDateTime dateDate;
        if (isTimeFormat(dateString)) {
            LocalTime time = LocalTime.parse(dateString, timeFormatter);
            dateDate = LocalDateTime.now().with(time);
        } else {
            switch (dateString) {
            case "today":
                dateDate = LocalDate.now().atTime(23, 59);
                break;
            case "tomorrow":
                dateDate = LocalDate.now().plusDays(1).atTime(23, 59);
                break;
            default:
                dateDate = LocalDate.parse(dateString).atTime(23, 59);
            }
        }
        return Optional.of(dateDate);
    }

    /**
     * Assess whether a string is in the HHmm format
     * @param input the user input String
     * @return boolean whether input is in the specified format.
     */
    public static boolean isTimeFormat(String input) {
        try {
            LocalTime time = LocalTime.parse(input, timeFormatter);
            return true;
        } catch (DateTimeParseException d) {
            return false;
        }
    }
}


