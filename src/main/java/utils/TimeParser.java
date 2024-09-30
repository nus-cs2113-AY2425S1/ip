package utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TimeParser {
    // Parses the any string to any date format if possible
    public static String parseTime(String time) {
        try {
            LocalDate date = LocalDate.parse(time);
            return date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        } catch (Exception e) {
            return time;
        }
    }
}
