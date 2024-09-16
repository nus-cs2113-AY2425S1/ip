package melchizedek;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class FileHandling {

    public static final String FILE_DIRECTORY = "./data";
    public static final String FILE_PATH = "./data/Melchizedek.txt";

    public static void loadFile() {
        File dir = new File(FILE_DIRECTORY);

        if (!dir.exists()) {
            if (!dir.mkdirs()) {
                System.err.println("Error. No directory could be created.");
            }
        }
        try {
            File file = new File(FILE_PATH);
            if (!file.createNewFile()) {
                loadDataFromFile(FILE_PATH);
            }
        } catch (IOException e) {
            System.err.println("Error. No file could be created or found.");
        }
    }

    public static void loadDataFromFile(String filePath) throws FileNotFoundException {
        File file = new File(filePath);
        Scanner s = new Scanner(file);
        while (s.hasNext()) {

        }
    }
    

}
