package nova;

import nova.task.Deadline;
import nova.task.Event;
import nova.task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {

    public static final String PATHNAME = "./data/nova.txt";

    public static void appendToStorage(String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(PATHNAME, true); // create a FileWriter in append mode
        fw.write(textToAppend + System.lineSeparator());
        fw.close();
    }

    public static void updateStorage(String updatedInfo) throws IOException {
        FileWriter fw = new FileWriter(PATHNAME); // create a FileWriter in append mode
        fw.write(updatedInfo);
        fw.close();
    }

    public static void readFromStorage() {
        File f = new File(PATHNAME);
        try {
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                String nextLine = s.nextLine().trim();
                if (nextLine.isEmpty()) {
                    continue; // Skip empty lines
                }

                String[] line = nextLine.split(" \\| ");
                switch (line[0]) {

                case "T":
                    TaskManager.loadTask(new Todo(line[1], line[2]));
                    break;

                case "D":
                    TaskManager.loadTask(new Deadline(line[1], line[2], line[3]));
                    break;

                case "E":
                    TaskManager.loadTask(new Event(line[1], line[2], line[3], line[4]));
                    break;

                default:
                    MessageDisplay.displayStorageError();
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            MessageDisplay.displayStorageError();
        }
    }

    public static void createStorage() {
        // Define the file path
        File file = new File(PATHNAME);

        try {
            // Check if the parent directory exists; if not, create it
            File parentDir = file.getParentFile();
            if (parentDir != null && !parentDir.exists()) {
                parentDir.mkdirs();
            }

            if (file.exists()) {
                readFromStorage();
            } else {
                file.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
}
