package nus.edu.rizzler.manager;

import nus.edu.rizzler.command.*;
import nus.edu.rizzler.exception.RizzlerException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Parses and executes user input commands within the application.
 */
public class Parser {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    /**
     * Parses the command string and returns the corresponding {@code Command}.
     *
     * @param fullCommand The command string entered by the user.
     * @return The parsed {@code Command} object.
     * @throws RizzlerException If the command format is invalid.
     */
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
        case FindCommand.COMMAND_WORD: return createFindCommand(argumentString);
        case ExitCommand.COMMAND_WORD: return createExitCommand();
        default: return createInvalidCommand();
        }
    }

    /**
     * Creates a {@code TodoCommand} with the given task name.
     *
     * @param argumentString Task name for the todo item.
     * @return The {@code TodoCommand} object.
     * @throws RizzlerException If task name is empty.
     */
    private Command createTodoCommand(String argumentString) throws RizzlerException {
        String taskName = argumentString.trim();
        if (taskName.isEmpty()) {
            throw new RizzlerException("Todo task name cannot be empty. Please provide a valid task name.");
        }
        return new TodoCommand(taskName);
    }

    /**
     * Creates a {@code DeadlineCommand} with the given task name and due date.
     *
     * @param argumentString Task name and due date in the format "[task name] /by [due date]".
     * @return The {@code DeadlineCommand} object.
     * @throws RizzlerException If task name or due date is empty or format is invalid.
     */
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
        validateDateTimeFormat(by);

        return new DeadlineCommand(taskName, by);
    }

    /**
     * Creates an {@code EventCommand} with the specified task name, start, and end times.
     *
     * @param argumentString Task name, start, and end times in the format "[task name] /from [start time] /to [end time]".
     * @return The {@code EventCommand} object.
     * @throws RizzlerException If task name, start, or end time is empty, or format is invalid.
     */
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
        validateDateTimeFormat(from);
        validateDateTimeFormat(to);

        return new EventCommand(taskName, from, to);
    }

    /**
     * Validates the input date string using the expected date and time format.
     *
     * @param dateTimeString The date string to validate.
     * @throws RizzlerException If the date string is not in the correct format.
     */
    private void validateDateTimeFormat(String dateTimeString) throws RizzlerException { // NEW METHOD: Validates date and time format
        try {
            LocalDateTime.parse(dateTimeString, DATE_TIME_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new RizzlerException("Invalid date/time format. Please use 'yyyy-MM-dd HH:mm'.");
        }
    }

    /**
     * Creates a {@code MarkCommand} for marking a task as done.
     *
     * @param argumentString The task index.
     * @return The {@code MarkCommand} object.
     * @throws RizzlerException If task index is invalid.
     */
    private Command createMarkCommand(String argumentString) throws RizzlerException {
        int taskIndex = parseTaskIndex(argumentString);
        return new MarkCommand(taskIndex);
    }

    /**
     * Creates an {@code UnmarkCommand} for marking a task as not done.
     *
     * @param argumentString The task index.
     * @return The {@code UnmarkCommand} object.
     * @throws RizzlerException If task index is invalid.
     */
    private Command createUnmarkCommand(String argumentString) throws RizzlerException {
        int taskIndex = parseTaskIndex(argumentString);
        return new UnmarkCommand(taskIndex);
    }

    /**
     * Creates a {@code ListCommand} to list all tasks.
     *
     * @return The {@code ListCommand} object.
     */
    private Command createListCommand() {
        return new ListCommand();
    }

    /**
     * Creates a {@code DeleteCommand} for deleting a task by index.
     *
     * @param argumentString The task index.
     * @return The {@code DeleteCommand} object.
     * @throws RizzlerException If task index is invalid.
     */
    private Command createDeleteCommand(String argumentString) throws RizzlerException {
        int taskIndex = parseTaskIndex(argumentString);
        return new DeleteCommand(taskIndex);
    }

    /**
     * Creates a {@code FindCommand} to search tasks by keyword.
     *
     * @param keyword The keyword for filtering tasks.
     * @return The {@code FindCommand} object.
     * @throws RizzlerException If the keyword is empty.
     */
    private Command createFindCommand(String keyword) throws RizzlerException {
        if (keyword.trim().isEmpty()) {
            throw new RizzlerException("Keyword cannot be empty. Please provide valid keyword for find.");
        }
        return new FindCommand(keyword);
    }

    /**
     * Creates an {@code ExitCommand} to exit the application.
     *
     * @return The {@code ExitCommand} object.
     */
    private Command createExitCommand() {
        return new ExitCommand();
    }

    /**
     * Creates an {@code InvalidCommand} to handle unrecognized commands.
     *
     * @return The {@code InvalidCommand} object.
     */
    private Command createInvalidCommand() {
        return new InvalidCommand();
    }

    /**
     * Parses the task index from a string.
     *
     * @param index The task index as a string.
     * @return The parsed task index.
     * @throws RizzlerException If the task index is invalid.
     */
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

    /**
     * Parses the argument string using given delimiters.
     *
     * @param argumentString The raw argument string.
     * @param delimiters     Delimiters for parsing.
     * @return Parsed arguments as an array.
     */
    private String[] parseArguments(String argumentString, String... delimiters) {
        String delimiterPattern = String.join("|", delimiters);
        return argumentString.split(delimiterPattern);
    }
}

