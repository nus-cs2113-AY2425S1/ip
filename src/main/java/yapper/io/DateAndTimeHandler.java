package yapper.io;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateAndTimeHandler {
    public static final String DATE_WITHOUT_TIME_TO_DISPLAY = "MM/dd/yyyy";
    public static final String DATE_WITH_TIME_TO_DISPLAY = "MM/dd/yyyy, EEE, hh:mm a";
    public static final String DATE_WITHOUT_TIME_TO_STRING = "yyyy-dd-MM";
    public static final String DATE_WITH_TIME_TO_STRING = "yyyy-dd-MM hhmm";
    public static final String DATE_WITHOUT_TIME_INPUT = "yyyy-MM-dd";
    public static final String DATE_WITH_TIME_INPUT = "yyyy-MM-dd HHmm";

    public static String getDateTimeFromString(
            String dateString, LocalDate dateWithoutTime, LocalDateTime dateWithTime,
            String dateOnly, String dateAndTime) {

        String endDateAsString;
        if ( dateString.isEmpty() ) {
            if (dateWithTime == null) {
                endDateAsString = dateWithoutTime.format(
                        DateTimeFormatter.ofPattern(dateOnly) );
            } else {
                endDateAsString = dateWithTime.format(
                        DateTimeFormatter.ofPattern(dateAndTime) );
            }
        } else {
            endDateAsString = dateString;
        }
        return endDateAsString;
    }

}
