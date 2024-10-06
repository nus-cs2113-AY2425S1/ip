import sleepy.Task.Deadline;
import sleepy.Task.Event;
import sleepy.Task.Task;
import sleepy.Task.Todo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.time.format.DateTimeFormatter;

/**
 * The Storage class handles the saving and loading of tasks to and from a file.
 * It ensures persistence by storing tasks in a local text file and recreating them upon program initialization.
 */
public class Storage {
    private static final String DIRECTORY_NAME = "data";
    private static final String FILE_PATH = "sleepy.txt";

    /**
     * Initializes the Storage class by creating the necessary file structure if it does not exist.
     * Ensures that the directory and file for storing tasks are created.
     */
    //creates file if is empty
    public Storage() {
        String directoryPath = Paths.get("").toAbsolutePath() + File.separator + DIRECTORY_NAME;
        String filePath = directoryPath + File.separator + FILE_PATH;

        File dir = new File(directoryPath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        File file = new File(filePath);
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("Error initializing storage: " + e.getMessage());
        }
    }

    /**
     * Saves the list of tasks to the file.
     *
     * @param tasks The list of tasks to be saved.
     */
    public static void saveTasks(ArrayList<Task> tasks) {
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            for (Task task : tasks) {
                writer.write(task.toFileFormat() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks to file: " + e.getMessage());
        }
    }

    /**
     * Loads the tasks from the file into an ArrayList of tasks.
     *
     * @return An ArrayList of tasks loaded from the file.
     */
    public static ArrayList<Task> loadTasks() {
        ArrayList<Task> tasks = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] taskInfo = line.split("\\|");
                Task task;

                switch (taskInfo[0]) {
                    case "T":
                        task = new Todo(taskInfo[2]);
                        break;
                    case "D":
                        LocalDateTime by = LocalDateTime.parse(taskInfo[3], formatter);
                        task = new Deadline(taskInfo[2], by);
                        break;
                    case "E":
                        task = new Event(taskInfo[2], taskInfo[3], taskInfo[4]);
                        break;
                    default:
                        throw new IOException("Corrupted task format.");
                }

                if (taskInfo[1].equals("1")) {
                    task.markAsDone();
                }

                tasks.add(task);
            }
        } catch (IOException e) {
            System.out.println("Error loading tasks: " + e.getMessage());
        }
        return tasks;
    }
}
