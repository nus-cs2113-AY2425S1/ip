package tars.parser;

import tars.command.*;
import tars.tarsexception.TarsException;

/**
 * Represents the parser that interprets user input and returns the corresponding command.
 */
public class Parser {

    /**
     * Parses the user input and returns the corresponding command.
     *
     * @param userInput The full input string provided by the user.
     * @return The appropriate Command object based on the user's input.
     * @throws TarsException If the input is not recognized as a valid command.
     */
    public Command parse(String userInput) throws TarsException {
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
            case "find":
                return parseFindCommand(arguments);
            default:
                throw new TarsException("Unknown command: " + command);
        }
    }

    /**
     * Parses the command for marking a task as completed.
     *
     * @param arguments The task number provided by the user.
     * @return A MarkCommand object to mark the task as done.
     * @throws TarsException If the task number is missing or invalid.
     */
    private Command parseMarkCommand(String arguments) throws TarsException {
        if (arguments.isEmpty()) {
            throw new TarsException("The mark command requires a task number.");
        }
        int taskIndex = Integer.parseInt(arguments);
        return new MarkCommand(taskIndex);
    }

    /**
     * Parses the command for unmarking a task as not completed.
     *
     * @param arguments The task number provided by the user.
     * @return An UnmarkCommand object to unmark the task as not done.
     * @throws TarsException If the task number is missing or invalid.
     */
    private Command parseUnmarkCommand(String arguments) throws TarsException {
        if (arguments.isEmpty()) {
            throw new TarsException("The unmark command requires a task number.");
        }
        int taskIndex = Integer.parseInt(arguments);
        return new UnmarkCommand(taskIndex);
    }

    /**
     * Parses the command for deleting a task.
     *
     * @param arguments The task number provided by the user.
     * @return A DeleteCommand object to delete the task.
     * @throws TarsException If the task number is missing or invalid.
     */
    private Command parseDeleteCommand(String arguments) throws TarsException {
        if (arguments.isEmpty()) {
            throw new TarsException("The delete command requires a task number.");
        }
        int taskIndex = Integer.parseInt(arguments);
        return new DeleteCommand(taskIndex);
    }

    /**
     * Parses the command for adding a deadline task.
     *
     * @param arguments The task description and deadline date provided by the user.
     * @return An AddDeadlineCommand object to add the deadline task.
     * @throws TarsException If the deadline format is incorrect or the date is missing.
     */
    private Command parseDeadlineCommand(String arguments) throws TarsException {
        if (!arguments.contains("/by")) {
            throw new TarsException("The deadline command requires a /by date.");
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
     * @throws TarsException If the event format is incorrect or the times are missing.
     */
    private Command parseEventCommand(String arguments) throws TarsException {
        if (!arguments.contains("/from") || !arguments.contains("/to")) {
            throw new TarsException("The event command requires both /from and /to times.");
        }
        String[] taskParts = arguments.split("/from|/to");
        String description = taskParts[0].trim();
        String from = taskParts[1].trim();
        String to = taskParts[2].trim();
        return new AddEventCommand(description, from, to);
    }

    /**
     * Parses the command for finding tasks with a keyword.
     *
     * @param keyword The keyword to search for.
     * @return A FindCommand object to find tasks containing the keyword.
     * @throws TarsException If the keyword is missing.
     */
    private Command parseFindCommand(String keyword) throws TarsException {
        if (keyword.isEmpty()) {
            throw new TarsException("The find command requires a keyword.");
        }
        return new FindCommand(keyword);
    }

}
