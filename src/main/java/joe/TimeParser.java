package joe;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;

public class TimeParser {

    private static DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HHmm");

    public static Optional<LocalDateTime> extractDateWithTime(String[] deadlineStrings) {
        String dateToken = deadlineStrings[0];
        String timeToken = deadlineStrings[1];
        Optional<LocalDateTime> dateDate = extractDateWithoutTime(dateToken);
        LocalTime time = LocalTime.parse(timeToken, timeFormatter);
        return dateDate.map(date -> date.with(time));
    }

    public static Optional<LocalDateTime> extractDateWithoutTime(String dateString) {
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

    public static boolean isTimeFormat(String input) {
        try {
            LocalTime time = LocalTime.parse(input, timeFormatter);
            return true;
        } catch (DateTimeParseException d) {
            return false;
        }
    }
}


