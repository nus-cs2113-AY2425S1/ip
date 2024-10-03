package conglo.storage;

import conglo.task.Task;
import conglo.task.TaskList;
import conglo.task.Todo;
import conglo.task.Deadline;
import conglo.task.Event;
import conglo.exception.StorageInvalidFormat;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles loading and saving tasks from a file to provide persistent storage.
 */
public class Storage {

    private static String filePath;

    /**
     * Initializes the storage with the specified file path.
     *
     * @param filePath The path of the file to load and save tasks.
     */
    public Storage(String filePath) {
        Storage.filePath = filePath;
    }

    /**
     * Loads tasks from the storage file and returns them as an ArrayList.
     *
     * @return an ArrayList of tasks loaded from the file.
     * @throws StorageInvalidFormat If the file format is invalid.
     * @throws FileNotFoundException If the specified file does not exist.
     */
    public ArrayList<Task> loadTasks() throws StorageInvalidFormat, FileNotFoundException {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                throw new FileNotFoundException(filePath);
            }
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                String[] parts = line.split(" \\| ");

                if (parts.length < 3) {
                    throw new StorageInvalidFormat(line);
                }

                String type = parts[0];
                boolean isDone = parts[1].equals("1");
                String description = parts[2];

                Task task = null;
                switch (type) {
                case "T":
                    task = new Todo(description);
                    break;
                case "D":
                    if (parts.length < 4) {
                        throw new StorageInvalidFormat(line);
                    }
                    task = new Deadline(description, parts[3].substring(3));
                    break;
                case "E":
                    if (parts.length < 4) {
                        throw new StorageInvalidFormat(line);
                    }
                    String[] details = parts[3].split(" to ");
                    details[0] = details[0].substring(5);
                    task = new Event(description, details[0], details[1]);
                    break;
                }
                if (task != null && isDone) {
                    task.markAsDone();
                }
                tasks.add(task);
            }
            scanner.close();
        } catch (IOException e) {
            System.out.println("Error loading tasks: " + e.getMessage());
        }
        return tasks;
    }

    /**
     * Saves the current list of tasks to the storage file.
     *
     * @param taskList The TaskList object containing the tasks to be saved.
     */
    public static void saveTasks(TaskList taskList) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Task task : taskList.getTaskList()) {
                writer.write(task.toFileFormat());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }
}
