package niwa.utils;

import niwa.exception.NiwaException;
import niwa.messages.NiwaExceptionMessages;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NiwaUtils {
    public static boolean isMatch(String input, String format) {
        // Compile the regex pattern for matching the command format
        Pattern pattern = Pattern.compile(format);

        // Create a matcher for the input command string
        Matcher matcher = pattern.matcher(input);

        return matcher.matches();
    }

    private static final DateTimeFormatter DATETIME_READ_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static final DateTimeFormatter DATETIME_PRINT_FORMAT = DateTimeFormatter.ofPattern("EEEE, yyyy-MM-dd hh.mm a");
    public static LocalDateTime parseDateTime (String datetime) throws NiwaException {
        String[] datetimeParts = datetime.trim().split(" ", 2);

        if (datetimeParts.length == 1) {
            datetime = datetime + " 2359";
        }

        LocalDateTime result;
        try {
            result = LocalDateTime.parse(datetime, DATETIME_READ_FORMAT);
        } catch (DateTimeParseException e) {
            throw new NiwaException(NiwaExceptionMessages.MESSAGE_INVALID_DATE_FORMAT);
        }

        return result;
    }

    public static String getDateTimeString (LocalDateTime dateTime) {
        return dateTime.format(DATETIME_PRINT_FORMAT);
    }

    public static String getDateTimeSaveString (LocalDateTime dateTime) {
        return dateTime.format(DATETIME_READ_FORMAT);
    }
}
