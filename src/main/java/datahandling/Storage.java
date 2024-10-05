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

public class Storage {
    private static final Logger LOGGER = Logger.getLogger(Storage.class.getName());
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

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
