package jeremy.util;

import jeremy.command.*;
import jeremy.exception.IllegalCommandException;

/**
 * The {@code Parser} class is responsible for interpreting user input and
 * converting it into specific {@link Command} objects. It also helps
 * in identifying command types based on icons or user inputs.
 */
public class Parser {
    /**
     * Parses the given user input and returns the corresponding {@link Command}.
     * The user input is split into a command type and an optional argument.
     *
     * @param userInput The input string entered by the user.
     * @return A {@link Command} object corresponding to the user input.
     * @throws IllegalCommandException If the input does not match a valid command type.
     */
    public static Command parse(String userInput) throws IllegalCommandException {
        String[] parts = userInput.split(" ", 2);
        try {
            CommandType commandType = CommandType.valueOf(parts[0].toUpperCase());
            String argument = parts.length > 1 ? parts[1] : "";

            switch (commandType) {
            case LIST:
                return new ListCommand();
            case MARK:
                return new MarkCommand(argument);
            case UNMARK:
                return new UnmarkCommand(argument);
            case DELETE:
                return new DeleteCommand(argument);
            case FIND:
                return new FindCommand(argument);
            case TODO:
                return new TodoCommand(argument);
            case DEADLINE:
                return new DeadlineCommand(argument);
            case EVENT:
                return new EventCommand(argument);
            case BYE:
                return new ByeCommand();
            default:
                // Shouldn't be reachable, below catch should handle all errors
                throw new IllegalCommandException("How did you get here?");
            }
        } catch (IllegalArgumentException e) {
            throw new IllegalCommandException(parts[0]);
        }
    }

    /**
     * Converts an icon from storage into the corresponding {@link CommandType}.
     *
     * @param icon The icon representing the command type (e.g., "T" for Todo).
     * @return The {@link CommandType} corresponding to the icon.
     * @throws IllegalCommandException If storage is not in the correct format.
     */
    public static CommandType fromIcon(String icon) throws IllegalCommandException {
        switch (icon) {
        case "T":
            return CommandType.TODO;
        case "D":
            return CommandType.DEADLINE;
        case "E":
            return CommandType.EVENT;
        default:
            throw new IllegalCommandException("Corrupted storage :(");
        }
    }
}
