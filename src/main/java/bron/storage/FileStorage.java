package bron.storage;

import bron.task.Task;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FileStorage {
    private static final String FILE_PATH = "./storage/data.txt";

    public FileStorage() {
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