package Ryan.utility;

import Ryan.tasks.Deadline;
import Ryan.tasks.Event;
import Ryan.tasks.Task;
import Ryan.tasks.Todo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles saving and loading of tasks from a file.
 */
public class Storage {

    private static final String FILE_PATH = "./data/ryan.txt";
    private static final String SPLIT_DELIMITER = " \\| ";
    private static final String MARKED_VALUE = "1";

    private static final String TODO_TASK_TYPE = "T";
    private static final String DEADLINE_TASK_TYPE = "D";
    private static final String EVENT_TASK_TYPE = "E";

    /**
     * Saves the list of tasks to a file.
     *
     * @param tasks The list of tasks to be saved.
     */
    public static void saveTasks(ArrayList<Task> tasks) {
        try {
            File file = new File(FILE_PATH);
            file.getParentFile().mkdirs();
            if (!file.exists()) {
                file.createNewFile();
            }

            try (FileWriter fw = new FileWriter(file); BufferedWriter writer = new BufferedWriter(fw)) {
                for (Task task : tasks) {
                    writer.write(task.toFile());
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Loads the tasks from a file.
     *
     * @return The list of tasks loaded from the file.
     */
    public static ArrayList<Task> loadTasks() {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            System.out.println("No previous task file found. Starting with an empty list.");
            return tasks;
        }

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Task task = parseTaskFromFile(line);
                if (task != null) {
                    tasks.add(task);
                }
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return tasks;
    }

    /**
     * Parses a line from the file format into a Task object.
     *
     * @param line The line to be parsed.
     * @return The corresponding Task object.
     */
    private static Task parseTaskFromFile(String line) {
        String[] parts = line.split(SPLIT_DELIMITER);

        if (parts.length < 3) {
            System.out.println("Invalid task format: " + line);
            return null;
        }

        String type = parts[0];
        boolean isMarked = parts[1].equals(MARKED_VALUE);
        String description = parts[2];

        Task task;
        switch (type) {
            case TODO_TASK_TYPE:
                task = new Todo(description);
                break;
            case DEADLINE_TASK_TYPE:
                if (parts.length < 4) {
                    System.out.println("Invalid deadline format: " + line);
                    return null;
                }
                String by = parts[3];
                task = new Deadline(description, by);
                break;
            case EVENT_TASK_TYPE:
                if (parts.length < 5) {
                    System.out.println("Invalid event format: " + line);
                    return null;
                }
                String from = parts[3];
                String to = parts[4];
                task = new Event(description, from, to);
                break;
            default:
                throw new IllegalArgumentException("Unknown task type: " + type);
        }

        if (isMarked) {
            task.mark();
        }
        return task;
    }
}
