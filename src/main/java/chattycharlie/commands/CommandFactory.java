package chattycharlie.commands;

import chattycharlie.CharlieExceptions;

/**
 * Represents a factory for creating different types of commands for the ChattyCharlie system.
 * The <code>CommandFactory</code> class provides a method to create instances of commands base on
 * the given command type in the user input
 */
public class CommandFactory {

    /**
     * Creates a command instance based on the provided <code>CommandType</code> and user input line.
     *
     * @param commandType the type of command to create.
     * @param line the user input line associated with the command type.
     * @return the instance of the command to be executed.
     * @throws CharlieExceptions if the command type cannot be identified.
     */
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
        case PRINT:
            return new PrintCommand(line);
        case FIND:
            return new FindCommand(line);
        default:
            throw CharlieExceptions.cannotIdentifyCommandType(); //change this
        }
    }
}
