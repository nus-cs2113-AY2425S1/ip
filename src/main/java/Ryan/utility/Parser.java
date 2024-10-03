package Ryan.utility;

import Ryan.exceptions.RyanException;
import Ryan.commands.*;

public class Parser {

    public Command parse(String fullCommand) throws RyanException {
        String[] parts = fullCommand.trim().split(" ", 2);
        String commandWord = parts[0].toLowerCase();
        String arguments = parts.length > 1 ? parts[1] : "";

        try {
            switch (commandWord) {
                case "todo":
                    return new TodoCommand(arguments);
                case "deadline":
                    return new DeadlineCommand(arguments);
                case "event":
                    return new EventCommand(arguments);
                case "delete":
                    return new DeleteCommand(Integer.parseInt(arguments));
                case "mark":
                    return new MarkCommand(Integer.parseInt(arguments));
                case "unmark":
                    return new UnmarkCommand(Integer.parseInt(arguments));
                case "bye":
                    return new ExitCommand();
                case "list":
                    return new ListCommand();
                default:
                    throw new RyanException("Unknown command: " + commandWord);
            }
        } catch (NumberFormatException e) {
            throw new RyanException("Please provide a valid task number.");
        }
    }

}
