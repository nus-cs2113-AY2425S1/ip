package lovespiritual;

import lovespiritual.exception.lovespiritualException;
import lovespiritual.task.Task;

import java.io.*;
import java.util.ArrayList;

public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

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
