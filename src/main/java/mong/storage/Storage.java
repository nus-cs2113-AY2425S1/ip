package mong.storage;

import mong.task.Task;
import mong.ui.Parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import static mong.task.TaskList.list;

public class Storage {
    public static final String DIRECTORY_NAME = "./src/main/java/mong/data/";
    public static final String FILE_NAME = "mong.txt";
    public static final String FILE_PATH =  DIRECTORY_NAME + FILE_NAME;
    static File directory = new File(DIRECTORY_NAME);
    static File file = new File(FILE_PATH);

    public Storage() {

    }

    /**
     * Writes new content to txt file.
     */
    private static void writeToFile(String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(FILE_PATH);
        fw.write(textToAdd);
        fw.close();
    }

    /**
     * Saves new content to file.
     */
    public static void saveToFile() {
        try {
            writeToFile(parseListToTxt());
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    /**
     * Formats latest list to txt file format.
     */
    public static String parseListToTxt() {
        StringBuilder textToAdd = new StringBuilder();
        for (Task task : list) {
            textToAdd.append(task.toFileFormat()).append(System.lineSeparator());
        }
        return textToAdd.toString();
    }

    /**
     * Adds all tasks in txt file.
     */
    public static void loadFileContents() throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            Parser.parseLineContents(line);
        }
    }

    public static void handleFileDoesNotExist() throws IOException {
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
        }
    }

    public static void handleFolderDoesNotExist() throws IOException {
        if (!directory.exists()) {
            directory.mkdir();
            handleFileDoesNotExist();
        }
    }
}
