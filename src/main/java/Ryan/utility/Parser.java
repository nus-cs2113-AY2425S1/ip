package Ryan.utility;

import Ryan.commands.Command;
import Ryan.commands.TodoCommand;
import Ryan.commands.DeadlineCommand;
import Ryan.commands.EventCommand;
import Ryan.commands.DeleteCommand;
import Ryan.commands.MarkCommand;
import Ryan.commands.UnmarkCommand;
import Ryan.commands.ListCommand;
import Ryan.commands.FindCommand;
import Ryan.commands.ExitCommand;

import Ryan.exceptions.InvalidIndexException;
import Ryan.exceptions.RyanException;
import Ryan.exceptions.UnknownCommandException;

/**
 * Represents a parser that processes user input and returns the appropriate command.
 */
public class Parser {

    protected static final String TODO_COMMAND = "todo";
    protected static final String DEADLINE_COMMAND = "deadline";
    protected static final String EVENT_COMMAND = "event";
    protected static final String DELETE_COMMAND = "delete";
    protected static final String MARK_COMMAND = "mark";
    protected static final String UNMARK_COMMAND = "unmark";
    protected static final String LIST_COMMAND = "list";
    protected static final String FIND_COMMAND = "find";
    protected static final String EXIT_COMMAND = "bye";

    /**
     * Parses the user input and returns the corresponding command.
     *
     * @param fullCommand The full command entered by the user.
     * @return The command corresponding to the input.
     * @throws RyanException If the command is invalid or cannot be parsed.
     */
    public Command parse(String fullCommand) throws RyanException {
        String[] parts = fullCommand.trim().split(" ", 2);
        String commandWord = parts[0].toLowerCase();
        String arguments = parts.length > 1 ? parts[1] : "";

        try {
            switch (commandWord) {
                case TODO_COMMAND:
                    return new TodoCommand(arguments);
                case DEADLINE_COMMAND:
                    return new DeadlineCommand(arguments);
                case EVENT_COMMAND:
                    return new EventCommand(arguments);
                case DELETE_COMMAND:
                    return new DeleteCommand(Integer.parseInt(arguments));
                case MARK_COMMAND:
                    return new MarkCommand(Integer.parseInt(arguments));
                case UNMARK_COMMAND:
                    return new UnmarkCommand(Integer.parseInt(arguments));
                case LIST_COMMAND:
                    return new ListCommand();
                case FIND_COMMAND:
                    return new FindCommand(arguments);
                case EXIT_COMMAND:
                    return new ExitCommand();
                default:
                    throw new UnknownCommandException(commandWord);
            }
        } catch (NumberFormatException e) {
            throw new InvalidIndexException();
        }
    }
}
