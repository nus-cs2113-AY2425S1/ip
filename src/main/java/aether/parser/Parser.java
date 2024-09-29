package aether.parser;

import aether.command.*;
import aether.DukeException;

/**
 * Parses user commands and returns the appropriate Command object.
 */
public class Parser {

    public static Command parseCommand(String command) throws DukeException {
        String[] commandParts = command.split("\\s+", 2);  // Split into command and arguments
        String commandName = commandParts[0].toLowerCase();
        String arguments = commandParts.length > 1 ? commandParts[1].trim() : "";

        switch (commandName) {
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand();
        case "mark":
            return new MarkCommand(arguments, true);
        case "unmark":
            return new MarkCommand(arguments, false);
        case "todo":
            return new AddCommand(arguments, TaskType.TODO);
        case "deadline":
            return new AddCommand(arguments, TaskType.DEADLINE);
        case "event":
            return new AddCommand(arguments, TaskType.EVENT);
        case "delete":
            return new DeleteCommand(arguments);
        case "find":  // Add handling for the find command
            if (arguments.isEmpty()) {
                throw new DukeException("Error: The find command requires a keyword.");
            }
            return new FindCommand(arguments);  // Create and return a FindCommand
        default:
            throw new DukeException("Error: Invalid command. Please enter a valid command.");
        }
    }
}
