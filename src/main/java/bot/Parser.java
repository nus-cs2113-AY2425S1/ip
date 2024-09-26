package bot;

import bot.command.*;
import bot.TobyBotException;

/**
 * Parses user input and returns the appropriate command.
 */
public class Parser {

    /**
     * Parses the user's input command and creates a corresponding Command object.
     *
     * @param fullCommand The full command input by the user.
     * @return A Command object that represents the user's command.
     * @throws TobyBotException If the command is invalid or unrecognized.
     */
    public static Command parse(String fullCommand) throws TobyBotException {
        String[] parts = fullCommand.split(" ", 2);
        String command = parts[0];

        switch (command) {
            case "bye":
                return new ExitCommand();
            case "list":
                return new ListCommand();
            case "mark":
                // Check if the user has provided a task number
                if (parts.length < 2) {
                    throw new TobyBotException("Please provide a task number to mark.");
                }
                return new MarkCommand(Integer.parseInt(parts[1]));
            case "unmark":
                // Check if the user has provided a task number
                if (parts.length < 2) {
                    throw new TobyBotException("Please provide a task number to unmark.");
                }
                return new UnmarkCommand(Integer.parseInt(parts[1]));
            case "todo":
                // Check if the user has provided a description for the todo
                if (parts.length < 2 || parts[1].trim().isEmpty()) {
                    throw new TobyBotException("The description of a todo cannot be empty.");
                }
                return new AddCommand("todo", parts[1]);
            case "deadline":
                // Check if the user has provided details for the deadline
                if (parts.length < 2 || parts[1].trim().isEmpty()) {
                    throw new TobyBotException("The description of a deadline cannot be empty.");
                }
                return new AddCommand("deadline", parts[1]);
            case "event":
                // Check if the user has provided details for the event
                if (parts.length < 2 || parts[1].trim().isEmpty()) {
                    throw new TobyBotException("The description of an event cannot be empty.");
                }
                return new AddCommand("event", parts[1]);
            case "delete":
                // Check if the user has provided a task number to delete
                if (parts.length < 2) {
                    throw new TobyBotException("Please provide a task number to delete.");
                }
                return new DeleteCommand(Integer.parseInt(parts[1]));
            case "find":
                // Check if the user has provided a keyword to search
                if (parts.length < 2 || parts[1].trim().isEmpty()) {
                    throw new TobyBotException("Please provide a keyword to search for.");
                }
                return new FindCommand(parts[1]);
            default:
                throw new TobyBotException("Unknown command: " + command);
        }
    }
}