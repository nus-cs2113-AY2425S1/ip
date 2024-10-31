package luke;

import luke.commands.*;
import luke.exceptions.InvalidCommandException;

/**
 * Parses user input.
 */
public class Parser {

    /**
     * Parses user input into command for execution.
     *
     * @param inputs String array of user input, split with " " as delimiter
     * @return Command object of command to execute
     */
    public static Command parseCommand(String[] inputs) {

        String command = inputs[0].toLowerCase();

        switch (command) {
        case ByeCommand.COMMAND_WORD:
            return new ByeCommand();
        case ListCommand.COMMAND_WORD:
            return new ListCommand();
        case MarkCommand.COMMAND_WORD:
            return new MarkCommand();
        case UnmarkCommand.COMMAND_WORD:
            return new UnmarkCommand();
        case AddTodoCommand.COMMAND_WORD:
            return new AddTodoCommand();
        case AddDeadlineCommand.COMMAND_WORD:
            return new AddDeadlineCommand();
        case AddEventCommand.COMMAND_WORD:
            return new AddEventCommand();
        case DeleteCommand.COMMAND_WORD:
            return new DeleteCommand();
        case FindCommand.COMMAND_WORD:
            return new FindCommand();
        default:
            throw new InvalidCommandException("Invalid command");
        }

    }
}
