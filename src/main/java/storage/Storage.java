package storage;

import tasks.Task;

import java.io.*;
import java.util.ArrayList;

public class Storage {
    private static final String DEFAULT_FILE_NAME = "dootData.txt";

    /**
     * Creates a new instance of the Storage class with default values.
     */
    public Storage() {
    }

    /**
     * Loads an ArrayList of tasks from the default file.
     *
     * @return ArrayList of tasks read from the default file
     */
    public ArrayList<Task> load() {
        ArrayList<Task> taskList = new ArrayList<>();
        try {
            FileInputStream fileReader = new FileInputStream(DEFAULT_FILE_NAME);
            ObjectInputStream objectReader = new ObjectInputStream(fileReader);
            boolean fileHasData = true;
            while (fileHasData) {
                try {
                    Object taskToAdd = objectReader.readObject();
                    taskList.add((Task) taskToAdd);
                } catch (EOFException e) {
                    fileHasData = false;
                }
            }
            objectReader.close();
            fileReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Read file does not exist, will be created!");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e);
        }
        return taskList;
    }

    /**
     * Writes the given ArrayList of tasks to the default file.
     *
     * @param taskList ArrayList of tasks to be written to the file
     */
    public void writeTaskData(ArrayList<Task> taskList) {
        try {
            FileOutputStream fileWriter = new FileOutputStream(DEFAULT_FILE_NAME);
            ObjectOutputStream objectWriter = new ObjectOutputStream(fileWriter);
            for (Task task : taskList) {
                if (task != null) {
                    objectWriter.writeObject(task);
                }
            }
            objectWriter.close();
            fileWriter.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
