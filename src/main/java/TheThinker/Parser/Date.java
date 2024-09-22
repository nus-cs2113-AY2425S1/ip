package TheThinker.Parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Date {

    public static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
    public static DateTimeFormatter resultDateTimeFormatter = DateTimeFormatter.ofPattern("dd MMMM yyyy , h a");

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
        if(dateParameters.length != 3) {
            return date;
        }

        if(isValidDay(dateParameters[0]) != -1 && isValidMonth(dateParameters[1]) != -1){
            return toString(date);
        }else{
            return date;
        }
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
}
