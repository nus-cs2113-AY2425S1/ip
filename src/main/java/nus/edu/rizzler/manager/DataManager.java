package nus.edu.rizzler.manager;

import nus.edu.rizzler.exception.InvalidInputException;
import nus.edu.rizzler.task.Task;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class DataManager {
    private final String FILE_PATH = "src/main/java/nus/edu/rizzler/data/RizzlerData.txt";

    public DataManager() {
        try {
            createFileIfNotExists();
        } catch (IOException e) {
            System.out.println("Error initializing data file: " + e.getMessage());
        }
    }

    private void createFileIfNotExists() throws IOException {
        File dataFile = new File(FILE_PATH);
        File dataDirectory = dataFile.getParentFile();

        if (!dataDirectory.exists()) {
            dataDirectory.mkdirs();
            System.out.println("Data directory created: " + dataDirectory.getAbsolutePath());
        }

        if (!dataFile.exists()) {
            dataFile.createNewFile();
            System.out.println("Data file created: " + dataFile.getAbsolutePath());
        }
    }

    public void writeToFile(ArrayList<String> data) throws IOException {
        createFileIfNotExists();
        FileWriter fileWriter = new FileWriter(FILE_PATH);

        for (String line : data) {
            fileWriter.write(line + System.lineSeparator());
        }
        fileWriter.close();
    }

    public void appendToFile(String textToAppend) throws IOException {
        createFileIfNotExists();
        FileWriter fileWriter = new FileWriter(FILE_PATH, true);

        fileWriter.write(textToAppend + System.lineSeparator());
        fileWriter.close();
    }

    public ArrayList<String> readFromFile() throws IOException {
        File file = new File(FILE_PATH);
        Scanner scanner = new Scanner(file);

        ArrayList<String> savedDataList = new ArrayList<>();
        try {
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                Task.parseSavedString(line);
                savedDataList.add(line);
            }
        } catch (InvalidInputException e) {
            System.out.println(e.getMessage());
            clearFile();
            return new ArrayList<>();
        } finally {
            scanner.close();
        }
        return savedDataList;
    }

    private void clearFile() throws IOException {
        FileWriter fileWriter = new FileWriter(FILE_PATH, false);
        fileWriter.write("");
        fileWriter.close();
        System.out.println("Corrupted data has been cleared from the file.");
    }
}
