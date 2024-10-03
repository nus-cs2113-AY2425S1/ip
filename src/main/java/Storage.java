import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    // Method to load tasks from file
    public List<Task> load() throws FlashException {
        List<Task> tasks = new ArrayList<>();
        File file = new File(filePath);
        if (!file.exists()) {
            // Create the file and the directory if they do not exist
            try {
                file.getParentFile().mkdirs();
                file.createNewFile();
            } catch (IOException e) {
                throw new FlashException("Failed to create file: " + e.getMessage());
            }
        } else {
            try (Scanner sc = new Scanner(file)) {
                while (sc.hasNextLine()) {
                    String line = sc.nextLine();
                    String[] parts = line.split(" \\| ");
                    String taskType = parts[0];
                    boolean isDone = parts[1].equals("1");
                    String description = parts[2];

                    switch (taskType) {
                        case "T":
                            Task todo = new ToDo(description);
                            if (isDone) todo.markDone();
                            tasks.add(todo);
                            break;
                        case "D":
                            String by = parts[3];
                            Task deadline = new Deadline(description, by);
                            if (isDone) deadline.markDone();
                            tasks.add(deadline);
                            break;
                        case "E":
                            String from = parts[3];
                            String to = parts[4];
                            Task event = new Event(description, from, to);
                            if (isDone) event.markDone();
                            tasks.add(event);
                            break;
                        default:
                            throw new FlashException("Corrupted file: Unknown task type.");
                    }
                }
            } catch (Exception e) {
                throw new FlashException("Failed to load tasks: " + e.getMessage());
            }
        }
        return tasks;
    }

    // Method to save tasks to file
    public void save(List<Task> tasks) throws FlashException {
        try (FileWriter fw = new FileWriter(filePath)) {
            for (Task task : tasks) {
                fw.write(task.toFileFormat() + System.lineSeparator());
            }
        } catch (IOException e) {
            throw new FlashException("Failed to save tasks: " + e.getMessage());
        }
    }
}
