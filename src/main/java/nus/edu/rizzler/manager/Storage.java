package nus.edu.rizzler.manager;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    private final String FILE_PATH = "src/main/java/nus/edu/rizzler/data/RizzlerData.txt";

    public Storage() {
        try {
            createFileIfNotExists();
        } catch (IOException e) {
            System.out.println("Failed to initialize Rizzler.java to store tasks: " + e.getMessage());
        }
    }

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

    public void writeToFile(String tasksString) throws IOException {
        FileWriter fileWriter = new FileWriter(FILE_PATH);
        fileWriter.write(tasksString);
        fileWriter.close();
    }

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

