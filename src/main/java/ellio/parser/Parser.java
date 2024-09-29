package ellio.parser;

import ellio.EllioExceptions;
import ellio.command.*;

import java.util.Scanner;

public class Parser {

    public static final String COMMAND_BYE = "bye";
    public static final String COMMAND_TODO = "todo";
    public static final String COMMAND_DEADLINE = "deadline";
    public static final String COMMAND_EVENT = "event";
    public static final String COMMAND_MARK = "mark";
    public static final String COMMAND_UNMARK = "unmark";
    public static final String COMMAND_LIST = "list";
    public static final String COMMAND_DELETE = "delete";
    public static final String COMMAND_FIND = "find";

    /**
     * Checks for the first word in the command, and checks against the command library
     * If an invalid command is found, an exception is thrown
     * @param inputCommand
     * @return Command Object for subsequent execution
     * @throws EllioExceptions.UnknownCommandException
     */
    public static Command parse(String inputCommand) throws EllioExceptions.UnknownCommandException {
        String[] inputs = inputCommand.split(" ",2);
        switch (inputs[0].toLowerCase()){
        case COMMAND_LIST:
            return new ListCommand(inputCommand);
        case COMMAND_BYE:
            return new ExitCommand(inputCommand);
        case COMMAND_FIND:
            return new FindCommand(inputCommand);
        case COMMAND_TODO:
            return new TodoCommand(inputCommand);
        case COMMAND_DEADLINE:
            return new DeadlineCommand(inputCommand);
        case COMMAND_EVENT:
            return new EventCommand(inputCommand);
        case COMMAND_UNMARK:
            return new UnmarkCommand(inputCommand);
        case COMMAND_DELETE:
            return new DeleteCommand(inputCommand);
        case COMMAND_MARK:
            return new MarkCommand(inputCommand);
        default:
            throw new EllioExceptions.UnknownCommandException();
        }
    }
}

