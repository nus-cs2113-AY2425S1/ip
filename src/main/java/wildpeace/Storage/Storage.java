package wildpeace.Storage;

import wildpeace.task.Task;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class Storage {
    private static final String FILE_PATH = "tasks.json";
    private Gson gson;

    public Storage() {
        this.gson = new Gson();
    }

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

    public void save(ArrayList<Task> tasks) {
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            gson.toJson(tasks, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
