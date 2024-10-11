package bron.storage;

import bron.task.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import static bron.parser.Parser.parseTask;

/**
 * Handles the loading and saving of tasks to and from a file.
 * Supports loading tasks from a file at startup and saving tasks to the file when changes are made.
 */
public class FileStorage {
    private static final String FILE_PATH = "./storage/data.txt";

    /**
     * Constructs a new FileStorage object.
     * The file path is hardcoded as './storage/data.txt'.
     */
    public FileStorage() {
    }

    /**
     * Loads tasks from the specified data file and returns them as a TaskList.
     * If the file does not exist, an empty TaskList is returned.
     *
     * @return A TaskList containing tasks loaded from the file, or an empty TaskList if the file doesn't exist.
     */
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

    /**
     * Saves the given TaskList to the file.
     * If the necessary directory does not exist, it is created before saving.
     *
     * @param taskList The TaskList to save to the file.
     */
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