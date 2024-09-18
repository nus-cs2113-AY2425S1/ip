package akshan.storage;

import java.io.File;
import java.util.Scanner;
import java.util.Arrays;
import java.io.IOException;
import java.io.FileNotFoundException;

import akshan.task.TaskList;
import akshan.task.Task;
import akshan.task.Todo;
import akshan.task.Deadline;
import akshan.task.Event;

public class StorageHandler {
    private static File storage;
    private final static String STORAGE_FOLDER_PATH = "./data";
    private final static String STORAGE_PATH = "./data/akshan.txt";
    private final static String SEPARATOR = " \\| ";

    public StorageHandler(TaskList taskList) throws IOException {
        // Create data folder if it does not exist
        File storageFolder = new File(STORAGE_FOLDER_PATH);
        if (!storageFolder.exists()) {
            System.out.println("No data folder found, creating new data folder...");
            if (!storageFolder.mkdir()) {
                throw new IOException("Failed to create storage folder");
            }
        }
        else {
            System.out.println("Data folder found!");
        }

        // Create data file if it does not exist
        storage = new File(STORAGE_PATH);
        if (!storage.exists()) {
            System.out.println("No data file found, creating new data folder...");
            if (!storage.createNewFile()) {
                throw new IOException("Failed to create storage file");
            }
        }
        else {
            System.out.println("Data file found!");
            System.out.println("Creating list from tasks in file...");
        }

        loadData(taskList);
    }

    public void loadData(TaskList taskList) throws IOException {
        try {
            Scanner sc = new java.util.Scanner(storage);
            while (sc.hasNextLine()) {
                String data = sc.nextLine();
                String[] dataStrings = data.split(SEPARATOR);
                createTask(taskList, dataStrings);
            }
            sc.close();
        } catch (FileNotFoundException e) {
            throw new IOException("Where art thou file?");
        }
    }

    public void createTask(TaskList taskList, String[] data) throws IOException, IndexOutOfBoundsException {
        boolean isDone = data[1].equals("1");
        boolean isNotDone = data[1].equals("0");

        // Check for corrupted task state
        if (!isDone && !isNotDone) {
            throw new IOException("Oh nooooo there is corrupted data! Oh no :(");
        }
        try {
            Task taskToBeAdded = switch (data[0]) {
                case "T" -> new Todo(data[2]);
                case "D" -> new Deadline(data[2], data[3]);
                case "E" -> new Event(data[2], data[3], data[4]);
                default -> throw new IOException("Invalid task type!! Oh no :(");
            };

            // Handle task completion status and add to list
            taskToBeAdded.setStatusSilent(isDone);
            taskList.addItem(taskToBeAdded);
        }
        catch (IndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException("Invalid task input format in data file!!");
        }
    }
}