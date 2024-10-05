package filemanager;

import tasks.Task;
import tasks.Deadline;
import tasks.Event;
import tasks.Todo;

import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import java.nio.file.Paths;


/**
 * The FileManager class handles the loading and saving of tasks to and from a file.
 * It supports the reading of tasks from a file, parsing them into Task objects,
 * and writing them back to the file in a specific format. The supported task types
 * include ToDo, Deadline, and Event.
 */

public class Storage {

    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Ensures the parent directory of the specified file exists.
     * If it does not exist, this method attempts to create it.
     *
     * @param file The file whose parent directory needs to be checked.
     * @return true if the directory exists or was successfully created, false otherwise.
     */
    private boolean ensureDirectoryExists(File file) {
        File parentDir = file.getParentFile();
        if (parentDir != null && !parentDir.exists()) {
            if (!parentDir.mkdirs()) {
                System.out.println("Failed to create directory: " + parentDir.getAbsolutePath());
                return false;
            }
        }
        return true;
    }

    /**
     * Fallback mechanism to handle missing directories and update the path to the current working directory.
     *
     * @return the updated path in the current working directory.
     */
    private String fallbackToCurrentDirectory() {
        try {
            String currentDir = System.getProperty("user.dir");
            String newFilePath = Paths.get(currentDir, "data", "tasks.txt").toString();

            this.filePath = newFilePath;

            File file = new File(newFilePath);
            ensureDirectoryExists(file);

            return newFilePath;
        } catch (Exception e) {
            System.out.println("Failed to fallback to current directory: " + e.getMessage());
            return null;
        }
    }

    /**
     * Loads tasks from the file at the specified file path.
     * If the file or directory does not exist, it attempts to create them.
     * If the directory is missing, it falls back to the current working directory.
     *
     * @return An ArrayList of Task objects loaded from the file.
     */

    public ArrayList<Task> loadTasks() {
        ArrayList<Task> taskList = new ArrayList<>();

        try {
            File file = new File(filePath);

            // If directory or file creation fails, fallback to the current directory
            if (!ensureDirectoryExists(file)) {
                filePath = fallbackToCurrentDirectory();
                file = new File(filePath);
            }

            // Check if the file exists, if not, create it
            if (!file.exists()) {
                if (!file.createNewFile()) {
                    System.out.println("Failed to create file: " + file.getAbsolutePath());
                    return taskList;
                }
            }

            Scanner scanner = new Scanner(new FileReader(file));
            while (scanner.hasNextLine()) {
                String taskLine = scanner.nextLine();
                taskList.add(parseTask(taskLine));
            }
            scanner.close();
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        return taskList;
    }

    /**
     * Saves the list of tasks to the file at the specified file path.
     * If the file or directory does not exist, it attempts to create them.
     * If the directory is missing, it falls back to the current working directory.
     *
     * @param tasks An ArrayList of Task objects to be saved to the file.
     */

    public void saveTasksToFile(ArrayList<Task> tasks) {
        try {
            File file = new File(filePath);

            if (!ensureDirectoryExists(file)) {
                filePath = fallbackToCurrentDirectory();
                file = new File(filePath);  // Update file reference to the new path
            }

            FileWriter writer = new FileWriter(file);
            for (Task task : tasks) {
                writer.write(task.fileFormat() + System.lineSeparator());
            }

            writer.flush();
            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving tasks to file: " + e.getMessage());
        }
    }

    /**
     * Parses a line from the file into a Task object.
     * The line is expected to be in a specific format corresponding to different task types (ToDo, Deadline, or Event).
     * Invalid or unrecognized lines are skipped.
     *
     * @param line The string representing a task from the file.
     * @return A Task object corresponding to the line or null if the line is invalid.
     */

    public Task parseTask(String line) {
        if (line.trim().isEmpty()) {
            System.out.println("Empty line found, skipping...");
            return null;
        }

        String[] parts = line.split(" \\| ");

        String taskType = parts[0];
        boolean isCompleted = parts[1].equals("+");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        switch (taskType) {
        case "T":
            if (parts.length < 3) {
                System.out.println("Invalid ToDo task format: " + line);
                return null;
            }
            String taskName = parts[2];
            Task todoTask = new Todo(taskName);
            if (isCompleted) {
                todoTask.setStatus();
            }
            return todoTask;

        case "D":
            if (parts.length < 4) {
                System.out.println("Invalid Deadline task format: " + line);
                return null;  // Skip invalid tasks
            }
            String deadlineTaskName = parts[2];
            String deadlineString = parts[3].replace("by ", "");
            LocalDateTime deadline = LocalDateTime.parse(deadlineString, formatter);
            Task deadlineTask = new Deadline(deadlineTaskName, deadline);
            if (isCompleted) {
                deadlineTask.setStatus();
            }
            return deadlineTask;

        case "E":
            if (parts.length < 4) {
                System.out.println("Invalid Event task format: " + line);
                return null;  // Skip invalid tasks
            }
            String eventTaskName = parts[2];
            String[] eventTimes = parts[3].replace("from ", "").split(" to ");
            if (eventTimes.length < 2) {
                System.out.println("Invalid Event task time format: " + line);
                return null;
            }
            String startString = eventTimes[0];
            String endString = eventTimes[1];
            LocalDateTime start = LocalDateTime.parse(startString, formatter);
            LocalDateTime end = LocalDateTime.parse(endString, formatter);
            Task eventTask = new Event(eventTaskName, start, end);
            if (isCompleted) {
                eventTask.setStatus();
            }
            return eventTask;

        default:
            System.out.println("Unrecognized task format: " + line);
            return null;
        }
    }
}
