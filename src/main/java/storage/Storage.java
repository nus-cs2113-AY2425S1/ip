package storage;

import exception.MondayException;
import model.Task;
import model.Todo;
import model.Deadline;
import model.Event;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Handles loading and saving tasks to and from a file.
 */
public class Storage {
    private String filePath;

    /**
     * Constructs a Storage object with the given file path.
     *
     * @param filePath the path of the file to save/load tasks
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the file and returns them as an ArrayList.
     *
     * @return a list of tasks loaded from the file
     * @throws MondayException if an error occurs while loading tasks
     */
    public ArrayList<Task> load() throws MondayException {
        File file = new File(filePath);
        System.out.println("Loading tasks from: " + file.getAbsolutePath()); // Debugging line

        ArrayList<Task> tasks = new ArrayList<>();

        if (!file.exists()) {
            System.out.println("File does not exist: " + filePath);
            return tasks; // Return empty tasks if file does not exist
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                Task task = parseTask(line); // Parse the line to create a Task object
                tasks.add(task);
            }
        } catch (IOException e) {
            throw new MondayException(" Error loading tasks from file: " + e.getMessage());
        }
        return tasks;
    }

    /**
     * Saves the given list of tasks to the file.
     *
     * @param tasks the list of tasks to be saved
     */
    public void save(ArrayList<Task> tasks) {
        File file = new File(filePath);

        // Ensure parent directories exist, create them if they don't
        File parentDir = file.getParentFile();
        if (parentDir != null && !parentDir.exists()) {
            boolean dirCreated = parentDir.mkdirs();
            if (dirCreated) {
                System.out.println("Created missing directory: " + parentDir.getAbsolutePath());
            }
        }

        try (PrintWriter pw = new PrintWriter(new FileWriter(file))) {
            for (Task task : tasks) {
                if (task instanceof Todo) {
                    Todo todo = (Todo) task;
                    pw.println(todo.saveFormat());
                } else if (task instanceof Deadline) {
                    Deadline deadline = (Deadline) task;
                    pw.println(deadline.saveFormat());
                } else if (task instanceof Event) {
                    Event event = (Event) task;
                    pw.println(event.saveFormat());
                }
            }
        } catch (IOException e) {
            // Handle exception, e.g., log the error or notify the user
            System.out.println("    Error saving tasks to file: " + e.getMessage());
        }
    }

    /**
     * Parses a task from a line of text.
     *
     * @param line the line containing task data
     * @return the Task object parsed from the line
     * @throws MondayException if the line format is invalid
     */
    private Task parseTask(String line) throws MondayException {
        String[] parts = line.split(" \\| "); // Assuming fields are separated by " | "

        // Check if there are enough parts for different task types
        if (parts.length < 3) {
            throw new MondayException(" Not enough data to parse task: " + line);
        }

        String taskType = parts[0].trim(); // T, D, E etc.
        boolean isDone = parts[1].trim().equals("1"); // Assuming 1 for done, 0 for not done
        String description = parts[2].trim(); // Task description

        switch (taskType) {
        case "T":
            return new Todo(description, isDone);
        case "D":
            if (parts.length < 4) {
                throw new MondayException(" Not enough data to parse Deadline: " + line);
            }
            String deadlineDate = parts[3].trim(); // e.g., the date part for Deadline
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            LocalDateTime by = LocalDateTime.parse(deadlineDate, formatter); // Convert String to LocalDateTime
            return new Deadline(description, isDone, by);
        case "E":
            if (parts.length < 5) {
                throw new MondayException(" Not enough data to parse Event: " + line);
            }
            String from = parts[3].trim(); // Start
            String to = parts[4].trim(); // End
            return new Event(description, isDone, from, to);
        default:
            throw new MondayException(" Unknown task format: " + line);
        }
    }
}
