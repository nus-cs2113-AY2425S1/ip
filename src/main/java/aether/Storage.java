package aether;

import aether.task.Task;
import aether.task.Todo;
import aether.task.Deadline;
import aether.task.Event;

import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles loading and saving tasks to a file using ArrayList.
 */
public class Storage {
    private Path filePath;

    public Storage(String filePath) {
        this.filePath = Paths.get(filePath);
    }

    // Loads tasks from the file and returns them as an ArrayList<Task>
    public ArrayList<Task> load() throws IOException {
        ArrayList<Task> tasks = new ArrayList<>();

        if (!Files.exists(filePath)) {
            // Create the file and parent directories if they do not exist
            Files.createDirectories(filePath.getParent());
            Files.createFile(filePath);
            return tasks; // Return an empty task list
        }

        List<String> lines = Files.readAllLines(filePath);

        for (String line : lines) {
            String[] parts = line.split(" \\| ");
            if (parts.length < 3) {
                // Handle corrupted line
                continue;
            }
            String taskType = parts[0];
            boolean isDone = parts[1].equals("1");
            String description = parts[2];

            switch (taskType) {
            case "T":
                Task todo = new Todo(description);
                todo.setDone(isDone);
                tasks.add(todo);
                break;
            case "D":
                if (parts.length < 4) {
                    continue;
                }
                String by = parts[3];
                Task deadline = new Deadline(description, by);
                deadline.setDone(isDone);
                tasks.add(deadline);
                break;
            case "E":
                if (parts.length < 5) {
                    continue;
                }
                String from = parts[3];
                String to = parts[4];
                Task event = new Event(description, from, to);
                event.setDone(isDone);
                tasks.add(event);
                break;
            default:
                // Handle unknown task type
                continue;
            }
        }

        return tasks;
    }

    // Saves the tasks to a file from the ArrayList<Task>
    public void save(ArrayList<Task> tasks) throws IOException {
        List<String> lines = new ArrayList<>();

        for (Task task : tasks) {
            lines.add(task.toDataString());
        }

        Files.write(filePath, lines, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
    }
}
