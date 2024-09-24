package storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import parser.FormatException;
import tasklist.TaskManager;
import tasklist.types.Deadline;
import tasklist.types.Event;
import tasklist.types.Task;
import tasklist.types.ToDo;

public class Storage {
    public static final String FILE_PATH = getJarDirectoryPath().resolve("tasklist.txt").toString(); 

    /**
     * Get the directory where the JAR file is located.
     *
     * @return The Path to the directory containing the JAR file.
     */
    public static Path getJarDirectoryPath() {
        try {
            // Get the location of the JAR file or class folder during development
            return Paths.get(Class.forName("CheonsaBot")
            .getProtectionDomain().getCodeSource()
            .getLocation().toURI()).getParent();
        } catch (ClassNotFoundException | URISyntaxException e) {
            throw new RuntimeException("Can't find JAR file path :(", e);
        }
    }

    /**
     * Appends a task to the tasklist.txt file.
     * @param task The task to write to the file on disk.
     */
    public static void appendTaskToFile(Task task) {
        try (FileWriter writer = new FileWriter(FILE_PATH, true)) {
            writer.write(formatTaskForFile(task));
            writer.write(System.lineSeparator());
        } catch (IOException e) {
            System.out.println("Oh no, error appending task to file: " 
                                + e.getMessage() + " :(");
        }
    }

    /**
     * Formats a task as a string for saving to the file.
     * @param task The task to write to the file on disk.
     * @return A line to be saved to file on disk.
     */
    public static String formatTaskForFile(Task task) {
        String taskType = "";
        if (task instanceof ToDo) {
            taskType = TaskManager.TASK_TYPE_TODO;
        } else if (task instanceof Deadline) {
            taskType = TaskManager.TASK_TYPE_DEADLINE;
        } else if (task instanceof Event) {
            taskType = TaskManager.TASK_TYPE_EVENT;
        }

        String isDone = String.valueOf(task.getIsDone());
        String description = task.getDescription();

        // Handle task-specific properties (like deadlines and event times)
        String taskDetails = "";
        if (task instanceof Deadline) {
            taskDetails = ((Deadline) task).getBy();
        } else if (task instanceof Event) {
            taskDetails = ((Event) task).getFrom() + " | " + ((Event) task).getTo();
        }

        return taskType + " | " + isDone + " | " + description + 
        (taskDetails.isEmpty() ? "" : " | " + taskDetails);
    }

    /**
     * Creates the tasklist.txt file if it doesn't exist.
     */
    public static void createFileIfNotExists() {
        Path filePath = Paths.get(FILE_PATH);
        if (!Files.exists(filePath)) {
            try {
                Files.createFile(filePath);
                System.out.println("Created tasklist.txt at " + filePath.toString());
            } catch (IOException e) {
                System.out.println("Error creating task list file: " + e.getMessage());
            }
        }
    }

    /**
     * Loads tasks from the tasklist.txt file using Scanner.
     * Skips lines that are incorrectly formatted.
     */
    public static void loadTasksFromFile() {
        try (Scanner scanner = new Scanner(new File(FILE_PATH))) {
            System.out.println("Loading tasks from taskslist.txt at " +
                                FILE_PATH + "...");
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                try {
                    Task task = parser.Parser.parseTaskFromLine(line);
                    TaskManager.tasks.add(task);
                } catch (FormatException e) {
                    System.out.println("Skipping malformed line: " + line);
                }
            }
            System.out.println("Load success!");
        } catch (FileNotFoundException e) {
            System.out.println("Error: tasklist.txt not found :(");
        }
    }

    /**
     * Updates the tasklist.txt file after deleting a task by rewriting the entire file.
     */
    public static void updateTaskFile() {
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            for (Task task : TaskManager.tasks) {
                writer.write(formatTaskForFile(task));
                writer.write(System.lineSeparator());
            }
        } catch (IOException e) {
            System.out.println("Error updating task file: " + e.getMessage() + " :(");
        }
    }
}
