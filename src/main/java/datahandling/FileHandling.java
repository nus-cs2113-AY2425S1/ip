
package datahandling;

import task.Task;
import task.Deadline;
import task.Event;
import task.ToDo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileHandling {

    private final String filePath;

    public FileHandling(String filePath) {
        this.filePath = filePath;
    }

    public void saveTasksToFile(List<Task> tasks) {
        try {
            File file = new File(filePath);
            file.getParentFile().mkdirs(); // Ensure parent directories exist
            FileWriter writer = new FileWriter(file);

            for (Task task : tasks) {
                writer.write(formatTaskForSaving(task) + System.lineSeparator());
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error occurred while saving tasks to file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public List<Task> loadTasksFromFile() {
        List<Task> tasks = new ArrayList<>();
        File file = new File(filePath);

        // Ensure the directory exists
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }

        // If the file does not exist, create it and return an empty task list
        if (!file.exists()) {
            try {
                file.createNewFile();
                System.out.println("No saved tasks found. Starting with an empty list.");
                return tasks;
            } catch (IOException e) {
                System.out.println("Error occurred while creating the data file: " + e.getMessage());
                e.printStackTrace();
                return tasks;
            }
        }

        try {
            List<String> lines = Files.readAllLines(Paths.get(filePath));
            for (String line : lines) {
                try {
                    Task task = parseTaskFromFile(line);
                    if (task != null) {
                        tasks.add(task);
                    }
                } catch (Exception e) {
                    System.out.println("Skipping corrupted line: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error occurred while loading tasks from file: " + e.getMessage());
            e.printStackTrace();
        }
        return tasks;
    }

    private String formatTaskForSaving(Task task) {
        String type = "";
        String status = task.isDone ? "1" : "0"; // 1 if task is done, 0 otherwise
        String description = task.description;

        if (task instanceof ToDo) {
            type = "T"; // ToDo task type
            return type + " | " + status + " | " + description;
        } else if (task instanceof Deadline) {
            type = "D"; // Deadline task type
            String by = ((Deadline) task).by; // Assuming 'by' is the deadline time or date
            return type + " | " + status + " | " + description + " | " + by;
        } else if (task instanceof Event) {
            type = "E"; // Event task type
            String from = ((Event) task).from; // Event start time
            String to = ((Event) task).to; // Event end time
            return type + " | " + status + " | " + description + " | " + from + " to " + to;
        }

        return ""; // Default case (shouldn't reach here if all task types are covered)
    }

    private Task parseTaskFromFile(String line) {
        String[] parts = line.split(" \\| ");
        if (parts.length < 3) {
            throw new IllegalArgumentException("Invalid line format");
        }

        String type = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];

        switch (type) {
        case "T":
            ToDo todo = new ToDo(description);
            if (isDone) todo.markAsDone();
            return todo;
        case "D":
            if (parts.length < 4) throw new IllegalArgumentException("Invalid Deadline format");
            Deadline deadline = new Deadline(description, parts[3]);
            if (isDone) deadline.markAsDone();
            return deadline;
        case "E":
            if (parts.length < 5) throw new IllegalArgumentException("Invalid Event format");
            Event event = new Event(description, parts[3], parts[4]);
            if (isDone) event.markAsDone();
            return event;
        default:
            throw new IllegalArgumentException("Unknown task type: " + type);
        }
    }
}