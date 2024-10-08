/**
 * Utility class that handles the parsing of user input into commands and task indexes.
 */
public class Parser {

    /**
     * Parses the user input into a command and its arguments.
     * Splits the input into two parts: the command and the argument.
     */
    public static String[] parseCommand(String input) {
        return input.split(" ", 2);
    }

    /**
     * Parses the user input to extract the task index.
     * Converts the task index string to an integer and adjusts it to a zero-based index.
     */
    public static int parseTaskIndex(String input) throws KaiException {
        try {
            return Integer.parseInt(input.split(" ")[1]) - 1;
        } catch (NumberFormatException e) {
            throw new KaiException("Please enter a valid task number.");
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new KaiException("You must specify a task number.");
        }
    }
}

