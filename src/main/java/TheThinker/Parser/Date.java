package TheThinker.Parser;

import TheThinker.Tasks.Task;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public interface Date {

    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
    DateTimeFormatter resultDateTimeFormatter = DateTimeFormatter.ofPattern("dd MMMM yyyy , h a");
    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private static LocalDateTime parseIntoDateTimeObject(String date){
        return LocalDateTime.parse(date, dateTimeFormatter);
    }

    private static String toString(String date) {
        LocalDateTime dateObject = parseIntoDateTimeObject(date);
        return dateObject.format(resultDateTimeFormatter); // -> Oct 15 2019
    }

    /**
     * Converts date from format of dd/MM/yyyy HHmm to dd MMMM yyyy , h a.
     * If date is not of dd/MM/yyyy HHmm format, the original string is returned.
     *
     * @param date deadline of task extracted from user input
     * @return date of string type with format e.g. 15 October 2019 , 6 pm.
     *
     */
    static String convertDateFormat(String date) {
        String[] dateParameters = date.split("/");
        if(dateParameters.length != 3 || !isDateTimeFormat(date)) {
            return date;
        }

        if(isValidDay(dateParameters[0]) != -1 && isValidMonth(dateParameters[1]) != -1){
            return toString(date);
        }
        return date;

    }

    private static int isValidMonth(String month) {
        int monthInt = Integer.parseInt(month);
        if(monthInt < 1 || monthInt > 12) {
            return -1;
        }
        return monthInt;
    }

    private static int isValidDay(String day) {
        int dayInt = Integer.parseInt(day);
        if(dayInt < 1 || dayInt > 30) {
            return -1;
        }
        return dayInt;
    }

    /**
     * Returns boolean if day , month and year of both parameters are same , ignoring time.
     *
     * @param specifiedDate date in format of dd/MM/yyyy.
     * @param taskDate date in format of dd MMMM yyyy , h a.
     * @return boolean
     *
     */
    private static boolean isMatchingDate(String specifiedDate , String taskDate) {
        LocalDate parsedDate = LocalDate.parse(specifiedDate, dateFormatter);
        LocalDateTime parsedDateTime = LocalDateTime.parse(taskDate, resultDateTimeFormatter);
        return parsedDate.getMonthValue() == parsedDateTime.getMonthValue() &&
                parsedDate.getDayOfMonth() == parsedDateTime.getDayOfMonth() &&
                parsedDate.getYear() == parsedDateTime.getYear();
    }

    /**
     * Returns true if day , month and year matches according to deadlines by task type.
     * If task type is not event or deadline, return false.
     *
     * @param task Object of type task and subclasses.
     * @param specifiedDate date in format of dd/MM/yyyy.
     * @return boolean
     */
    static boolean isMatchingDateByType(Task task , String specifiedDate){
        if((task.getTaskType() == 'D' || task.getTaskType() == 'E') && isResultDateTimeFormat(task.getTaskDate())){
            return isMatchingDate(specifiedDate , task.getTaskDate());
        }
        return false;
    }

    static boolean isDateOnlyFormat(String dateStr){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        try {
            LocalDate date = LocalDate.parse(dateStr, formatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    static boolean isDateTimeFormat(String dateStr){

        try {
            LocalDate date = LocalDate.parse(dateStr, dateTimeFormatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    static boolean isResultDateTimeFormat(String dateStr){

        try {
            LocalDate date = LocalDate.parse(dateStr, resultDateTimeFormatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
}
