package bot;

import task.Task;
import task.Todo;
import task.Event;
import task.Deadline;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles loading and saving tasks to a file.
 */
public class Storage {
    private final String filePath;

    /**
     * Constructor for Storage class.
     *
     * @param filePath The file path for saving and loading tasks.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from a file.
     *
     * @return A list of tasks loaded from the file.
     * @throws IOException If an I/O error occurs.
     */
    public ArrayList<Task> load() throws IOException {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);
        if (!file.exists()) {
            file.getParentFile().mkdirs();
        } else {
            try (Scanner s = new Scanner(file)) {
                while (s.hasNext()) {
                    String line = s.nextLine();
                    String[] parts = line.split(" \\| ");
                    Task task = switch (parts[0]) {
                        case "T" -> new Todo(parts[2]);
                        case "D" -> new Deadline(parts[2], parts[3]);
                        case "E" -> new Event(parts[2], parts[3], parts[4]);
                        default -> null;
                    };

                    if (task != null) {
                        if (parts[1].equals("1")) {
                            task.markAsDone();
                        }
                        tasks.add(task);
                    }
                }
            }
        }
        return tasks;
    }

    /**
     * Saves a list of tasks to a file.
     *
     * @param tasks The list of tasks to save.
     * @throws IOException If an I/O error occurs.
     */
    public void save(ArrayList<Task> tasks) throws IOException {
        try (FileWriter fw = new FileWriter(filePath)) {
            for (Task task : tasks) {
                fw.write(taskToFileString(task) + System.lineSeparator());
            }
        }
    }

    /**
     * Converts a task to a string format suitable for saving to a file.
     *
     * @param task The task to convert.
     * @return A string representation of the task for file storage.
     */
    private String taskToFileString(Task task) {
        StringBuilder sb = new StringBuilder();
        if (task instanceof Todo) {
            sb.append("T");
        } else if (task instanceof Deadline deadline) {
            sb.append("D");
            sb.append(" | ").append(deadline.getDueDate());
        } else if (task instanceof Event event) {
            sb.append("E");
            sb.append(" | ").append(event.getStartTime());
            sb.append(" | ").append(event.getEndTime());
        }
        sb.insert(1, " | " + (task.isDone() ? "1" : "0") + " | " + task.getDescription());
        return sb.toString();
    }
}