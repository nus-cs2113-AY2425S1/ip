package dobby.tasklist;

import dobby.exceptions.InvalidDescriptionException;
import dobby.tasks.Deadline;
import dobby.tasks.Event;
import dobby.tasks.Task;
import dobby.tasks.Todo;

/**
 * The TaskCreator class is responsible for creating different types of Task objects
 * based on user input. It handles the parsing of command strings to extract
 * task details and instantiate the appropriate Task subclass.
 */
public class TaskCreator {

    /**
     * Creates a task based on the provided line command.
     *
     * @param line The command line input from the user.
     * @return The created Task object.
     * @throws InvalidDescriptionException If the task description is missing.
     */
    public static Task createTask(String line) throws InvalidDescriptionException {
        if (line.startsWith("todo")) {
            return createTodoTask(line);
        } else if (line.startsWith("deadline")) {
            return createDeadlineTask(line);
        } else if (line.startsWith("event")){
            return createEventTask(line);
        }
        return new Task(line);
    }

    /**
     * Creates a Todo task from the given command line.
     *
     * @param line The command line input for the Todo task.
     * @return The created Todo object.
     * @throws InvalidDescriptionException If the task description is missing.
     */
    public static Todo createTodoTask(String line) throws InvalidDescriptionException {
        if (line.length() <= "todo".length()) {
            throw new InvalidDescriptionException();
        }
        return new Todo(line.substring("todo ".length()).strip());
    }

    /**
     * Creates a Deadline task from the given command line.
     *
     * @param line The command line input for the Deadline task.
     * @return The created Deadline object.
     * @throws InvalidDescriptionException If the task description or due date is missing.
     */
    public static Deadline createDeadlineTask(String line) throws InvalidDescriptionException {
        String[] lineParts = line.split(" /by ");

        if (lineParts.length < 2 || lineParts[0].length() <= "deadline ".length()) {
            throw new InvalidDescriptionException();
        }

        String taskDescription = lineParts[0].replaceFirst("deadline ", "");
        String byWhen = lineParts[1];
        return new Deadline(taskDescription.strip(), byWhen.strip());
    }

    /**
     * Creates an Event task from the given command line.
     *
     * @param line The command line input for the Event task.
     * @return The created Event object.
     * @throws InvalidDescriptionException If the task description or time information is missing.
     */
    public static Event createEventTask(String line) throws InvalidDescriptionException {
        String[] lineParts = line.split(" /from | /to ");

        if (lineParts.length != 3 || lineParts[0].trim().length() <= "event ".length()) {
            throw new InvalidDescriptionException();
        }

        String taskDescription = lineParts[0].replaceFirst("event ", "").strip();
        String fromTime = lineParts[1].strip();
        String toTime = lineParts[2].strip();
        return new Event(taskDescription, fromTime, toTime);
    }
}
