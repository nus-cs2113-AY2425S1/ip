package luke;

import luke.commands.*;
import luke.exceptions.InvalidCommand;

public class Parser {

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
        default:
            throw new InvalidCommand("Invalid command");
        }

    }
}
