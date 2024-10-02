package freedom.user;

import freedom.exceptions.InvalidDateTime;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;

public class DateParser {
    private final static List<DateTimeFormatter> formatters = Arrays.asList(
            DateTimeFormatter.ofPattern("dd MMM yyyy HHmm"),
            DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm")
    );

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
