package wildpeace.Storage;

import wildpeace.task.Task;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Handles the loading and saving of tasks to and from a file using JSON format.
 * The tasks are saved to a file named "tasks.json" and can be loaded from it when needed.
 */
public class Storage {
    private static final String FILE_PATH = "tasks.json";
    private Gson gson;

    /**
     * Constructs a Storage object and initializes the Gson instance for JSON processing.
     */
    public Storage() {
        this.gson = new Gson();
    }

    /**
     * Loads tasks from the JSON file "tasks.json".
     * If the file is not found or cannot be read, it returns an empty task list.
     *
     * @return An ArrayList of tasks loaded from the file, or an empty list if no tasks are found.
     */
    public ArrayList<Task> load() {
        try (FileReader reader = new FileReader(FILE_PATH)) {
            Type listType = new TypeToken<ArrayList<Task>>() {}.getType();
            ArrayList<Task> tasks = gson.fromJson(reader, listType);
            if (tasks != null) {
                return tasks;
            }
        } catch (IOException e) {
            System.out.println("No existing tasks found. Starting fresh.");
        }
        return new ArrayList<>();  // Return an empty list if no tasks are found
    }

    /**
     * Saves the given list of tasks to the JSON file "tasks.json".
     * If the file cannot be written to, an IOException will be printed.
     *
     * @param tasks The ArrayList of tasks to be saved.
     */
    public void save(ArrayList<Task> tasks) {
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            gson.toJson(tasks, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
