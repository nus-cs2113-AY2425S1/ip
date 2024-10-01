package melchizedek;

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
}
