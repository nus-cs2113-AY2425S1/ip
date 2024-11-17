package tars.storage;

import tars.task.Deadline;
import tars.task.Event;
import tars.task.Task;
import tars.task.Todo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Handles reading and writing tasks to and from a file.
 */
public class Storage {
    private final String filePath;

    /**
     * Constructs a Storage object with a specified file path.
     *
     * @param filePath The file path where tasks will be stored.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Saves the list of tasks to the specified file.
     *
     * @param tasks The list of tasks to be saved.
     * @throws IOException If there is an error writing to the file.
     */
    public void saveTasks(List<Task> tasks) throws IOException {
        File file = new File(filePath);
        createDirectoryIfNotExists(file);

        try (FileWriter fileWriter = new FileWriter(file)) {
            for (Task task : tasks) {
                fileWriter.write(task.toSaveFormat() + System.lineSeparator());
            }
        } catch (IOException e) {
            System.err.println("Error saving tasks: " + e.getMessage());
            throw e;  // Re-throw the exception for upper-level handling.
        }
    }

    /**
     * Loads tasks from the specified file.
     *
     * @return A list of tasks loaded from the file.
     * @throws IOException If there is an error reading from the file.
     */
    public List<Task> loadTasks() throws IOException {
        List<Task> tasks = new ArrayList<>();
        File file = new File(filePath);

        if (!file.exists()) {
            return tasks;  // Return an empty list if the file does not exist.
        }

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String taskString = scanner.nextLine();
                Task task = convertStringToTask(taskString);
                if (task != null) {
                    tasks.add(task);
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading tasks: " + e.getMessage());
            throw e;
        }
        return tasks;
    }

    /**
     * Converts a string from the file to a Task object.
     *
     * @param taskString The string representation of a task.
     * @return The corresponding Task object, or null if the task type is unknown.
     */
    private Task convertStringToTask(String taskString) {
        String[] taskParts = taskString.split(" \\| ");
        String type = taskParts[0];
        boolean isDone = taskParts[1].equals("1");

        switch (type) {
            case "T":
                return new Todo(taskParts[2], isDone);
            case "D":
                return new Deadline(taskParts[2], taskParts[3], isDone);
            case "E":
                return new Event(taskParts[2], taskParts[3], taskParts[4], isDone);
            default:
                System.err.println("Unknown task type found: " + type);
                return null;  // Return null to handle unknown task types.
        }
    }

    /**
     * Creates the directory for storing the file if it does not already exist.
     *
     * @param file The file for which the directory is to be created.
     */
    private void createDirectoryIfNotExists(File file) {
        File directory = file.getParentFile();
        if (directory != null && !directory.exists()) {
            boolean isCreated = directory.mkdirs();
            if (isCreated) {
                System.out.println("Directory created: " + directory.getAbsolutePath());
            }
        }
    }
}
