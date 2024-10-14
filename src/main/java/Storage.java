package main.java;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() throws KenChatException {
        ArrayList<Task> doList = new ArrayList<>();
        File file = new File(filePath);

        if (!file.exists()) {
            System.out.println("Data file no found. Beginning with a new empty task list.");
            return doList;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Task task = getTask(line);
                doList.add(task);
            }
        } catch (IOException e) {
            throw new KenChatException("Error loading data from file: " + e.getMessage());
        }
        return doList;
    }

    private static Task getTask(String line) throws KenChatException {
        String[] parts = line.split(" \\| ");
        Task task;
        switch (parts[0]) {
        case "T":
            task = new Task.ToDo(parts[2]);
            break;
        case "D":
            task = new Task.Deadline(parts[2], parts[3]);
            break;
        case "E":
            task = new Task.Event(parts[2], parts[3], parts[4]);
            break;
        default:
            throw new KenChatException("Corrupted data file.");
        }
        if (parts[1].equals("1")) {
            task.markAsDone();
        }
        return task;
    }

    public void save(ArrayList<Task> doList) throws KenChatException {
        File file = new File(filePath);

        // Ensure the directory exists
        File directory = new File(file.getParent());
        if (!directory.exists()) {
            if (directory.mkdirs()) {
                System.out.println("Directory created successfully.");
            } else {
                throw new KenChatException(KenChatException.directoryCreationFailure());
            }
        }

        try (FileWriter writer = new FileWriter(file)) {
            for (Task task : doList) {
                writer.write(task.formatForStorage() + System.lineSeparator());
            }
        } catch (IOException e) {
            throw new KenChatException("Error saving data to file: " + e.getMessage());
        }
    }
}
