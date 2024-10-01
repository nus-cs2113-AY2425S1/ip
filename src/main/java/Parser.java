import exceptions.IllegalCommandException;
import exceptions.IllegalEmptyException;
import exceptions.IllegalKeywordException;
import exceptions.IllegalTaskException;
import Ui.*;
import constants.Warnings;


public class Parser {

    public static void validateMark(String[] splitInputs, int count) throws IllegalTaskException {
        try {

            int index = Integer.parseInt(splitInputs[1]) - 1;

            if (index < 0 || index >= count) {
                throw new IllegalTaskException(Warnings.VALID_INDEX_WARNING + count);
            }

        } catch (NumberFormatException e) {
            throw new IllegalTaskException(Warnings.VALID_NUMBER_WARNING + count);
        }

    }

    public static String trimString(String input) throws IllegalEmptyException {
        String output = input.trim();

        // Split the input into two parts: the command and the description
        String[] outputSubstrings = output.split(" ", 2);

        // Check if the length is less than 2, meaning no description was provided
        if (outputSubstrings.length < 2 || outputSubstrings[1].trim().isEmpty()) {
            throw new IllegalEmptyException(Warnings.VALID_DESCRIPTION_WARNING);
        }

        // Return the trimmed description
        return outputSubstrings[1].trim();
    }
}

