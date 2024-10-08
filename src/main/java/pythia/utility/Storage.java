package pythia.utility;

import pythia.task.Task;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.io.*;

/**
 * The {@code Storage} class is responsible for saving
 * and loading {@link TaskList} objects to and from a file.
 * It provides methods to serialize a task list into a file
 * and deserialize a task list from a file.
 */
public class Storage {
    private String filePath;
    private WriteVisitor writeVisitor;

    /**
     * Constructs a {@code Storage} object with the specified file path.
     *
     * @param filePath the path to the file where the {@link TaskList}
     *                will be stored or read from
     */
    public Storage(String filePath) {
        this.filePath = filePath;
        this.writeVisitor = new WriteVisitor();
    }

    /**
     * Saves the given {@link TaskList} to a file at the specified path.
     * Each task in the task list is serialized into a string representation
     * using the {@link WriteVisitor} and written to the file.
     *
     * @param taskList the {@link TaskList} object to be saved to the file
     * @throws IOException if an I/O error occurs during writing to the file
     */
    public void save(TaskList taskList) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, false))) {
            for (Task task : taskList) {
                String taskAsString = task.accept(writeVisitor);
                writer.write(taskAsString);
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    /**
     * Loads a {@link TaskList} from the file at the specified path.
     * Each line in the file is deserialized into a {@link Task} object using
     * the {@link TaskFromStringFactory} and added to a new task list.
     *
     * @return the newly created {@link TaskList} object loaded from the file
     */
    public TaskList load() {
        TaskList taskList = new TaskList();
        TaskFromStringFactory factory = new TaskFromStringFactory();
        Path path = Paths.get(filePath);
        Path directory = path.getParent();  // Get the directory part of the path

        // Check if the directory exists, if not create it
        if (directory != null && Files.notExists(directory)) {
            try {
                Files.createDirectories(directory);  // Create all missing directories
            } catch (IOException e) {
                System.err.println("Error creating directory: " + e.getMessage());
                return taskList; // Return empty task list if directory creation fails
            }
        }

        // Check if the file exists, if not create an empty file
        if (Files.notExists(path)) {
            try {
                Files.createFile(path);
            } catch (IOException e) {
                System.err.println("Error creating new file: " + e.getMessage());
                return taskList; // Return empty task list if file creation fails
            }
            return taskList; // Return empty task list for a newly created file
        }

        // If file exists, read the tasks from the file
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String taskAsString;
            while ((taskAsString = reader.readLine()) != null) {
                Task task = factory.create(taskAsString);
                taskList.add(task);
            }
        } catch (IOException e) {
            System.err.println("Error reading from file: " + e.getMessage());
        }

        return taskList;
    }
}
