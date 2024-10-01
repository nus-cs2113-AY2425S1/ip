package aether.parser;


import aether.DukeException;
import aether.command.*;

/**
 * Parses user commands and returns the appropriate {@code Command} object.
 * <p>
 * This class interprets the user's input string, identifies the command type,
 * and constructs the corresponding {@code Command} object to execute the desired action.
 * It handles various commands such as adding, deleting, marking tasks, and more.
 * </p>
 */
public class Parser {

    /**
     * Parses the user's input command and returns the corresponding {@code Command} object.
     *
     * @param command The input string entered by the user.
     * @return The {@code Command} object corresponding to the parsed command.
     * @throws DukeException If the command is invalid or required arguments are missing.
     */
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
