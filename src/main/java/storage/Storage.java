package storage;

import exception.MondayException;
import model.Task;
import model.Todo;
import model.Deadline;
import model.Event;

import java.io.*;
import java.util.ArrayList;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() throws MondayException {
        File file = new File(filePath);
        System.out.println("Loading tasks from: " + file.getAbsolutePath()); // Debugging line

        ArrayList<Task> tasks = new ArrayList<>();

        // Check if the file exists
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
            throw new MondayException("    Error loading tasks from file: " + e.getMessage());
        }
        return tasks;
    }


    public void save(ArrayList<Task> tasks) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(filePath))) {
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

    private Task parseTask(String line) throws MondayException {
        String[] parts = line.split(" \\| "); // Assuming fields are separated by " | "

        // Check if there are enough parts for different task types
        if (parts.length < 3) {
            throw new MondayException("Not enough data to parse task: " + line);
        }

        String taskType = parts[0].trim(); // T, D, E etc.
        boolean isDone = parts[1].trim().equals("1"); // Assuming 1 for done, 0 for not done
        String description = parts[2].trim(); // Task description

        switch (taskType) {
        case "T":
            return new Todo(description, isDone);
        case "D":
            if (parts.length < 4) {
                throw new MondayException("Not enough data to parse Deadline: " + line);
            }
            String deadlineDate = parts[3].trim(); // e.g., the date part for Deadline
            return new Deadline(description, isDone, deadlineDate);
        case "E":
            if (parts.length < 5) {
                throw new MondayException("Not enough data to parse Event: " + line);
            }
            String from = parts[3].trim(); // Start time
            String to = parts[4].trim(); // End time
            return new Event(description, isDone, from, to);
        default:
            throw new MondayException("Unknown task format: " + line);
        }
    }




}
