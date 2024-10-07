package pythia.utility;

import pythia.task.Deadline;
import pythia.task.Event;
import pythia.task.Task;
import pythia.task.ToDo;

/**
 * A factory class used for creating {@link Task} objects and its
 * derived classes ({@link ToDo}, {@link Event}, {@link Deadline})
 * from a string representation. The factory parses the string and
 * creates the appropriate type of task based on the information.
 */
public class TaskFromStringFactory {
    /**
     * Creates a new {@link Task} object or one of its derived classes
     * from the given string representation. The string must follow
     * a specific format with fields separated by " | ". The first field
     * determines the type of task:
     * <ul>
     *     <li>{@code "T"} - {@link ToDo}</li>
     *     <li>{@code "E"} - {@link Event}</li>
     *     <li>{@code "D"} - {@link Deadline}</li>
     *     <li>Any other identifier creates a generic {@link Task}</li>
     * </ul>
     *
     * @param taskAsString a string representation of the task,
     *                     with fields separated by " | "
     * @return an object of the appropriate task type ({@link ToDo},
     * {@link Event}, {@link Deadline}, or {@link Task})
     * @throws IllegalArgumentException if the string doesn't have at
     * least two fields or the type identifier is missing
     */
    public Task create(String taskAsString) {
        String[] fields = taskAsString.split(" \\| ");

        if (fields.length < 2) {
            throw new IllegalArgumentException("Task should have at least 2 fields");
        }

        String typeIdentifier = fields[0];
        return switch (typeIdentifier) {
            case "T" -> createToDo(fields);
            case "E" -> createEvent(fields);
            case "D" -> createDeadline(fields);
            default -> createTask(fields);
        };
    }

    /**
     * Creates a generic {@link Task} from the parsed string fields.
     * This is used if no specific type identifier is found in the string.
     *
     * @param fields an array of string fields representing the task attributes
     * @return a new {@link Task} object
     */
    private Task createTask(String[] fields) {
        boolean isDone = Boolean.parseBoolean(fields[0]);
        String name = fields[1];
        return new Task(name, isDone);
    }

    private ToDo createToDo(String[] fields) {
        boolean isDone = Boolean.parseBoolean(fields[1]);
        String name = fields[2];
        return new ToDo(name, isDone);
    }

    private Event createEvent(String[] fields) {
        boolean isDone = Boolean.parseBoolean(fields[1]);
        String name = fields[2];
        String startDate = fields[3];
        String endDate = fields[4];
        return new Event(name, isDone, startDate, endDate);
    }

    private Deadline createDeadline(String[] fields) {
        boolean isDone = Boolean.parseBoolean(fields[1]);
        String name = fields[2];

        String dueDate = fields[3];
        return new Deadline(name, isDone, dueDate);
    }
}
