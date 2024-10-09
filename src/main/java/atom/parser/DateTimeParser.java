package atom.parser;

import atom.ui.Ui;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a parser to parse date and time.
 */
public class DateTimeParser {

    /**
     * Checks if the date time string passed in is in the correct format.
     * If the format is correct, proceeds to check if the date time
     * parameters are logical and valid.
     *
     * @param dateTime Date time string entered by user.
     * @param ui Ui object that handles printing of error messages.
     * @return <code>true</code> if date and time are valid, <code>false</code> otherwise.
     * @throws ArrayIndexOutOfBoundsException If date time format is invalid.
     * @throws NumberFormatException If date and/or time parameters are invalid.
     */
    public static boolean isValidDateTime(String dateTime, Ui ui) throws
            ArrayIndexOutOfBoundsException, NumberFormatException{
        //format example: "12/11/2024 17:30"
        dateTime = dateTime.trim();
        String[] dateTimeParams = dateTime.split(" ");

        String[] timeParams = dateTimeParams[1].split(":");
        String hourString = timeParams[0].trim();
        String minString = timeParams[1].trim();

        String[] dateParams = dateTimeParams[0].split("/");
        String dayString = dateParams[0].trim();
        String monthString = dateParams[1].trim();
        String yearString = dateParams[2].trim();

        int hour = Integer.parseInt(hourString);
        int min = Integer.parseInt(minString);

        int day = Integer.parseInt(dayString);
        int month = Integer.parseInt(monthString);
        int year = Integer.parseInt(yearString);

        boolean isValidDate = isValidDay(day, ui) && isValidMonth(month, ui) && isValidYear(year, ui);
        boolean isValidTime = isValidHour(hour, ui) && isValidMinute(min, ui);

        return isValidDate && isValidTime;
    }

    /**
     * Checks if the date string passed in is in the correct format.
     * If the format is correct, proceeds to check if the date
     * parameters are logical and valid.
     *
     * @param date Date string entered by user.
     * @param ui Ui object that handles printing of error messages.
     * @return <code>true</code> if date is valid, <code>false</code> otherwise.
     * @throws ArrayIndexOutOfBoundsException If date format is invalid.
     * @throws NumberFormatException If date parameters are invalid.
     */
    public static boolean isValidDate(String date, Ui ui) throws
            ArrayIndexOutOfBoundsException, NumberFormatException{
        //format example: "12/11/2024"
        date = date.trim();

        String[] dateParams = date.split("/");
        String dayString = dateParams[0].trim();
        String monthString = dateParams[1].trim();
        String yearString = dateParams[2].trim();

        int day = Integer.parseInt(dayString);
        int month = Integer.parseInt(monthString);
        int year = Integer.parseInt(yearString);

        boolean isValidDate = isValidDay(day, ui) && isValidMonth(month, ui) && isValidYear(year, ui);
        return isValidDate;
    }

    /**
     * Checks if day parameter of date is valid.
     *
     * @param day Day parameter of date.
     * @param ui Ui object that handles printing of error messages.
     * @return <code>true</code> if day is valid, <code>false</code> otherwise.
     */
    private static boolean isValidDay(int day, Ui ui) {
        if (day < 1 || day > 31) {
            ui.showInvalidDayMessage();
            return false;
        }
        return true;
    }

    /**
     * Checks if month parameter of date is valid.
     *
     * @param month Month parameter of date.
     * @param ui Ui object that handles printing of error messages.
     * @return <code>true</code> if month is valid, <code>false</code> otherwise.
     */
    private static boolean isValidMonth(int month, Ui ui) {
        if (month < 1 || month > 12) {
            ui.showInvalidMonthMessage();
            return false;
        }
        return true;
    }

    /**
     * Checks if year parameter of date is valid.
     *
     * @param year Year parameter of date.
     * @param ui Ui object that handles printing of error messages.
     * @return <code>true</code> if year is valid, <code>false</code> otherwise.
     */
    private static boolean isValidYear(int year, Ui ui) {
        if (year < 2024) {
            ui.showInvalidYearMessage();
            return false;
        }
        return true;
    }

    /**
     * Checks if hour parameter of time is valid.
     *
     * @param hour Hour parameter of time.
     * @param ui Ui object that handles printing of error messages.
     * @return <code>true</code> if hour is valid, <code>false</code> otherwise.
     */
    private static boolean isValidHour(int hour, Ui ui) {
        if (hour < 0 || hour > 24) {
            ui.showInvalidHourMessage();
            return false;
        }
        return true;
    }

    /**
     * Checks if minute parameter of time is valid.
     *
     * @param min Minute parameter of time.
     * @param ui Ui object that handles printing of error messages.
     * @return <code>true</code> if min is valid, <code>false</code> otherwise.
     */
    private static boolean isValidMinute(int min, Ui ui) {
        if (min < 0 || min > 60) {
            ui.showInvalidMinuteMessage();
            return false;
        }
        return true;
    }

    /**
     * Parses a valid date time string and stores it in a different format.
     *
     * @param dateTime Valid date time string.
     * @return parsed version of the date time string.
     * @throws DateTimeParseException If date time string does not conform to the format
     * specified by the <code>DateTimeFormatter</code> formatter.
     */
    public static String parseDateTime(String dateTime) throws DateTimeParseException {
        //format example: "12/11/2024 17:30"
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        LocalDateTime localDateTime = LocalDateTime.parse(dateTime, formatter);
        String parsedDateTime = localDateTime.format(DateTimeFormatter.ofPattern("dd MMM yyyy, hh:mma"));
        return parsedDateTime;
    }

    /**
     * Parses a valid date string and stores it in a different format.
     *
     * @param date Valid date string.
     * @return parsed version of the date string.
     * @throws DateTimeParseException If date string does not conform to the format
     * specified by the <code>DateTimeFormatter</code> formatter.
     */
    public static String parseDate(String date) throws DateTimeParseException {
        //format example: "12/11/2024"
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate localDate = LocalDate.parse(date, formatter);
        String parsedDate = localDate.format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
        return parsedDate;
    }
}
