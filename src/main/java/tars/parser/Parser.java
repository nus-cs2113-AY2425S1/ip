package tars.parser;

import tars.command.*;
import tars.tarsexception.tarsException;

/**
 * Represents the parser that interprets user input and returns the corresponding command.
 */
public class Parser {

    /**
     * Parses the user input and returns the corresponding command.
     *
     * @param userInput The full input string provided by the user.
     * @return The appropriate Command object based on the user's input.
     * @throws tarsException If the input is not recognized as a valid command.
     */
    public Command parse(String userInput) throws tarsException {
        String[] splitInput = userInput.split(" ", 2);
        String command = splitInput[0];
        String arguments = splitInput.length > 1 ? splitInput[1] : "";

        switch (command) {
            case "bye":
                return new ExitCommand();
            case "list":
                return new ListCommand();
            case "mark":
                return parseMarkCommand(arguments);
            case "unmark":
                return parseUnmarkCommand(arguments);
            case "delete":
                return parseDeleteCommand(arguments);
            case "todo":
                return new AddTodoCommand(arguments);
            case "deadline":
                return parseDeadlineCommand(arguments);
            case "event":
                return parseEventCommand(arguments);
            default:
                throw new tarsException("Unknown command: " + command);
        }
    }

    /**
     * Parses the command for marking a task as completed.
     *
     * @param arguments The task number provided by the user.
     * @return A MarkCommand object to mark the task as done.
     * @throws tarsException If the task number is missing or invalid.
     */
    private Command parseMarkCommand(String arguments) throws tarsException {
        if (arguments.isEmpty()) {
            throw new tarsException("The mark command requires a task number.");
        }
        int taskIndex = Integer.parseInt(arguments);
        return new MarkCommand(taskIndex);
    }

    /**
     * Parses the command for unmarking a task as not completed.
     *
     * @param arguments The task number provided by the user.
     * @return An UnmarkCommand object to unmark the task as not done.
     * @throws tarsException If the task number is missing or invalid.
     */
    private Command parseUnmarkCommand(String arguments) throws tarsException {
        if (arguments.isEmpty()) {
            throw new tarsException("The unmark command requires a task number.");
        }
        int taskIndex = Integer.parseInt(arguments);
        return new UnmarkCommand(taskIndex);
    }

    /**
     * Parses the command for deleting a task.
     *
     * @param arguments The task number provided by the user.
     * @return A DeleteCommand object to delete the task.
     * @throws tarsException If the task number is missing or invalid.
     */
    private Command parseDeleteCommand(String arguments) throws tarsException {
        if (arguments.isEmpty()) {
            throw new tarsException("The delete command requires a task number.");
        }
        int taskIndex = Integer.parseInt(arguments);
        return new DeleteCommand(taskIndex);
    }

    /**
     * Parses the command for adding a deadline task.
     *
     * @param arguments The task description and deadline date provided by the user.
     * @return An AddDeadlineCommand object to add the deadline task.
     * @throws tarsException If the deadline format is incorrect or the date is missing.
     */
    private Command parseDeadlineCommand(String arguments) throws tarsException {
        if (!arguments.contains("/by")) {
            throw new tarsException("The deadline command requires a /by date.");
        }
        String[] taskParts = arguments.split("/by");
        String description = taskParts[0].trim();
        String by = taskParts[1].trim();
        return new AddDeadlineCommand(description, by);
    }

    /**
     * Parses the command for adding an event task.
     *
     * @param arguments The task description, start time, and end time provided by the user.
     * @return An AddEventCommand object to add the event task.
     * @throws tarsException If the event format is incorrect or the times are missing.
     */
    private Command parseEventCommand(String arguments) throws tarsException {
        if (!arguments.contains("/from") || !arguments.contains("/to")) {
            throw new tarsException("The event command requires both /from and /to times.");
        }
        String[] taskParts = arguments.split("/from|/to");
        String description = taskParts[0].trim();
        String from = taskParts[1].trim();
        String to = taskParts[2].trim();
        return new AddEventCommand(description, from, to);
    }
}
