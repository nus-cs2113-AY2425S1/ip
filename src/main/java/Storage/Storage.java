package Storage;

import constants.Utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {

    /**
     * Print previously stored tasks from the txt file.
     * Create new txt file if file is not found at the file path.
     */
    public static void loadExistingData() {
        try {
            printFileContents();
        } catch (FileNotFoundException e) {
            createFile();
        }
    }

    //Print previously stored tasks from the txt file
    private static void printFileContents() throws FileNotFoundException {
        File f = new File(Utils.FILE_PATH);
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            System.out.println(s.nextLine());
        }
    }

    /**
     * Create txt file if file not found at the file path
     */
    public static void createFile() {
        try {
            File file = new File(Utils.FILE_PATH);

            // Check if the parent directory exists
            if (!file.getParentFile().exists()) {
                // Create the directory if it doesn't exist
                file.getParentFile().mkdirs();
            }

            //Create file is the file doesn't exist at the particular directory
            if (!file.exists()) {
                file.createNewFile();
                System.out.println("File created: " + file.getAbsolutePath());
            }

        } catch (IOException e) {
            System.out.println("An error occurred while creating the file: " + e.getMessage());
        }
    }

    /**
     * Saves the new task into a txt file. Input is generated from the createDeadlineTxt(),
     * createEventTxt(),createTodoTxt() methods.These methods are found in the respective
     * deadline, event and todo classes respectively.
     *
     * @param input String of text that is going to be stored in the txt file
     */
    public static void saveNewData(String input) {
        try {
            appendToFile(input);
        } catch (IOException e) {
            System.out.println("Something went wrong while saving the data: " + e.getMessage());
        }
    }

    //Writes the input from saveNewData() method into the txt file
    private static void appendToFile(String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(Utils.FILE_PATH, true);
        fw.write(System.lineSeparator() + textToAppend);
        fw.close();
    }
}
