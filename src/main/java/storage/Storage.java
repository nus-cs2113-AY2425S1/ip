package storage;

import task.Task;
import task.Event;
import task.ToDo;
import task.Deadline;
import exception.LiaException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedWriter;

/**
 * Handles loading and saving tasks from/to a file.
 */
public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() throws LiaException {
        ArrayList<Task> tasks = new ArrayList<>();
        Path path = Paths.get(filePath);

        try {
            if (!Files.exists(path)) {
                // Create directories and file if it does not exist
                Files.createDirectories(path.getParent());
                Files.createFile(path);
            } else {
                List<String> lines = Files.readAllLines(path);
                for (String line : lines) {
                    try {
                        String[] data = line.split(" \\| ");
                        switch (data[0]) {
                        case "T":
                            if (data.length != 3) throw new LiaException("Invalid ToDo format.");
                            ToDo todo = new ToDo(data[2]);
                            if (data[1].equals("1")) todo.markAsDone();
                            tasks.add(todo);
                            break;
                        case "D":
                            if (data.length != 4) throw new LiaException("Invalid Deadline format.");
                            Deadline deadline = new Deadline(data[2], data[3]);
                            if (data[1].equals("1")) deadline.markAsDone();
                            tasks.add(deadline);
                            break;
                        case "E":
                            if (data.length != 5) throw new LiaException("Invalid Event format.");
                            Event event = new Event(data[2], data[3], data[4]);
                            if (data[1].equals("1")) event.markAsDone();
                            tasks.add(event);
                            break;
                        default:
                            System.out.println("Warning: Unrecognized task type in file. Skipping line.");
                        }
                    } catch (Exception e) {
                        System.out.println("Warning: Corrupted data in file. Skipping line: " + line);
                    }
                }
            }
        } catch (Exception e) {
            throw new LiaException("Error loading tasks from file.");
        }
        return tasks;
    }

    public void save(ArrayList<Task> tasks) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Task task : tasks) {
                writer.write(task.toFileFormat());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks to file: " + e.getMessage());
        }
    }
}
