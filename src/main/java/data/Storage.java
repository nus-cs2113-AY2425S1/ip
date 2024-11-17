package data;

import exceptions.IrisException;
import task.TaskList;
import ui.Ui;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * The Storage class is responsible for saving and loading the task list
 * from a file. It handles the serialization and deserialization of
 * TaskList objects, enabling persistent storage of tasks.
 * <p>
 * This class provides methods to load tasks from a file and save them back
 * to the file for future use.
 *
 * @author Tan Ping Hui
 */
public class Storage {
    private final String filePath;

    /**
     * Constructs a Storage object that manages the saving and loading of tasks
     * from the specified file path.
     *
     * @param filePath The path to the file where tasks are saved and loaded from.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads the task list by deserializing the data from a file at the given file path.
     * If the file is not found or cannot be read, an {@link IrisException} is thrown.
     *
     * @return The loaded task list.
     */
    public TaskList load() {
        TaskList tasks;
        try {
            FileInputStream fis = new FileInputStream(filePath);
            ObjectInputStream ois = new ObjectInputStream(fis);
            tasks = (TaskList) ois.readObject();
            Ui.printLoadSuccessMessage();
        } catch (IOException | ClassNotFoundException e) {
            Ui.printLoadErrorMessage();
            tasks = new TaskList();
        }
        return tasks;
    }

    /**
     * Saves the current task list by serializing it to a file at the given file path.
     * If an I/O error occurs, an error message is displayed to the user.
     *
     * @param tasks The task list to be saved.
     */
    public void save(TaskList tasks) {
        try {
            FileOutputStream fos = new FileOutputStream(filePath);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(tasks);
            Ui.printSaveSuccessMessage();
        } catch (IOException e) {
            Ui.printSaveErrorMessage(e.getMessage());
        }
    }
}
