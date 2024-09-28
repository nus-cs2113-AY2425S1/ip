import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Storage {

    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public void saveTasksToFile(ArrayList<Task> tasks) {
        try {
            File directory = new File("data");
            if (!directory.exists()) {
                boolean dirCreated = directory.mkdirs();
                if (!dirCreated) {
                    System.out.println("An error occurred: Could not create the directory.");
                    return;
                }
            }

            try (FileWriter fw = new FileWriter(filePath)) {
                for (Task task : tasks) {
                    fw.write(task.toString().trim() + System.lineSeparator());
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while saving tasks to file.");
        }
    }

    public ArrayList<Task> loadTasksFromFile() {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                return tasks;
            }

            BufferedReader br = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = br.readLine()) != null) {
                String trimmedLine = line.trim();
                Task task = parseTaskFromString(trimmedLine);
                if (task != null) {
                    tasks.add(task);
                }
            }
            br.close();
        } catch (IOException e) {
            System.out.println("An error occurred while loading tasks from file.");
        }
        return tasks;
    }

    private Task parseTaskFromString(String line) {
        line = line.trim();

        if (line.startsWith("[T]")) {
            return new Todo(line.substring(6).trim());
        } else if (line.startsWith("[D]")) {
            String[] parts = line.split("\\(by: ");
            String description = parts[0].substring(6).trim();
            String by = parts[1].replace(")", "");
            return new Deadline(description, by);
        } else if (line.startsWith("[E]")) {
            String[] parts = line.split("\\(from: | to: |\\)");
            String description = parts[0].substring(6).trim();
            String from = parts[1].trim();
            String to = parts[2].trim();
            return new Event(description, from, to);
        }
        return null;
    }

}
