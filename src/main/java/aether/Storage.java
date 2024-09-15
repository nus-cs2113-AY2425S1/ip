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
 * Handles loading and saving tasks to a file.
 */
public class Storage {
    private Path filePath;

    public Storage(String filePath) {
        this.filePath = Paths.get(filePath);
    }

    public Task[] load() throws IOException {
        Task[] tasks = new Task[100];
        int taskCount = 0;

        if (!Files.exists(filePath)) {
            // Create the file and parent directories if they do not exist
            Files.createDirectories(filePath.getParent());
            Files.createFile(filePath);
            return tasks;
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
                tasks[taskCount++] = todo;
                break;
            case "D":
                if (parts.length < 4) {
                    continue;
                }
                String by = parts[3];
                Task deadline = new Deadline(description, by);
                deadline.setDone(isDone);
                tasks[taskCount++] = deadline;
                break;
            case "E":
                if (parts.length < 5) {
                    continue;
                }
                String from = parts[3];
                String to = parts[4];
                Task event = new Event(description, from, to);
                event.setDone(isDone);
                tasks[taskCount++] = event;
                break;
            default:
                // Handle unknown task type
                continue;
            }
        }

        return tasks;
    }

    public void save(Task[] tasks, int taskCount) throws IOException {
        List<String> lines = new ArrayList<>();

        for (int i = 0; i < taskCount; i++) {
            lines.add(tasks[i].toDataString());
        }

        Files.write(filePath, lines, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
    }
}
