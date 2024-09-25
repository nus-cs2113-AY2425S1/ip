package aegis;

import aegis.command.*;
import aegis.task.Deadline;
import aegis.task.Event;
import aegis.task.Todo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {
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
        case "bye":
            return new ExitCommand();
        default:
            throw new AegisException("Your command has not been authorized");
        }
    }

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

    private static Todo parseTodoCommand(String argument) throws AegisException {
        if (argument.isEmpty()) {
            throw new AegisException("The description of a todo cannot be empty");
        }
        return new Todo(argument);
    }

    private static Deadline parseDeadlineCommand(String argument) throws AegisException {
        String[] deadlineParts = argument.split(" /by ");
        if (deadlineParts.length < 2 || deadlineParts[0].trim().isEmpty() || deadlineParts[1].trim().isEmpty()) {
            throw new AegisException("Invalid syntax: you need to include [/by] parameter with description and time");
        }
        Object by = parseDateTimeOrString(deadlineParts[1].trim());
        return new Deadline(deadlineParts[0].trim(), by);
    }

    private static Event parseEventCommand(String argument) throws AegisException {
        String[] eventParts = argument.split(" /from | /to ");
        if (eventParts.length < 3 || eventParts[0].trim().isEmpty() || eventParts[1].trim().isEmpty() || eventParts[2].trim().isEmpty()) {
            throw new AegisException("Invalid syntax: you need to include [/from, /to] parameters with description, start, and end times");
        }
        Object from = parseDateTimeOrString(eventParts[1].trim());
        Object to = parseDateTimeOrString(eventParts[2].trim());
        return new Event(eventParts[0].trim(), from, to);
    }

    private static Object parseDateTimeOrString(String dateTimeStr) {
        try {
            return LocalDateTime.parse(dateTimeStr, DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
        } catch (DateTimeParseException e) {
            return dateTimeStr;
        }
    }

}
