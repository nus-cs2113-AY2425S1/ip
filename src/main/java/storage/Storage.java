package storage;

import tasks.Task;

import java.io.*;
import java.util.ArrayList;

public class Storage {
    private static final String DEFAULT_FILE_NAME = "dootData.txt";

    public Storage() {}

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
