package parser;

import command.*;
import exception.MondayException;

/**
 * Parses user input commands and returns the appropriate Command object.
 */
public class Parser {
    /**
     * Parses the input string and returns the corresponding Command.
     *
     * @param input the input command string
     * @return the Command object corresponding to the input
     * @throws MondayException if the input is invalid
     */
    public static Command parse(String input) throws MondayException {
        if (input.equals("list")) {
            return new ListCommand();
        } else if (input.startsWith("mark ")) {
            return new MarkCommand(input);
        } else if (input.startsWith("unmark ")) {
            return new UnmarkCommand(input);
        } else if (input.startsWith("delete ")) {
            return new DeleteCommand(input);
        } else if (input.startsWith("find ")) {
            String keyword = input.substring(5).trim();
            return new FindCommand(keyword);
        } else if (input.startsWith("todo ") || input.startsWith("deadline ") || input.startsWith("event ")) {
            return new AddCommand(input);
        } else if (input.equals("bye")) {
            return new ExitCommand();
        } else {
            throw new MondayException(" Invalid command. Please try again.");
        }
    }
}
