package lovespiritual;

import lovespiritual.task.Task;

import java.io.*;
import java.util.ArrayList;

/**
 * Handles reading and writing task data to a file on the local disk.
 */
public class Storage {
    private final String filePath;

    /**
     * Constructs a new object for saving and loading tasks from the specified file path.
     *
     * @param filePath The path to the file where tasks are stored.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Saves the list of tasks to the file.
     *
     * @param tasks List of tasks to be saved.
     */
    public void saveTasks(ArrayList<Task> tasks) {
        try {
            File file = new File(filePath);
            file.getParentFile().mkdirs();
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
            for (Task task : tasks) {
                writer.write(TaskList.savedFormat(task));
                writer.newLine();
            }
            writer.close();
        } catch (Exception e) {
            UI.printError("Error saving tasks (×_×;): " + e.getMessage());
        }
    }

    /**
     * Loads tasks from the file into the list provided.
     *
     * @param tasks List of tasks to load data into.
     */
    public void loadTasks(ArrayList<Task> tasks) {
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                return;
            }
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = reader.readLine()) != null) {
                tasks.add(TaskList.extractTasks(line));
            }
            reader.close();
        } catch (Exception e) {
            UI.printError("Error loading tasks (×_×;): " + e.getMessage());
        }
    }

}
