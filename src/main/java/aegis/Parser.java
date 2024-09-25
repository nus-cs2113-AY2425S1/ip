package aegis;

import aegis.command.*;
import aegis.task.Deadline;
import aegis.task.Event;
import aegis.task.Todo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * The Parser class is responsible for interpreting user commands and converting them into executable commands for the application.
 * It parses the input strings and translates them into specific command objects.
 */
public class Parser {

    /**
     * Parses the user input string and returns the corresponding Command object.
     *
     * @param input The user input string containing the command and its arguments.
     * @return The Command object corresponding to the user's input.
     * @throws AegisException If the input command is invalid or if there are errors in parsing the input.
     */
    public static Command parse(String input) throws AegisException {
        String[] parts = input.split(" ", 2);
        String commandWord = parts[0].toLowerCase();
        String argument = parts.length > 1 ? parts[1].trim() : "";

        switch (commandWord) {
        case "list":
            return new ListCommand();
        case "mark":
            return new MarkCommand(parseTaskIndex(argument), true);
        case "unmark":
            return new MarkCommand(parseTaskIndex(argument), false);
        case "delete":
            return new DeleteCommand(parseTaskIndex(argument));
        case "todo":
            return new AddCommand(parseTodoCommand(argument));
        case "deadline":
            return new AddCommand(parseDeadlineCommand(argument));
        case "event":
            return new AddCommand(parseEventCommand(argument));
        case "find":
            if (argument.isEmpty()) {
                throw new AegisException("The search keyword cannot be empty.");
            }
            return new FindCommand(argument);
        case "bye":
            return new ExitCommand();
        default:
            throw new AegisException("Your command has not been authorized");
        }
    }

    /**
     * Parses the task index from the given argument string.
     *
     * @param argument The argument string containing the task index.
     * @return The task index as an integer.
     * @throws AegisException If the task index is not a valid number or is out of range.
     */
    private static int parseTaskIndex(String argument) throws AegisException {
        try {
            int index = Integer.parseInt(argument) - 1;
            if (index < 0) {
                throw new AegisException("Invalid task number");
            }
            return index;
        } catch (NumberFormatException e) {
            throw new AegisException("Task index must be a valid number");
        }
    }

    /**
     * Parses a Todo task from the given argument string.
     *
     * @param argument The argument string containing the description of the todo task.
     * @return A new Todo object.
     * @throws AegisException If the description of the todo is empty.
     */
    private static Todo parseTodoCommand(String argument) throws AegisException {
        if (argument.isEmpty()) {
            throw new AegisException("The description of a todo cannot be empty");
        }
        return new Todo(argument);
    }

    /**
     * Parses a Deadline task from the given argument string.
     *
     * @param argument The argument string containing the description and due date of the deadline task.
     * @return A new Deadline object.
     * @throws AegisException If the syntax of the deadline command is incorrect or if required fields are missing.
     */
    private static Deadline parseDeadlineCommand(String argument) throws AegisException {
        String[] deadlineParts = argument.split(" /by ");
        if (deadlineParts.length < 2 || deadlineParts[0].trim().isEmpty() || deadlineParts[1].trim().isEmpty()) {
            throw new AegisException("Invalid syntax: you need to include [/by] parameter with description and time");
        }
        Object by = parseDateTimeOrString(deadlineParts[1].trim());
        return new Deadline(deadlineParts[0].trim(), by);
    }

    /**
     * Parses an Event task from the given argument string.
     *
     * @param argument The argument string containing the description, start time, and end time of the event task.
     * @return A new Event object.
     * @throws AegisException If the syntax of the event command is incorrect or if required fields are missing.
     */
    private static Event parseEventCommand(String argument) throws AegisException {
        String[] eventParts = argument.split(" /from | /to ");
        if (eventParts.length < 3 || eventParts[0].trim().isEmpty() || eventParts[1].trim().isEmpty() || eventParts[2].trim().isEmpty()) {
            throw new AegisException("Invalid syntax: you need to include [/from, /to] parameters with description, start, and end times");
        }
        Object from = parseDateTimeOrString(eventParts[1].trim());
        Object to = parseDateTimeOrString(eventParts[2].trim());
        return new Event(eventParts[0].trim(), from, to);
    }

    /**
     * Parses a date-time string into a LocalDateTime object if the format is valid; otherwise, returns the string as is.
     *
     * @param dateTimeStr The string representation of the date and time.
     * @return A LocalDateTime object if parsing is successful, otherwise the original string.
     */
    private static Object parseDateTimeOrString(String dateTimeStr) {
        try {
            return LocalDateTime.parse(dateTimeStr, DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
        } catch (DateTimeParseException e) {
            return dateTimeStr;
        }
    }

}
