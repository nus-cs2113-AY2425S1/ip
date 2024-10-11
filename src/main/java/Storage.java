import java.io.*;
import java.util.ArrayList;

/**
 * Handles the storage of tasks for the KBot application.
 * This class provides functionality to save tasks to a specified file.
 */
public class Storage {
    private static final String FILE_PATH = "./data/KBot.txt";

    /**
     * Saves the provided list of tasks to a file.
     *
     * @param tasks The list of tasks to be saved.
     */
    public void saveTasksToFile(ArrayList<Task> tasks) {
        try {
            File file = new File(FILE_PATH);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs(); // Create directories if they do not exist
            }
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            for (Task task : tasks) {
                writer.write(task.toFileString());
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error occurred while saving tasks to file: " + e.getMessage());
        }
    }
}
