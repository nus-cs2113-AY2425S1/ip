package medea.core;

import medea.exceptions.MedeaException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Handles the storage of tasks in the task management system.
 * This class provides methods for loading and saving tasks from/to a specified file.
 */
public class Storage {
    /** The path to the file where tasks are stored. */
    private String path;

    /**
     * Constructs a Storage object with the specified file path.
     *
     * @param path the path to the file for storing tasks
     */
    public Storage(String path) {
        this.path = path;
    }

    /**
     * Loads tasks from the file.
     *
     * @return a string representation of the tasks loaded from the file
     * @throws MedeaException if loading fails
     */
    public String loadTasks() {
        try {
            return getTasksFromFile();
        } catch (FileNotFoundException e) {
            throw new MedeaException("Failed to load tasks from disk: " + e.getMessage());
        }
    }

    /**
     * Retrieves tasks from the file.
     *
     * @return a string containing all tasks from the file
     * @throws FileNotFoundException if the file does not exist
     */
    public String getTasksFromFile() throws FileNotFoundException {
        StringBuilder tasks = new StringBuilder();
        File file = new File(path);
        Scanner scanner = new Scanner(file);

        while (scanner.hasNext()) {
            tasks.append(scanner.nextLine());
            tasks.append("\n");
        }

        scanner.close();
        return tasks.toString();
    }

    /**
     * Saves tasks to the file.
     *
     * @param tasksCSVString a string representation of tasks in CSV format
     * @throws MedeaException if saving fails
     */
    public void saveTasks(String tasksCSVString) {
        try {
            writeTasksToFile(tasksCSVString);
        } catch (IOException e) {
            throw new MedeaException("Failed to save tasks to disk: " + e.getMessage());
        }
    }

    /**
     * Writes tasks to the file.
     *
     * @param csvString a string representation of tasks in CSV format
     * @throws IOException if writing to the file fails
     */
    private void writeTasksToFile(String csvString) throws IOException {
        createDirIfNotExists();
        createFileIfNotExists();
        FileWriter fileWriter = new FileWriter(path);
        fileWriter.write(csvString);
        fileWriter.close();
    }

    /**
     * Creates the directory for the file if it does not exist.
     *
     * @throws IOException if creating the directory fails
     */
    public void createDirIfNotExists() throws IOException {
        File dir = new File(path).getParentFile();

        if (dir != null && !dir.exists()) {
            boolean isMakeDirSuccessful = dir.mkdirs();
            if (!isMakeDirSuccessful) {
                throw new IOException("Failed to create directory: " + dir.getAbsolutePath());
            }
        }
    }

    /**
     * Creates the file if it does not exist.
     *
     * @throws IOException if creating the file fails
     */
    public void createFileIfNotExists() throws IOException {
        File file = new File(path);
        if (file.exists()){
            return;
        }

        boolean isMakeFileSuccessful = file.createNewFile();
        if (!isMakeFileSuccessful) {
            throw new IOException("Failed to create file: " + file.getAbsolutePath());
        }
    }
}
