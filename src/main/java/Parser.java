package main.java;

import main.java.Ui;
import main.java.TaskList;
import main.java.Storage;

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

import ran.exception.MissingArgumentException;
import ran.exception.MissingCommandException;
import ran.exception.MissingDescriptionException;
import ran.exception.EmptyListException;
import ran.exception.OutOfListBoundsException;
import ran.exception.RanException;

public class Parser {

    public static String getCommandArg(String input) {
        String[] splitInput = input.split("\\s+", 2);
        if (splitInput.length == 2) {
            return splitInput[1];
        } else {
            return "";
        }
    }

    public static CommandType getCommandType(String input) {
        if (input.equals("bye")) {
            return CommandType.TERMINATE;
        } else if (input.equals("list")) {
            return CommandType.LIST;
        }
        String[] splitInput = input.split("\\s+", 2);
        String command = splitInput[0];
        switch (command) {
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
        default:
            return CommandType.UNDEFINED;
        }
    }

    public static Command parse(String input) throws MissingCommandException {
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
        case UNDEFINED:
            // Fall-through
        default:
            throw new MissingCommandException();
        }
    }
}
