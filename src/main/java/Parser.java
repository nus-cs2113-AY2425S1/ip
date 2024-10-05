package main.java;

import ran.command.Command;
import ran.command.CommandType;
import ran.command.AddDeadlineCommand;
import ran.command.AddEventCommand;
import ran.command.AddTodoCommand;
import ran.command.DeleteTaskCommand;
import ran.command.MarkTaskCommand;
import ran.command.UnmarkTaskCommand;
import ran.command.ShowListCommand;
import ran.command.TerminateCommand;
import ran.command.FindCommand;
import ran.command.HelpCommand;

import ran.exception.MissingArgumentException;
import ran.exception.InvalidCommandException;
import ran.exception.EmptyListException;
import ran.exception.OutOfListBoundsException;

/**
 * Parser class to interpret user input and pass a command back to Ran chatbot to execute.
 */
public class Parser {
    private static String getCommandArg(String input) {
        String[] splitInput = input.split("\\s+", 2);
        if (splitInput.length == 2) {
            return splitInput[1].trim();
        } else {
            return "";
        }
    }

    private static CommandType getCommandType(String input) {
        String[] splitInput = input.split("\\s+", 2);
        String command = splitInput[0];
        switch (command.toLowerCase()) {
        case ("bye"):
            return CommandType.TERMINATE;
        case ("list"):
            return CommandType.LIST;
        case ("todo"):
            return CommandType.TODO;
        case ("event"):
            return CommandType.EVENT;
        case ("deadline"):
            return CommandType.DEADLINE;
        case ("mark"):
            return CommandType.MARK;
        case ("unmark"):
            return CommandType.UNMARK;
        case ("delete"):
            return CommandType.DELETE;
        case ("find"):
            return CommandType.FIND;
        case ("help"):
            return CommandType.HELP;
        default:
            return CommandType.UNDEFINED;
        }
    }

    /**
     * Parse function that interprets user input into a command.
     *
     * @param input String that user inputted
     * @return Command corresponding what user inputted
     * @throws InvalidCommandException If user inputted unrecognisable command
     */
    public static Command parse(String input) throws InvalidCommandException {
        String commandArg = getCommandArg(input);
        CommandType commandType = getCommandType(input);
        switch (commandType) {
        case TERMINATE:
            return new TerminateCommand(commandArg);
        case LIST:
            return new ShowListCommand(commandArg);
        case TODO:
            return new AddTodoCommand(commandArg);
        case DEADLINE:
            return new AddDeadlineCommand(commandArg);
        case EVENT:
            return new AddEventCommand(commandArg);
        case MARK:
            return new MarkTaskCommand(commandArg);
        case UNMARK:
            return new UnmarkTaskCommand(commandArg);
        case DELETE:
            return new DeleteTaskCommand(commandArg);
        case FIND:
            return new FindCommand(commandArg);
        case HELP:
            return new HelpCommand(commandArg);
        case UNDEFINED:
            // Fall-through
        default:
            throw new InvalidCommandException();
        }
    }
}
