package dobby.tasklist;

import dobby.exceptions.MissingDescriptionException;
import dobby.tasks.Deadline;
import dobby.tasks.Event;
import dobby.tasks.Task;
import dobby.tasks.Todo;

public class TaskCreator {

    public static Task createTask(String line) throws MissingDescriptionException {
        if (line.startsWith("todo")) {
            return createTodoTask(line);
        } else if (line.startsWith("deadline")) {
            return createDeadlineTask(line);
        } else if (line.startsWith("event")){
            return createEventTask(line);
        }
        return new Task(line);
    }

    private static Todo createTodoTask(String line) throws MissingDescriptionException {
        if (line.length() <= "todo".length()) {
            throw new MissingDescriptionException();
        }
        return new Todo(line.substring("todo ".length()));
    }

    public static Deadline createDeadlineTask(String line) throws MissingDescriptionException {
        String[] lineParts = line.split(" /by ");

        if (lineParts.length < 2 || lineParts[0].length() <= "deadline ".length()) {
            throw new MissingDescriptionException();
        }

        String taskDescription = lineParts[0].replaceFirst("deadline ", "");
        String byWhen = lineParts[1];
        return new Deadline(taskDescription, byWhen);
    }

    public static Event createEventTask(String line) throws MissingDescriptionException {
        String[] lineParts = line.split(" /from | /to ");

        if (lineParts.length < 3 || lineParts[0].length() <= "event ".length()) {
            throw new MissingDescriptionException();
        }

        String taskDescription = lineParts[0].replaceFirst("event ", "");
        String fromTime = lineParts[1];
        String toTime = lineParts[2];
        return new Event(taskDescription, fromTime, toTime);
    }
}
