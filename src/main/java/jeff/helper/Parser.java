package jeff.helper;

import jeff.command.*;
import java.io.IOException;

/**
 * A utility class responsible for parsing user input commands and creating
 * the corresponding command objects. The <code>Parser</code> class
 * interprets the first word of a user's input to determine the type of
 * command to execute.
 */
public class Parser {

    /**
     * Returns the first word of a given string.
     *
     * @param line The input string from which the first word is extracted.
     * @return The first word of the input string, or the entire string if
     *         it contains no spaces.
     */
    private static String processLine(String line) {
        int firstSpace = line.indexOf(" ");
        if (firstSpace != -1) {
            return line.substring(0, firstSpace);
        } else {
            return line; // In case there's only one word with no spaces
        }
    }

    /**
     * Parses the input line and returns the corresponding command object.
     * The method identifies the first word of the input to determine which
     * command to create and return.
     *
     * @param line The input string containing the command.
     * @return A <code>Command</code> object representing the user's input.
     */
    public static Command parse(String line) {
        //Getting first word of user input
        String firstWord = processLine(line);

        //Carry out different actions based on the first word
        switch (firstWord) {
        case "todo":
        case "deadline":
        case "event":
            return new AddCommand(firstWord, line);
        case "list":
            return new ListCommand(firstWord, line);
        case "mark":
        case "unmark":
            return new MarkCommand(firstWord, line);
        case "delete":
            return new DeleteCommand(firstWord, line);
        case "bye":
            return new ExitCommand();
        default:
            return new InvalidCommand();
        }
    }
}
