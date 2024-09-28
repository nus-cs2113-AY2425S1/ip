package jeremy.util;

import jeremy.command.*;
import jeremy.exception.IllegalCommandException;

public class Parser {
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
