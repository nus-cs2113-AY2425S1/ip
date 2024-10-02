import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles loading and saving tasks to a file.
 */
public class Storage {

    private final String filePath;

    /**
     * Constructs a Storage object with the specified file path.
     *
     * @param filePath The file path where tasks are stored.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the specified file and returns a list of tasks.
     *
     * @return A list of tasks loaded from the file.
     * @throws BebeException if there is an error reading from the file.
     */
    public List<Task> load() throws BebeException {
        List<Task> tasks = new ArrayList<>();
        try {
            if (!Files.exists(Paths.get(filePath))) {
                return tasks;  // Return empty list if no file exists
            }

            Scanner scanner = new Scanner(new File(filePath));
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                String[] parts = line.split(" \\| ");

                switch (parts[0]) {
                    case "T":
                        Task todo = new Todo(parts[2]);
                        if (parts[1].equals("1")) todo.markAsDone();
                        tasks.add(todo);
                        break;
                    case "D":
                        Task deadline = new Deadline(parts[2], parts[3]);
                        if (parts[1].equals("1")) deadline.markAsDone();
                        tasks.add(deadline);
                        break;
                    case "E":
                        String[] timeParts = parts[3].split(" to ");
                        Task event = new Event(parts[2], timeParts[0], timeParts[1]);
                        if (parts[1].equals("1")) event.markAsDone();
                        tasks.add(event);
                        break;
                }
            }
            scanner.close();
        } catch (IOException e) {
            throw new BebeException("Error loading file.");
        }
        return tasks;
    }

    /**
     * Saves the current list of tasks to the file.
     *
     * @param tasks The list of tasks to save.
     * @throws BebeException if there is an error writing to the file.
     */
    public void save(List<Task> tasks) throws BebeException {
        try {
            File directory = new File("./data");
            if (!directory.exists()) {
                directory.mkdir();
            }

            FileWriter writer = new FileWriter(filePath);
            for (Task task : tasks) {
                writer.write(task.toFileFormat() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            throw new BebeException("Error saving tasks.");
        }
    }
}
