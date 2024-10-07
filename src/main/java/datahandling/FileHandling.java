package datahandling;

import task.Task;
import task.Deadline;
import task.Event;
import task.ToDo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileHandling {
    private static final Logger LOGGER = Logger.getLogger(FileHandling.class.getName());

    public static String formatTaskForSaving(Task task) {
        if (task instanceof ToDo) {
            return String.format("T | %d | %s", task.isDone ? 1 : 0, task.description);
        } else if (task instanceof Deadline) {
            Deadline deadline = (Deadline) task;
            return String.format("D | %d | %s | %s", task.isDone ? 1 : 0, task.description, deadline.getByFormatted());
        } else if (task instanceof Event) {
            Event event = (Event) task;
            return String.format("E | %d | %s | %s | %s", task.isDone ? 1 : 0, task.description, event.getFromFormatted(), event.getToFormatted());
        }
        return null;
    }

    public static Task parseTaskFromFile(String line) {
        String[] parts = line.split(" \\| ");
        if (parts.length < 3) {
            LOGGER.log(Level.WARNING, "Skipping invalid line: {0}", line);
            return null;
        }
    
        String taskType = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];
    
        try {
            switch (taskType) {
                case "T":
                    ToDo todo = new ToDo(description);
                    if (isDone) todo.markTaskDone();
                    return todo;
                case "D":
                    // Check if there are enough parts for a Deadline
                    if (parts.length < 4) {
                        throw new IllegalArgumentException("Deadline task entry missing date.");
                    }
                    Deadline deadline = new Deadline(description, parts[3]);
                    if (isDone) deadline.markTaskDone();
                    return deadline;
                case "E":
                    // Check if there are enough parts for an Event
                    if (parts.length < 5) {
                        throw new IllegalArgumentException("Event task entry missing date range.");
                    }
                    Event event = new Event(description, parts[3], parts[4]);
                    if (isDone) event.markTaskDone();
                    return event;
                default:
                    LOGGER.log(Level.WARNING, "Unknown task type in line: {0}", line);
                    return null;
            }
        } catch (DateTimeParseException | IllegalArgumentException e) {
            LOGGER.log(Level.WARNING, "Skipping invalid date format in task entry: {0}", line);
            return null;
        } catch (ArrayIndexOutOfBoundsException e) {
            LOGGER.log(Level.WARNING, "Skipping line due to missing fields: {0}", line);
            return null;
        }
    }

    public static void saveTasksToFile(List<Task> tasks, String filePath) {
        List<String> lines = new ArrayList<>();
        for (Task task : tasks) {
            lines.add(formatTaskForSaving(task));
        }

        try {
            Files.write(Paths.get(filePath), lines, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error writing tasks to file.", e);
        }
    }

    public static List<Task> loadTasksFromFile(String filePath) {
        List<Task> tasks = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(Paths.get(filePath));
            for (String line : lines) {
                Task task = parseTaskFromFile(line);
                if (task != null) {
                    tasks.add(task);
                }
            }
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error loading tasks from file.", e);
        }
        return tasks;
    }
}
