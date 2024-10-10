package ryan.utility;

import ryan.tasks.Deadline;
import ryan.tasks.Event;
import ryan.tasks.Task;
import ryan.tasks.Todo;

import ryan.exceptions.RyanException;
import ryan.exceptions.FileIOException;
import ryan.exceptions.InvalidStorageFormatException;
import ryan.exceptions.UnknownTaskTypeException;

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
    private static final String NEW_FILE_CREATION = "No previous task file found. Starting with an empty list.";

    /**
     * Saves the list of tasks to a file.
     *
     * @param tasks The list of tasks to be saved.
     * @throws RyanException If an error occurs during the file I/O operations.
     */
    public static void saveTasks(ArrayList<Task> tasks) throws RyanException {
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
            throw new FileIOException(e.getMessage());
        }
    }

    /**
     * Loads the tasks from a file.
     *
     * @return The list of tasks loaded from the file.
     * @throws RyanException If an error occurs during the file reading process or if the file format is invalid.
     */
    public static ArrayList<Task> loadTasks() throws RyanException {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            System.out.println(NEW_FILE_CREATION);
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
            throw new FileIOException(e.getMessage());
        }
        return tasks;
    }

    /**
     * Parses a line from the file format into a Task object.
     *
     * @param line The line to be parsed.
     * @return The corresponding Task object.
     * @throws RyanException If the line format is invalid or the task type is unknown.
     */
    private static Task parseTaskFromFile(String line) throws RyanException{
        String[] parts = line.split(SPLIT_DELIMITER);

        if (parts.length < 3) {
            throw new InvalidStorageFormatException(line);
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
                    throw new InvalidStorageFormatException(line);
                }
                String by = parts[3];
                task = new Deadline(description, by);
                break;
            case EVENT_TASK_TYPE:
                if (parts.length < 5) {
                    throw new InvalidStorageFormatException(line);
                }
                String from = parts[3];
                String to = parts[4];
                task = new Event(description, from, to);
                break;
            default:
                throw new UnknownTaskTypeException(type);
        }

        if (isMarked) {
            task.mark();
        }
        return task;
    }
}
