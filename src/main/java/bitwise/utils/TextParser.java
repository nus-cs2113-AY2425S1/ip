package bitwise.utils;

import bitwise.constants.Constants;
import bitwise.constants.Icons;
import bitwise.tasks.Deadline;
import bitwise.tasks.Event;
import bitwise.tasks.Task;
import bitwise.tasks.Todo;

/**
 * The TextParser class is responsible for parsing input strings into
 * task objects. It provides methods to handle different task types
 * such as Todo, Event, and Deadline.
 */
public class TextParser {
    /**
     * Parses a string representation of a Todo task and creates a
     * Todo object.
     *
     * @param line the input string containing the Todo task information
     * @return a Todo object populated with the parsed information
     */
    public static Task handleTodo(String line) {
        boolean isCompleted = line.substring(Constants.PARSE_IS_COMPLETED_INDEX).startsWith(Icons.ICON_COMPLETED);
        String description = line.substring(Constants.PARSE_DESCRIPTION_START_INDEX);
        Todo newTask = new Todo(description);
        newTask.markCompletionStatus(isCompleted);
        return newTask;
    }

    /**
     * Parses a string representation of an Event task and creates an
     * Event object.
     *
     * @param line the input string containing the Event task information
     * @return an Event object populated with the parsed information
     */
    public static Task handleEvent(String line) {
        boolean isCompleted = line.substring(Constants.PARSE_IS_COMPLETED_INDEX).startsWith(Icons.ICON_COMPLETED);
        String description = line.substring(Constants.PARSE_DESCRIPTION_START_INDEX, line.indexOf("("));
        String from = line.substring(line.indexOf("from: ") + 6, line.indexOf(" to:"));
        String to = line.substring(line.indexOf("to: ") + 4, line.indexOf(")"));
        Event newTask = new Event(description, from, to);
        newTask.markCompletionStatus(isCompleted);
        return newTask;
    }

    /**
     * Parses a string representation of a Deadline task and creates
     * a Deadline object.
     *
     * @param line the input string containing the Deadline task information
     * @return a Deadline object populated with the parsed information
     */
    public static Task handleDeadline(String line) {
        boolean isCompleted = line.substring(Constants.PARSE_IS_COMPLETED_INDEX).startsWith(Icons.ICON_COMPLETED);
        String description = line.substring(Constants.PARSE_DESCRIPTION_START_INDEX, line.indexOf("("));
        String by = line.substring(line.indexOf("by: ") + 4, line.indexOf(")"));
        Deadline newTask = new Deadline(description, by);
        newTask.markCompletionStatus(isCompleted);
        return newTask;
    }
}
