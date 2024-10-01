import constants.Utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    private static void loadExistingData() {
        try {
            printFileContents();
        } catch (FileNotFoundException e) {
            createFile();
        }
    }
    private static void printFileContents() throws FileNotFoundException {
        File f = new File(Utils.FILE_PATH);
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            System.out.println(s.nextLine());
        }
    }
    public static void createFile(){
        try {
            File file = new File(Utils.FILE_PATH);

            // Check if the parent directory exists
            if (!file.getParentFile().exists()) {
                // Create the directory if it doesn't exist
                file.getParentFile().mkdirs();
            }

            if (!file.exists()) {
                file.createNewFile();
                System.out.println("File created: " + file.getAbsolutePath());
            }

        } catch (IOException e) {
            System.out.println("An error occurred while creating the file: " + e.getMessage());
        }
    }
}
