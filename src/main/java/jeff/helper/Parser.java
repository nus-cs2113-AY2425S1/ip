package jeff.helper;

import jeff.command.*;

/**
 * A utility class responsible for parsing user input commands and creating
 * the corresponding command objects. The <code>Parser</code> class
 * interprets the first word of a user's input to determine the type of
 * command to execute.
 */
public class Parser {

    private static final String TODO_COMMAND = "todo";
    private static final String DEADLINE_COMMAND = "deadline";
    private static final String EVENT_COMMAND = "event";
    private static final String MARK_COMMAND = "mark";
    private static final String UNMARK_COMMAND = "unmark";
    private static final String LIST_COMMAND = "list";
    private static final String DELETE_COMMAND = "delete";
    private static final String FIND_COMMAND = "find";
    private static final String BYE_COMMAND = "bye";

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
        case TODO_COMMAND:
        case DEADLINE_COMMAND:
        case EVENT_COMMAND:
            return new AddCommand(firstWord, line);
        case LIST_COMMAND:
            return new ListCommand(firstWord, line);
        case MARK_COMMAND:
        case UNMARK_COMMAND:
            return new MarkCommand(firstWord, line);
        case DELETE_COMMAND:
            return new DeleteCommand(firstWord, line);
        case FIND_COMMAND:
            return new FindCommand(firstWord, line);
        case BYE_COMMAND:
            return new ExitCommand();
        default:
            return new InvalidCommand();
        }
    }
}
