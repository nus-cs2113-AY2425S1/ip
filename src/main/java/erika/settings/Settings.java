package erika.settings;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

public class Settings {
    public static final int TODO_LENGTH_OFFSET = 4;
    public static final int DEADLINE_LENGTH_OFFSET = 8;
    public static final int EVENT_LENGTH_OFFSET = 5;
    public static final int FROM_LENGTH_OFFSET = 5;
    public static final int TO_LENGTH_OFFSET = 3;
    public static final int BY_LENGTH_OFFSET = 3;
    public static final int BY_REAR_OFFSET = 0;
    public static final int FROM_REAR_OFFSET = 0;
    public static final int TO_REAR_OFFSET = 0;

    public static final String DIRECTORY = "data";
    public static final String FILENAME = DIRECTORY + "/tasks.txt";
    public static final String SEPARATOR = ",";

    public static final DateTimeFormatter DATE_IN_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    public static final DateTimeFormatter DATE_TIME_IN_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    public static final DateTimeFormatter DATE_OUT_FORMATTER = DateTimeFormatter.ofPattern("dd MMM yyyy", Locale.ENGLISH);
    public static final DateTimeFormatter DATE_TIME_OUT_FORMATTER = DateTimeFormatter.ofPattern("dd MMM yyyy h.mma", Locale.ENGLISH);

    public static LocalDate parseLocalDate(String date) throws DateTimeParseException{
        DateTimeFormatter[] formatters = new DateTimeFormatter[] {
                DateTimeFormatter.ofPattern("yyyy-MM-dd"),
                DateTimeFormatter.ofPattern("dd/MM/yyyy"),
                DateTimeFormatter.ofPattern("dd-MM-yyyy"),
                DateTimeFormatter.ofPattern("dd/MM/yy"),
                DateTimeFormatter.ofPattern("dd-MM-yy"),
                DateTimeFormatter.ofPattern("MMM dd yyyy", Locale.ENGLISH),
                DateTimeFormatter.ofPattern("MMM dd yyyy", Locale.GERMAN),
                DateTimeFormatter.ofPattern("dd MMM yyyy", Locale.ENGLISH),
                DateTimeFormatter.ofPattern("dd MMM yyyy", Locale.GERMAN),
                DateTimeFormatter.ofPattern("MMM dd yy", Locale.ENGLISH),
                DateTimeFormatter.ofPattern("MMM dd yy", Locale.GERMAN),
                DateTimeFormatter.ofPattern("dd MMM yy", Locale.ENGLISH),
                DateTimeFormatter.ofPattern("dd MMM yy", Locale.GERMAN)
        };
        for (DateTimeFormatter formatter : formatters) {
            try {
                return LocalDate.parse(date, formatter);
            } catch (DateTimeParseException e) {
                // Continue to the next formatter
            }
        }
        throw new DateTimeParseException("Could not parse date: " + date, date, 0);
    }

    public static LocalDateTime parseLocalDateTime(String dateTime) throws DateTimeParseException{
        DateTimeFormatter[] formatters = new DateTimeFormatter[] {
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"),
                DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"),
                DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"),
                DateTimeFormatter.ofPattern("dd/MM/yy HH:mm"),
                DateTimeFormatter.ofPattern("dd-MM-yy HH:mm"),
                DateTimeFormatter.ofPattern("MMM dd yyyy h.mma", Locale.ENGLISH),
                DateTimeFormatter.ofPattern("MMM dd yyyy h.mma", Locale.GERMAN),
                DateTimeFormatter.ofPattern("dd MMM yyyy h.mma", Locale.ENGLISH),
                DateTimeFormatter.ofPattern("dd MMM yyyy h.mma", Locale.GERMAN),
                DateTimeFormatter.ofPattern("MMM dd yy h.mma", Locale.ENGLISH),
                DateTimeFormatter.ofPattern("MMM dd yy h.mma", Locale.GERMAN),
                DateTimeFormatter.ofPattern("dd MMM yy h.mma", Locale.ENGLISH),
                DateTimeFormatter.ofPattern("dd MMM yy h.mma", Locale.GERMAN)
        };
        for (DateTimeFormatter formatter : formatters) {
            try {
                return LocalDateTime.parse(dateTime, formatter);
            } catch (DateTimeParseException e) {
                // Continue to the next formatter
            }
        }
        throw new DateTimeParseException("Could not parse date: " + dateTime, dateTime, 0);
    }
}
