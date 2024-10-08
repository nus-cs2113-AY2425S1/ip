package nus.edu.rizzler.manager;

import nus.edu.rizzler.command.*;
import nus.edu.rizzler.exception.RizzlerException;

public class Parser {
    public Command parse(String fullCommand) throws RizzlerException {
        if (fullCommand == null || fullCommand.trim().isEmpty()) {
            throw new RizzlerException("Command cannot be empty. Please enter a valid command.");
        }

        String[] inputArguments = fullCommand.split(" ", 2);
        String commandString = inputArguments[0];
        String argumentString = inputArguments.length > 1 ? inputArguments[1] : "";

        switch (commandString) {
        case TodoCommand.COMMAND_WORD: return createTodoCommand(argumentString);
        case DeadlineCommand.COMMAND_WORD: return createDeadlineCommand(argumentString);
        case EventCommand.COMMAND_WORD: return createEventCommand(argumentString);
        case MarkCommand.COMMAND_WORD: return createMarkCommand(argumentString);
        case UnmarkCommand.COMMAND_WORD: return createUnmarkCommand(argumentString);
        case ListCommand.COMMAND_WORD: return createListCommand();
        case DeleteCommand.COMMAND_WORD: return createDeleteCommand(argumentString);
        case ExitCommand.COMMAND_WORD: return createExitCommand();
        default: return createInvalidCommand();
        }
    }

    private Command createTodoCommand(String argumentString) throws RizzlerException {
        String taskName = argumentString.trim();
        if (taskName.isEmpty()) {
            throw new RizzlerException("Todo task name cannot be empty. Please provide a valid task name.");
        }
        return new TodoCommand(taskName);
    }

    private Command createDeadlineCommand(String argumentString) throws RizzlerException {
        String[] arguments = parseArguments(argumentString, " /by ");
        if (arguments.length != 2) {
            throw new RizzlerException("Please provide valid task name and due date using the " +
                    "following format - deadline [task name] /by [due date].");
        }

        String taskName = arguments[0].trim();
        String by = arguments[1].trim();

        if (taskName.isEmpty()) {
            throw new RizzlerException("Task name cannot be empty. Please provide valid task name.");
        }
        if (by.isEmpty()) {
            throw new RizzlerException("Due date cannot be empty. Please provide a valid due date.");
        }
        return new DeadlineCommand(taskName, by);
    }

    private Command createEventCommand(String argumentString) throws RizzlerException {
        String[] arguments = parseArguments(argumentString, " /from ", " /to ");
        if (arguments.length != 3) {
            throw new RizzlerException("Please provide valid task name, start time, and end time using the" +
                    "following format - Event [task name] /from [start time] /to [end time].");
        }

        String taskName = arguments[0].trim();
        String from = arguments[1].trim();
        String to = arguments[2].trim();

        if (taskName.isEmpty()) {
            throw new RizzlerException("Task name cannot be empty. Please provide valid task name.");
        }
        if (from.isEmpty()) {
            throw new RizzlerException("Start time cannot be empty. Please provide valid start time.");
        }
        if (to.isEmpty()) {
            throw new RizzlerException("End time cannot be empty. Please provide valid end time.");
        }
        return new EventCommand(taskName, from, to);
    }

    private Command createMarkCommand(String argumentString) throws RizzlerException {
        int taskIndex = parseTaskIndex(argumentString);
        return new MarkCommand(taskIndex);
    }

    private Command createUnmarkCommand(String argumentString) throws RizzlerException {
        int taskIndex = parseTaskIndex(argumentString);
        return new UnmarkCommand(taskIndex);
    }

    private Command createListCommand() {
        return new ListCommand();
    }

    private Command createDeleteCommand(String argumentString) throws RizzlerException {
        int taskIndex = parseTaskIndex(argumentString);
        return new DeleteCommand(taskIndex);
    }

    private Command createExitCommand() {
        return new ExitCommand();
    }

    private Command createInvalidCommand() {
        return new InvalidCommand();
    }

    private int parseTaskIndex(String index) throws RizzlerException {
        if (index.trim().isEmpty()) {
            throw new RizzlerException("Task index cannot be empty. Please provide valid task index.");
        }

        try {
            int taskIndex = Integer.parseInt(index.trim()) - 1;
            if (taskIndex < 0) {
                throw new RizzlerException("Task index must be positive.");
            }
            return taskIndex;
        } catch (NumberFormatException e) {
            throw new RizzlerException("Task index is invalid.");
        }
    }

    private String[] parseArguments(String argumentString, String... delimiters) {
        String delimiterPattern = String.join("|", delimiters);
        return argumentString.split(delimiterPattern);
    }
}

