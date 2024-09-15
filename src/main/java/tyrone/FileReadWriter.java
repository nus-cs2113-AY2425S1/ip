package tyrone;

import tyrone.task.TaskList;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileReadWriter {

    private static String SAVE_FILE_NAME = "./data/Tyrone.txt";
    private static String SAVE_FILE_DIR = "./data";

    public static void createSaveFile() {
        File dir = new File(SAVE_FILE_DIR);
        dir.mkdir();
        try {
            File f = new File(SAVE_FILE_NAME);
            f.createNewFile();
        } catch (IOException e) {
            System.out.println("Error creating file: " + SAVE_FILE_NAME);
        }
    }

    private static void writeToFile(String fileName, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(fileName);
        fw.write(textToAdd);
        fw.close();
    }

    public static void updateSaveFile() {
        try {
            writeToFile(SAVE_FILE_NAME, TaskList.getAllTaskDetails());
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

}
