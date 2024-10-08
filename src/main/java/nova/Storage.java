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
 */
public class Storage {

    /** Path to the file where task data is stored. */
    public static final String PATHNAME = "./data/nova.txt";

    /**
     * Appends a string representing a task to the storage file.
     *
     * @param textToAppend The string representing the task to be appended.
     * @throws IOException If an I/O error occurs during writing to the file.
     */
    public static void appendToStorage(String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(PATHNAME, true);
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
     */
    public static void readFromStorage() {
        File f = new File(PATHNAME);
        try {
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                String[] line = splitInput(s);
                if (line == null) {
                    continue;
                }
                handleLine(line);
            }
        } catch (FileNotFoundException e) {
            Ui.displayStorageError();
        }
    }

    /**
     * Processes a single line of input and loads the corresponding task into the task list.
     *
     * @param line A string array representing the parsed input line.
     */
    private static void handleLine(String[] line) {
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

    /**
     * Reads the next line of input from the provided {@code Scanner}, and splits the line by the delimiter " | ".
     *
     * @param s The {@code Scanner} used to read user input.
     * @return A {@code String[]} containing the split elements of the input line, or null if the input is empty.
     */
    private static String[] splitInput(Scanner s) {
        String nextLine = s.nextLine().trim();
        if (nextLine.isEmpty()) {
            return null;
        }

        return nextLine.split(" \\| ");
    }

    /**
     * Creates the storage file if it does not exist.
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
            Ui.displayStorageError();
        }
    }
}
