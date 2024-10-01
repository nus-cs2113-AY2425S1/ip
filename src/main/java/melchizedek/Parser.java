package melchizedek;

import java.util.Arrays;

public class Parser {
    public static String[] parseInput(String input) {
        return input.split(" ");
    }

    public static String joinStringArray(String[] array, int from, int to, String delimiter) {
        String[] arrayCopy = Arrays.copyOfRange(array, from, to);
        return String.join(delimiter, arrayCopy);
    }
}
