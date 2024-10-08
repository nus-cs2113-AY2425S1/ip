package datahandling;

import task.Task;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Storage Class used to manage loading and saving task from a file.
 */
public class Storage {
    private static final Logger LOGGER = Logger.getLogger(Storage.class.getName());
    private final String filePath;

    /**
     * Construct Storage object with specified file path.
     * @param filePath path of file
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads task from file, and create the file if it does not exist.
     * @return list of tasks loaded from file
     * @throws IOException if there is an issue reading file
     */
    public List<Task> loadTaskFromFile() throws IOException {
        File file = new File(filePath);
        List<Task> tasks = new ArrayList<>();

        if (!file.exists()) {
            LOGGER.log(Level.INFO, "File not found. Creating new file: {0}", filePath);
            file.getParentFile().mkdirs();
            file.createNewFile();
            return tasks;
        }
        LOGGER.log(Level.INFO, "Loading tasks from file: {0}", filePath);

        try {
            List<String> lines = Files.readAllLines(Paths.get(filePath));
            for (String line : lines) {
                Task task = FileHandling.parseTaskFromFile(line);
                if (task != null) {
                    tasks.add(task);
                } else {
                    LOGGER.log(Level.WARNING, "Skipping invalid line: {0}", line);
                }
            }
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error occurred while reading the file.", e);
            throw e;
        }
        return tasks;
    }

    /**
     * Save list of tasks to storage file.
     * @param tasks list of task to save
     * @throws IOException if issue faced when writing to file
     */
    public void saveTasksToFile(List<Task> tasks) throws IOException {
        try (FileWriter writer = new FileWriter(filePath)) {
            for (Task task : tasks) {
                writer.write(FileHandling.formatTaskForSaving(task) + System.lineSeparator());
            }
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error occurred while writing to file.", e);
            throw e;
        }
    }
}
