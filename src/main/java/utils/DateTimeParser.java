package utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// // public class TimeParser {
//     // Parses the any string to any date format if possible
//     public static String parseTime(String time) {
//         try {
//             LocalDateTime date = LocalDateTime.parse(time, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
//             return date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
//         } catch (Exception e) {
//             return time;
//         }
//     }
// }
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class DateTimeParser {

    // list of supported date time formats
    private static List<DateTimeFormatter> formatters = new ArrayList<>();

    static {
        formatters.add(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        formatters.add(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        formatters.add(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        formatters.add(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        formatters.add(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"));
        formatters.add(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
        formatters.add(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        formatters.add(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm"));
        formatters.add(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
    }

    // 嘗試解析時間字串
    public static String parseDateTime(String dateTimeString) {
        for (DateTimeFormatter formatter : formatters) {
            try {
                LocalDateTime dateTime = LocalDateTime.parse(dateTimeString, formatter);
                return dateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm:ss"));
            } catch (DateTimeParseException e) {
                // cannot parse, try next format
            }
        }
        // cannot parse with any format
        return dateTimeString;
    }
}