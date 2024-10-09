package bron.storage;

import bron.task.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class FileStorage {
    private static final String FILE_PATH = "./storage/data.txt";

    public FileStorage() {
    }

    public TaskList load() {
        TaskList taskList = new TaskList();
        Path path = Paths.get(FILE_PATH);

        if (!Files.exists(path)) {
            System.out.println("No existing task file found, starting with an empty task list.");
            return taskList;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Task task = parseTask(line);
                if (task != null) {
                    taskList.addTask(task);
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while loading tasks.");
        }

        return taskList;
    }

    private Task parseTask(String line) {
        String[] parts = line.split(" \\| ");

        if (parts.length < 3) {
            System.out.println("Error parsing line: " + line);
            return null;
        }

        String type = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];

        switch (type) {
        case "T":
            return new ToDo(description, isDone);
        case "D":
            if (parts.length < 4) {
                System.out.println("Error parsing Deadline task (too few parts): " + line);
                return null;
            }
            String by = parts[3];
            return new Deadline(description, by, isDone);
        case "E":
            if (parts.length < 5) {
                System.out.println("Error parsing Event task (too few parts): " + line);
                return null;
            }
            String from = parts[3];
            String to = parts[4];
            return new Event(description, from, to, isDone);
        default:
            System.out.println("Unknown task type: " + type);
            return null;
        }
    }

    public void save(TaskList taskList) {
        Path directoryPath = Paths.get("./storage");
        try {
            if (!Files.exists(directoryPath)) {
                Files.createDirectories(directoryPath);
            }
        } catch (IOException e) {
            System.out.println("Failed to create directory for saving tasks.");
            return;
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {

            for (int i = 0; i < taskList.size(); i++) {
                Task task = taskList.getTask(i);  // Access task using TaskList
                writer.write(task.toSaveFormat() + System.lineSeparator());
            }

        } catch (IOException e) {
            System.out.println("An error occurred while saving tasks.");
        }
    }
}