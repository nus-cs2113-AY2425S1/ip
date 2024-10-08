package nus.edu.rizzler.manager;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Handles reading from and writing to the storage file that contains task data.
 */
public class Storage {
    private final String FILE_PATH = "src/main/java/nus/edu/rizzler/data/RizzlerData.txt";

    /**
     * Initializes the storage file by creating it if it does not exist.
     */
    public Storage() {
        try {
            createFileIfNotExists();
        } catch (IOException e) {
            System.out.println("Failed to initialize Rizzler.java to store tasks: " + e.getMessage());
        }
    }

    /**
     * Reads and returns all data from the storage file.
     *
     * @return A string containing all task data.
     * @throws IOException If an error occurs during file reading.
     */
    public String readFromFile() throws IOException {
        File file = new File(FILE_PATH);
        Scanner scanner = new Scanner(file);
        StringBuilder tasksString = new StringBuilder();

        while (scanner.hasNext()) {
            tasksString.append(scanner.nextLine());
            tasksString.append("\n");
        }

        scanner.close();
        return tasksString.toString();
    }

    /**
     * Writes the given task data to the storage file.
     *
     * @param tasksString The task data to write to the file.
     * @throws IOException If an error occurs during file writing.
     */
    public void writeToFile(String tasksString) throws IOException {
        FileWriter fileWriter = new FileWriter(FILE_PATH);
        fileWriter.write(tasksString);
        fileWriter.close();
    }

    /**
     * Creates the storage file and necessary directories if they do not exist.
     *
     * @throws IOException If the directory or file creation fails.
     */
    private void createFileIfNotExists() throws IOException {
        File dataFile = new File(FILE_PATH);
        File dataDirectory = dataFile.getParentFile();

        if (!dataDirectory.exists()) {
            boolean isSuccessful = dataDirectory.mkdirs();

            if (!isSuccessful) {
                throw new IOException("Failed to create directory: " + dataDirectory.getAbsolutePath());
            }
        }

        if (!dataFile.exists()) {
            boolean isSuccessful = dataFile.createNewFile();
            if (!isSuccessful) {
                throw new IOException("Failed to create file: " + dataFile.getAbsolutePath());
            }
        }
    }
}

