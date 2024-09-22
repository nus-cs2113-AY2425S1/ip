package data;

import exceptions.IrisException;
import task.TaskList;
import ui.Ui;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Storage {
    private static final String LOAD_SUCCESS_MESSAGE = "Loaded task list successfully!";
    private static final String LOAD_ERROR_MESSAGE = "Failed to load task list, initialising with an empty list.";
    private static final String SAVE_SUCCESS_MESSAGE = "Saved task list successfully!";
    private static final String SAVE_ERROR_MESSAGE = "Failed to save task list.";

    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public TaskList load() throws IrisException {
        TaskList tasks;
        try {
            FileInputStream fis = new FileInputStream(filePath);
            ObjectInputStream ois = new ObjectInputStream(fis);
            tasks = (TaskList) ois.readObject();
            System.out.println(LOAD_SUCCESS_MESSAGE);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(LOAD_ERROR_MESSAGE);
            throw new IrisException(e.getMessage());
        }
        return tasks;
    }

    public void save(TaskList tasks) {
        try {
            FileOutputStream fos = new FileOutputStream(filePath);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(tasks);
            System.out.println(SAVE_SUCCESS_MESSAGE);
        } catch (IOException e) {
            System.out.println(SAVE_ERROR_MESSAGE);
            Ui.showErrorMessage(e.getMessage());
        }
    }
}
