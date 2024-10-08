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

/**
 * Class for handling all file operations (saving and parsing tasks) related to tasks
 */
public class FileHandling {
    private static final Logger LOGGER = Logger.getLogger(FileHandling.class.getName());

    /**
     * Format task into a string that is suitable to be saved into output file
     * @param task task to format
     * @return formatted string representing task, or null if task type is unrecognisable
     */
    public static String formatTaskForSaving(Task task) {
        if (task instanceof ToDo) {
            return String.format("T | %d | %s", task.isDone ? 1 : 0, task.description);
        } else if (task instanceof Deadline) {
            Deadline deadline = (Deadline) task;
            return String.format("D | %d | %s | %s", task.isDone ? 1 : 0, task.description, deadline.getByForSaving());
        } else if (task instanceof Event) {
            Event event = (Event) task;
            return String.format("E | %d | %s | %s | %s", task.isDone ? 1 : 0, task.description, event.getFromForSaving(), event.getToForSaving());
        }
        return null;
    }

    /**
     * Parses task detail from a formatted string to read from file.
     * @param line formatted string representing task
     * @return parse Task object, else null if format is incorrect
     */
    public static Task parseTaskFromFile(String line) {
        // Trim whitespace and check for empty lines
        if (line == null || line.trim().isEmpty()) {
            // Skip empty or whitespace-only lines without logging
            return null;
        }

        // Split the line and check parts
        String[] parts = line.trim().split(" \\| ");
        if (parts.length < 3) {
            // Log only if the line is non-empty but has insufficient parts
            LOGGER.log(Level.WARNING, "Skipping invalid line due to insufficient parts: {0}", line);
            return null;
        }

        String taskType = parts[0].trim();
        boolean isDone = "1".equals(parts[1].trim());
        String description = parts[2].trim();

        try {
            switch (taskType) {
            case "T":
                if (description.isEmpty()) {
                    LOGGER.log(Level.WARNING, "Skipping ToDo task with missing description: {0}", line);
                    return null;
                }
                ToDo todo = new ToDo(description);
                if (isDone) todo.markTaskDone();
                return todo;

            case "D":
                if (parts.length < 4 || parts[3].trim().isEmpty()) {
                    LOGGER.log(Level.WARNING, "Skipping Deadline with missing deadline date: {0}", line);
                    return null;
                }
                Deadline deadline = new Deadline(description, parts[3].trim());
                if (isDone) deadline.markTaskDone();
                return deadline;

            case "E":
                if (parts.length < 5 || parts[3].trim().isEmpty() || parts[4].trim().isEmpty()) {
                    LOGGER.log(Level.WARNING, "Skipping Event with missing start/end time: {0}", line);
                    return null;
                }
                Event event = new Event(description, parts[3].trim(), parts[4].trim());
                if (isDone) event.markTaskDone();
                return event;

            default:
                LOGGER.log(Level.WARNING, "Skipping line due to unknown task type: {0}", line);
                return null;
            }
        } catch (DateTimeParseException e) {
            LOGGER.log(Level.WARNING, "Skipping line due to date format issue: {0} | Error: {1}", new Object[]{line, e.getMessage()});
            return null;
        } catch (IllegalArgumentException e) {
            LOGGER.log(Level.WARNING, "Skipping line due to invalid data: {0} | Error: {1}", new Object[]{line, e.getMessage()});
            return null;
        }
    }

    /**
     * Saves list of tasks into file defined in file path.
     * @param tasks list of tasks to be saved
     * @param filePath path of file to save tasks into
     */
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

    /**
     * Load task from specified file path.
     * @param filePath path of file to load tasks from
     * @return list of tasks loaded from file
     */
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
