package aether.storage;

import aether.task.Task;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles loading and saving tasks to a file.
 * <p>
 * This class provides functionalities to persist tasks by saving them to a specified file
 * and retrieving them when needed. It ensures that the task data is consistently maintained
 * across different sessions of the application.
 * </p>
 */
public class Storage {
    private Path filePath;

    /**
     * Constructs a {@code Storage} object with the specified file path.
     *
     * @param filePath The path to the file where tasks will be stored.
     */
    public Storage(String filePath) {
        this.filePath = Paths.get(filePath);
    }

    /**
     * Loads tasks from the storage file.
     *
     * @return An {@code ArrayList} containing all {@code Task} objects loaded from the file.
     * @throws IOException If an I/O error occurs while reading the file.
     */
    public ArrayList<Task> load() throws IOException {
        ArrayList<Task> tasks = new ArrayList<>();

        if (!Files.exists(filePath)) {
            Files.createDirectories(filePath.getParent());
            Files.createFile(filePath);
            return tasks;
        }

        List<String> lines = Files.readAllLines(filePath);
        for (String line : lines) {
            tasks.add(Task.fromStorage(line));
        }

        return tasks;
    }

    /**
     * Saves the current list of tasks to the storage file.
     *
     * @param tasks The {@code ArrayList} of {@code Task} objects to be saved.
     * @throws IOException If an I/O error occurs while writing to the file.
     */
    public void save(ArrayList<Task> tasks) throws IOException {
        List<String> lines = new ArrayList<>();

        for (Task task : tasks) {
            lines.add(task.toDataString());
        }

        Files.write(filePath, lines, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
    }
}
