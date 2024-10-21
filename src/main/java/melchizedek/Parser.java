package melchizedek;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

/**
 * Class to manage parsing of user inputs.
 */
public class Parser {

    /**
     * Method that splits the user input based on spaces.
     *
     * @param input User input
     * @return String[] of the split user input
     */
    public static String[] parseInput(String input) {
        return input.split(" ");
    }

    /**
     * Method that joins a String array into a String.
     *
     * @param array String array
     * @param from Starting index
     * @param to End index
     * @param delimiter Separator to be inserted between each element of the array
     * @return The joined String
     */
    public static String joinStringArray(String[] array, int from, int to, String delimiter) {
        String[] arrayCopy = Arrays.copyOfRange(array, from, to);
        return String.join(delimiter, arrayCopy);
    }

    /**
     * Method that joins a String array into a String.
     *
     * @param array String array
     * @param delimiter Separator to be inserted between each element of the array
     * @return The joined String
     */
    public static String joinStringArray(String[] array, String delimiter) {
        return String.join(delimiter, array);
    }

    /**
     * Method that parses String into LocalDate.
     *
     * @param stringDate Date in String type
     * @return Date in LocalDate type
     */
    public static LocalDate parseDate(String stringDate) {
        return LocalDate.parse(stringDate);
    }

    /**
     * Method that parses LocalDate into printable String format.
     *
     * @param localDate Date in LocalDate type
     * @return Date in print format
     */
    public static String stringDate(LocalDate localDate) {
        return localDate.format(DateTimeFormatter.ofPattern("d MMM yyyy"));
    }

    /**
     * Method that parses LocalDate into File storing format.
     *
     * @param localDate Date in LocalDate type
     * @return Date in File format
     */
    public static String stringDateToFile(LocalDate localDate) {
        return localDate.toString();
    }

    /**
     * Method that parses String into LocalTime.
     *
     * @param stringTime Time in String type
     * @return Time in LocalTime type
     */
    public static LocalTime parseTime(String stringTime) {
        return LocalTime.parse(stringTime);
    }

    /**
     * Method that parses LocalTime into printable String format.
     *
     * @param localTime Time in LocalTime type
     * @return Time in print format
     */
    public static String stringTime(LocalTime localTime) {
        return localTime.format(DateTimeFormatter.ofPattern("h:mm a"));
    }

    /**
     * Method that parses LocalTime into File storing format.
     *
     * @param localTime Time in LocalTime type
     * @return Time in File format
     */
    public static String stringTimeToFile(LocalTime localTime) {
        return localTime.toString();
    }

}
