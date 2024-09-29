package pythia.utility;

import pythia.task.Deadline;
import pythia.task.Event;
import pythia.task.Task;
import pythia.task.ToDo;

public class TaskFromStringFactory {
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
