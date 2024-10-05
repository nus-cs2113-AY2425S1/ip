package datahandling;

import task.Task;
import task.Deadline;
import task.Event;
import task.ToDo;

public class FileHandling {

    public static String formatTaskForSaving(Task task) {
        String type = task instanceof ToDo ? "T" :
                      task instanceof Deadline ? "D" : "E";
        String status = task.isDone ? "1" : "0"; 
        String description = task.description;

        if (task instanceof ToDo) {
            return type + " | " + status + " | " + description;
        } else if (task instanceof Deadline) {
            return type + " | " + status + " | " + description + " | " + ((Deadline) task).by;
        } else {
            Event event = (Event) task;
            return type + " | " + status + " | " + description + " | " + event.from + " to " + event.to;
        }
    }

    public static Task parseTaskFromFile(String line) {
        String[] parts = line.split(" \\| ");
        if (parts.length < 3) {
            return null; 
        }

        String type = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];

        switch (type) {
            case "T":
                ToDo todo = new ToDo(description);
                if (isDone) todo.markAsDone();
                return todo;
            case "D":
                Deadline deadline = new Deadline(description, parts[3]);
                if (isDone) deadline.markAsDone();
                return deadline;
            case "E":
                Event event = new Event(description, parts[3], parts[4]);
                if (isDone) event.markAsDone();
                return event;
            default:
                return null;
        }
    }
}