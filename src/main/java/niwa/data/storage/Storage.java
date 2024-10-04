package niwa.data.storage;

import niwa.data.task.Deadline;
import niwa.data.task.Event;
import niwa.data.task.Task;
import niwa.data.task.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;

/**
 * The {@code Storage} class handles the storage of tasks in a specified file.
 * It provides methods to create, read, write, and clear task data in the file system.
 */
public class Storage {
    private final File dataFile; // The file where tasks are stored

    /**
     * Constructs a {@code Storage} object with the specified file name.
     *
     * @param fileName The name of the file.
     */
    public Storage(String fileName) {
        dataFile = new File(fileName); // Initialize the file object
    }

    /**
     * Returns the data file.
     *
     * @return The {@code File} object representing the data file.
     */
    public File getDataFile() {
        return dataFile; // Return the data file
    }

    /**
     * Creates a new file.
     * Referred to AddressBook project.
     * @throws IOException If the file already exists or an error occurs while creating the file.
     */
    public void createFile() throws IOException {
        if (dataFile.exists()) {
            throw new IOException("File exists.");
        }
        try {
            if (dataFile.getParentFile() != null && !dataFile.getParentFile().exists()) {
                dataFile.getParentFile().mkdirs(); // Create parent directories if they do not exist
            }
            dataFile.createNewFile();
        } catch (IOException e) {
            throw new IOException("Can not create the file: " + e.getMessage());
        }
    }

    /**
     * Clears the content of the data file.
     *
     * @throws IOException If the file does not exist or an error occurs while clearing the file.
     */
    public void clearFile() throws IOException {
        if (!dataFile.exists()) {
            throw new FileNotFoundException("File does not exist.");
        }
        try {
            new FileWriter(dataFile, false).close(); // Clear the file content
        } catch (IOException e) {
            throw new IOException("Failed to clear the file: " + e.getMessage());
        }
    }

    /**
     * Reads the contents of the data file and returns it as a list of strings.
     * Referred to AddressBook project.
     *
     * @return An {@code ArrayList<String>} containing each line from the file.
     * @throws IOException If the file does not exist or is empty, or an error occurs while reading the file.
     */
    public ArrayList<String> readFile() throws IOException {
        if (!dataFile.exists()) {
            throw new FileNotFoundException("Data file not found.");
        }
        if (dataFile.length() == 0) {
            throw new IOException("Empty file.");
        }
        return (ArrayList<String>) Files.readAllLines(dataFile.toPath(), Charset.defaultCharset());
    }

    /**
     * Parses a list of string representations of tasks and returns an {@code ArrayList<Task>}.
     *
     * @param dataItems The list of strings to parse.
     * @return An {@code ArrayList<Task>} containing the parsed tasks.
     */
    public ArrayList<Task> parseTaskList(ArrayList<String> dataItems) {
        ArrayList<Task> allTasks = new ArrayList<>();
        for (String line : dataItems) {
            Task temp;
            switch (line.charAt(0)) { // Determine the type of task based on the first character
            case 'T':
                temp = ToDo.parseTask(line); // Parse ToDo tasks
                break;
            case 'D':
                temp = Deadline.parseTask(line); // Parse Deadline tasks
                break;
            case 'E':
                temp = Event.parseTask(line); // Parse Event tasks
                break;
            default:
                temp = null; // Unknown task type
            }
            if (temp != null) {
                allTasks.add(temp);
            }
        }
        return allTasks;
    }

    /**
     * Writes the list of tasks to the data file.
     *
     * @param tasks The list of tasks to write to the file.
     * @throws IOException If the file does not exist, an error occurs while creating/ writing to the file,
     */
    public void writeTaskList(ArrayList<Task> tasks) throws IOException {
        if (!dataFile.exists()) {
            createFile(); // Create the file if it does not exist
        } else {
            clearFile(); // Clear the file if it already exists
        }

        try (FileWriter writer = new FileWriter(dataFile, true)) {
            for (Task task : tasks) {
                writer.write(task.getFileOutput());
                writer.write("\n");
            }
        } catch (IOException e) {
            throw new IOException("Failed to save to the file: " + e.getMessage());
        }
    }

    /**
     * Loads the task list from the data file.
     *
     * @return An {@code ArrayList<Task>} containing the tasks loaded from the file.
     * @throws IOException If an error occurs while reading the file or parsing the tasks.
     */
    public ArrayList<Task> loadTaskList() throws IOException {
        ArrayList<String> taskStrings = readFile(); // Read the tasks from the file
        return parseTaskList(taskStrings); // Parse and return the tasks
    }
}
