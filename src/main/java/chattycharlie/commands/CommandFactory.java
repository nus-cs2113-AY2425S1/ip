package chattycharlie.commands;

import chattycharlie.CharlieExceptions;

public class CommandFactory {
    public static Command createCommand(CommandType commandType, String line) throws CharlieExceptions {
        switch (commandType) {
        case TODO:
            return new TodoCommand(line);
        case DEADLINE:
            return new DeadlineCommand(line);
        case EVENT:
            return new EventCommand(line);
        case MARK:
            return new MarkCommand(line);
        case UNMARK:
            return new UnmarkCommand(line);
        case BYE:
            return new ByeCommand();
        case LIST:
            return new ListCommand();
        case DELETE:
            return new DeleteCommand(line);
        default:
            throw CharlieExceptions.cannotIdentifyCommandType(); //change this
        }
    }
}
