import java.io.*;
import java.util.*;

/**
 * The Storage class is responsible for reading and writing tasks from and to a file.
 * It handles the loading of tasks from a file during startup and saves tasks back to the file.
 */
public class Storage {
    private static final String FILE_PATH = "./data/andy.txt"; // File path for storing tasks

    /**
     * Loads tasks from the specified file. If the file does not exist, returns an empty task list.
     *
     * @return A list of tasks loaded from the file.
     */
    public List<Task> loadTasks() {
        List<Task> tasks = new ArrayList<>();
        File file = new File(FILE_PATH);

        if (!file.exists()) {
            return tasks; // Return empty list if file doesn't exist
        }

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String taskLine = scanner.nextLine();
                Task task = parseTask(taskLine);
                if (task != null) {
                    tasks.add(task);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("No saved tasks found.");
        }
        return tasks;
    }

    /**
     * Parses a line of text into a Task object.
     *
     * @param taskLine The line of text representing a task.
     * @return The parsed Task object, or null if the line is malformed.
     */
    private Task parseTask(String taskLine) {
        String[] parts = taskLine.split(" \\| ");
        if (parts.length < 3) {
            return null; // Skip malformed lines
        }

        String taskType = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];

        Task task = createTask(taskType, description, parts);
        if (task != null) {
            task.setDone(isDone);
        }
        return task;
    }

    /**
     * Creates a Task object based on the task type and parts.
     *
     * @param taskType The type of task ("T", "D", "E").
     * @param description The description of the task.
     * @param parts The split parts of the task line.
     * @return The created Task object, or null if the type is unrecognized or parts are insufficient.
     */
    private Task createTask(String taskType, String description, String[] parts) {
        return switch (taskType) {
            case "T" -> new TodoTask(description);
            case "D" -> parts.length >= 4 ? new DeadlineTask(description, parts[3]) : null;
            case "E" -> createEventTask(description, parts);
            default -> null;
        };
    }

    /**
     * Creates an EventTask if the parts contain valid from and to times.
     *
     * @param description The task description.
     * @param parts The split parts of the task line.
     * @return The EventTask object, or null if the event parts are insufficient.
     */
    private EventTask createEventTask(String description, String[] parts) {
        if (parts.length < 4) return null;

        String[] eventParts = parts[3].split(" /to ");
        if (eventParts.length != 2) return null;

        String from = eventParts[0].trim();
        String to = eventParts[1].trim();
        return new EventTask(description, from, to);
    }

    /**
     * Saves the given list of tasks to the file.
     *
     * @param tasks The list of tasks to be saved to the file.
     */
    public void saveTasks(List<Task> tasks) {
        if (!ensureDirectoryExists()) return;

        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            for (Task task : tasks) {
                String taskLine = formatTaskForSaving(task);
                writer.write(taskLine + System.lineSeparator());
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }

    /**
     * Ensures the directory for the file exists.
     *
     * @return true if the directory exists or was created successfully, false otherwise.
     */
    private boolean ensureDirectoryExists() {
        File directory = new File("./data");
        if (!directory.exists() && !directory.mkdirs()) {
            System.out.println("Failed to create directory for task storage.");
            return false;
        }
        return true;
    }

    /**
     * Formats a Task object as a string for saving to the file.
     *
     * @param task The Task object to format.
     * @return The formatted string representing the task.
     */
    private String formatTaskForSaving(Task task) {
        String taskLine = task.getType() + " | " + (task.isDone() ? "1" : "0") + " | " + task.getDescription();
        if (task instanceof DeadlineTask) {
            taskLine += " | " + ((DeadlineTask) task).getBy();
        } else if (task instanceof EventTask) {
            taskLine += " | " + ((EventTask) task).getEventDetails();
        }
        return taskLine;
    }
}