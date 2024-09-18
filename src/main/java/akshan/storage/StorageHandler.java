package akshan.storage;

import java.io.File;
import java.util.Scanner;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.nio.file.Path;
import java.nio.file.Paths;

import akshan.task.TaskList;
import akshan.task.Task;
import akshan.task.Todo;
import akshan.task.Deadline;
import akshan.task.Event;

public class StorageHandler {
    private static File storage;
    private static final Path STORAGE_FOLDER_PATH = Paths.get("data");
    private static final Path STORAGE_PATH = STORAGE_FOLDER_PATH.resolve("akshan.txt");
    private static final String SEPARATOR = " <-> ";

    public StorageHandler(TaskList taskList) throws IOException {
        // Create data folder if it does not exist
        File storageFolder = new File(String.valueOf(STORAGE_FOLDER_PATH));
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
        storage = new File(String.valueOf(STORAGE_PATH));
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

    public void saveData(TaskList taskList) throws IOException {
        try {
            FileWriter fw = new FileWriter(String.valueOf(STORAGE_PATH));

            for (int i = 0; i < taskList.size(); i++) {
                Task task = taskList.getTask(i);
                String data = task.toStorageString(SEPARATOR);
                fw.write(data + "\n");
            }

            fw.close();
        } catch (IOException e) {
            throw new IOException("Where art thou file?");
        }
    }
}