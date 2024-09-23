package nova;

import nova.task.Deadline;
import nova.task.Event;
import nova.task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Manages the reading and writing of task data to and from storage.
 * The storage file is located at {@code PATHNAME}.
 *
 * Provides functionality for appending tasks, updating storage, reading from storage,
 * and creating the necessary file structure.
 */
public class Storage {

    /**
     * Path to the file where task data is stored.
     */
    public static final String PATHNAME = "./data/nova.txt";

    /**
     * Appends a string representing a task to the storage file.
     * The task is written in a new line.
     *
     * @param textToAppend The string representing the task to be appended.
     * @throws IOException If an I/O error occurs during writing to the file.
     */
    public static void appendToStorage(String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(PATHNAME, true); // create a FileWriter in append mode
        fw.write(textToAppend + System.lineSeparator());
        fw.close();
    }

    /**
     * Rewrites the storage file with updated task information.
     *
     * @param updatedInfo The string representing the updated task information.
     * @throws IOException If an I/O error occurs during writing to the file.
     */
    public static void updateStorage(String updatedInfo) throws IOException {
        FileWriter fw = new FileWriter(PATHNAME); // overwrite the file
        fw.write(updatedInfo);
        fw.close();
    }

    /**
     * Reads task data from the storage file and loads tasks into the task list.
     * Each task is parsed based on its type and loaded into the {@code TaskList}.
     *
     * Tasks are identified by their type ('T' for Todo, 'D' for Deadline, 'E' for Event).
     * Displays an error if the storage file is not found.
     */
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

                case Todo.TYPE:
                    TaskList.loadTask(new Todo(line[1], line[2]));
                    break;

                case Deadline.TYPE:
                    TaskList.loadTask(new Deadline(line[1], line[2], line[3]));
                    break;

                case Event.TYPE:
                    TaskList.loadTask(new Event(line[1], line[2], line[3], line[4]));
                    break;

                default:
                    Ui.displayStorageError();
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            Ui.displayStorageError();
        }
    }

    /**
     * Creates the storage file if it does not exist.
     * If the file exists, it reads and loads the task data into the task list.
     * If the parent directory does not exist, it creates the directory structure.
     *
     * @throws IOException If an error occurs during file creation or writing.
     */
    public static void createStorage() {
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
