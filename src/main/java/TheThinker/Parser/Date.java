package TheThinker.Parser;

import TheThinker.Tasks.Task;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Date {

    public static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
    public static DateTimeFormatter resultDateTimeFormatter = DateTimeFormatter.ofPattern("dd MMMM yyyy , h a");
    public static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static LocalDateTime parseIntoDateTimeObject(String date){
        return LocalDateTime.parse(date, dateTimeFormatter);
    }

    public static boolean isDateOneLater(String date1, String date2){
        LocalDateTime firstDate = parseIntoDateTimeObject(date1);
        LocalDateTime secondDate = parseIntoDateTimeObject(date2);
        return firstDate.isAfter(secondDate);
    }

    public static void printDate(String date) {
        LocalDateTime dateObject = parseIntoDateTimeObject(date);
        System.out.println(dateObject.format(resultDateTimeFormatter)); // -> Oct 15 2019
    }

    public static String toString(String date) {
        LocalDateTime dateObject = parseIntoDateTimeObject(date);
        return dateObject.format(resultDateTimeFormatter); // -> Oct 15 2019
    }

    public static String convertDateFormat(String date) {
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

    public static boolean isMatchingDate(String specifiedDate , String taskDate) {
        LocalDate parsedDate = LocalDate.parse(specifiedDate, dateFormatter);
        LocalDateTime parsedDateTime = LocalDateTime.parse(taskDate, resultDateTimeFormatter);
        return parsedDate.getMonthValue() == parsedDateTime.getMonthValue() &&
                parsedDate.getDayOfMonth() == parsedDateTime.getDayOfMonth() &&
                parsedDate.getYear() == parsedDateTime.getYear();
    }

    public static boolean isMatchingDateByType(Task task , String specifiedDate){
        if((task.getTaskType() == 'D' || task.getTaskType() == 'E') && isResultDateTimeFormat(task.getTaskDate())){
            return isMatchingDate(specifiedDate , task.getTaskDate());
        }
        return false;
    }

    public static boolean isDateOnlyFormat(String dateStr){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        try {
            LocalDate date = LocalDate.parse(dateStr, formatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public static boolean isDateTimeFormat(String dateStr){

        try {
            LocalDate date = LocalDate.parse(dateStr, dateTimeFormatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public static boolean isResultDateTimeFormat(String dateStr){

        try {
            LocalDate date = LocalDate.parse(dateStr, resultDateTimeFormatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
}
