package yapper.io;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * A utility class for handling date and time formatting and parsing in Yapper.
 *
 * <p>
 * This class provides predefined format patterns for displaying and storing dates and times,
 * as well as a method to format dates based on user input or internal representation.
 * </p>
 */
public class DateAndTimeHandler {
    /**
     * Date and time format patterns for display purposes.
     */
    public static final String DATE_WITHOUT_TIME_TO_DISPLAY = "MM/dd/yyyy";
    public static final String DATE_WITH_TIME_TO_DISPLAY = "MM/dd/yyyy, EEE, hh:mm a";
    /**
     * Date and time format patterns for converting to string, for storage purposes.
     */
    public static final String DATE_WITHOUT_TIME_TO_STRING = "yyyy-dd-MM";
    public static final String DATE_WITH_TIME_TO_STRING = "yyyy-dd-MM hhmm";
    /**
     * Date and time format patterns for parsing input from the user.
     */
    public static final String DATE_WITHOUT_TIME_INPUT = "yyyy-MM-dd";
    public static final String DATE_WITH_TIME_INPUT = "yyyy-MM-dd HHmm";

    /**
     * Returns the formatted date or datetime string based on the input parameters.
     *
     * <p>
     * If the provided {@code dateString} is not empty, it is returned as is.
     * Otherwise, it formats either the {@code dateWithoutTime} or {@code dateWithTime}
     * based on the provided format patterns.
     * </p>
     *
     * @param dateString    The original date string. If not empty, this will be returned.
     * @param dateWithoutTime A {@link LocalDate} instance, used if no time is provided.
     * @param dateWithTime  A {@link LocalDateTime} instance, used if time is provided.
     * @param dateOnly      The format pattern to use if only the date is provided.
     * @param dateAndTime   The format pattern to use if both date and time are provided.
     * @return The formatted date or datetime string.
     */
    public static String getDateTimeFromString(
            String dateString, LocalDate dateWithoutTime, LocalDateTime dateWithTime,
            String dateOnly, String dateAndTime) {

        String endDateAsString;
        if (dateString.isEmpty()) {
            if (dateWithTime == null) {
                endDateAsString = dateWithoutTime.format(
                        DateTimeFormatter.ofPattern(dateOnly));
            } else {
                endDateAsString = dateWithTime.format(
                        DateTimeFormatter.ofPattern(dateAndTime));
            }
        } else {
            endDateAsString = dateString;
        }
        return endDateAsString;
    }

}
