package parser;

import command.*;
import exception.MondayException;

public class Parser {
    public static Command parse(String input) throws MondayException {
        if (input.equals("list")) {
            return new ListCommand();
        } else if (input.startsWith("mark ")) {
            return new MarkCommand(input);
        } else if (input.startsWith("unmark ")) {
            return new UnmarkCommand(input);
        } else if (input.startsWith("delete ")) {
            return new DeleteCommand(input);
        } else if (input.startsWith("todo ") || input.startsWith("deadline ") || input.startsWith("event ")) {
            return new AddCommand(input);
        } else if (input.equals("bye")) {
            return new ExitCommand();
        } else {
            throw new MondayException("Invalid command. Please try again.");
        }
    }
}
