package bron.storage;

import bron.task.Deadline;
import bron.task.Event;
import bron.task.Task;
import bron.task.ToDo;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class FileStorage {
    private static final String FILE_PATH = "./storage/data.txt";

    public FileStorage() {
    }
    public ArrayList<Task> load() {
        ArrayList<Task> tasks = new ArrayList<>();
        Path path = Paths.get(FILE_PATH);

        if (!Files.exists(path)) {
            System.out.println("No existing task file found, starting with an empty task list.");
            return tasks;
        }

        // Read tasks from the file
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Task task = parseTask(line);  // Convert file data into Task objects
                if (task != null) {
                    tasks.add(task);
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while loading tasks.");
            e.printStackTrace();
        }

        return tasks;  // Return the list of tasks
    }

    private Task parseTask(String line) {
        String[] parts = line.split(" \\| ");
        String type = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];

        switch (type) {
        case "T":
            return new ToDo(description, isDone);
        case "D":
            String by = parts[3];
            return new Deadline(description, by, isDone);
        case "E":
            String from = parts[3];
            String to = parts[4];
            return new Event(description, from, to, isDone);
        default:
            return null;  // Handle unknown task types
        }
    }

    public void save(ArrayList<Task> tasks) {
        Path directoryPath = Paths.get("./storage");
        try {
            if (!Files.exists(directoryPath)) {
                Files.createDirectories(directoryPath);  // Create the storage directory if it doesn't exist
            }
        } catch (IOException e) {
            System.out.println("Failed to create directory for saving tasks.");
            e.printStackTrace();
            return; // Exit if directory creation fails
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {

            for (Task task : tasks) {
                writer.write(task.toSaveFormat() + System.lineSeparator());
            }

        } catch (IOException e) {
            System.out.println("An error occurred while saving tasks.");
            e.printStackTrace();
        }
    }
}