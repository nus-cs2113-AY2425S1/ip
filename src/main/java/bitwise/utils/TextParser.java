package bitwise.utils;

import bitwise.constants.Constants;
import bitwise.tasks.Deadline;
import bitwise.tasks.Event;
import bitwise.tasks.Task;
import bitwise.tasks.Todo;

public class TextParser {
    public static Task handleTodo(String line) {
        boolean isCompleted = line.substring(Constants.PARSE_IS_COMPLETED_INDEX).startsWith(Constants.ICON_COMPLETED);
        String description = line.substring(Constants.PARSE_DESCRIPTION_START_INDEX);
        Todo newTask = new Todo(description);
        newTask.markCompletionStatus(isCompleted);
        return newTask;
    }

    public static Task handleEvent(String line) {
        boolean isCompleted = line.substring(Constants.PARSE_IS_COMPLETED_INDEX).startsWith(Constants.ICON_COMPLETED);
        String description = line.substring(Constants.PARSE_DESCRIPTION_START_INDEX, line.indexOf("("));
        String from = line.substring(line.indexOf("from: ") + 6, line.indexOf(" to:"));
        String to = line.substring(line.indexOf("to: ") + 4, line.indexOf(")"));
        Event newTask = new Event(description, from, to);
        newTask.markCompletionStatus(isCompleted);
        return newTask;
    }

    public static Task handleDeadline(String line) {
        boolean isCompleted = line.substring(Constants.PARSE_IS_COMPLETED_INDEX).startsWith(Constants.ICON_COMPLETED);
        String description = line.substring(Constants.PARSE_DESCRIPTION_START_INDEX, line.indexOf("("));
        String by = line.substring(line.indexOf("by: ") + 4, line.indexOf(")"));
        Deadline newTask = new Deadline(description, by);
        newTask.markCompletionStatus(isCompleted);
        return newTask;
    }
}
