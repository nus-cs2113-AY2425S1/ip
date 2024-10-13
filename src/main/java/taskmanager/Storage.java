package taskmanager;

import tasks.Task;
import tasks.Event;
import tasks.Deadline;
import tasks.ToDo;

import java.io.*;
import java.util.ArrayList;

public class Storage {
    private static final String FILE_PATH = "./data/duke.txt"; // Hard-coded file path

    // Method to load tasks from the file
    public static ArrayList<Task> loadTasks() {
        ArrayList<Task> tasks = new ArrayList<>();
        File dataFile = new File(FILE_PATH);

        // Create the data directory if it doesn't exist
        dataFile.getParentFile().mkdirs();

        try {
            // Create the data file if it doesn't exist
            if (!dataFile.exists()) {
                dataFile.createNewFile();
                return tasks; // Return empty task list if file doesn't exist
            }

            // Use try-with-resources to automatically close the BufferedReader
            try (BufferedReader br = new BufferedReader(new FileReader(dataFile))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] parts = line.split("\\|");

                    if (parts.length < 3) continue; // Skip lines that don't have enough data

                    String type = parts[0].trim(); // Get task type
                    boolean isDone = parts[1].trim().equals("1"); // Get task done status
                    String description = parts[2].trim(); // Get task description

                    Task task;
                    switch (type) {
                        case "T":
                            ToDo todo = new ToDo(description);
                            if (isDone) todo.setDone(true);
                            tasks.add(todo); // Add to tasks list
                            break;

                        case "D":
                            if (parts.length < 4) continue; // Ensure there is a deadline part
                            String deadline = parts[3].trim();
                            Deadline deadlineTask = new Deadline(description, deadline);
                            if (isDone) deadlineTask.setDone(true);
                            tasks.add(deadlineTask); // Add to tasks list
                            break;

                        case "E":
                            if (parts.length < 5) continue; // Ensure there are start and end parts
                            String start = parts[3].trim();
                            String end = parts[4].trim();
                            Event event = new Event(description, start, end);
                            if (isDone) event.setDone(true);
                            tasks.add(event); // Add to tasks list
                            break;

                        default:
                            continue; // Skip unknown task types
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading tasks: " + e.getMessage());
        }
        return tasks; // Return the loaded tasks
    }

    // Method to save tasks to the file
    public static void saveTasks(ArrayList<Task> tasks) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_PATH))) { // Use try-with-resources
            for (Task task : tasks) {
                String taskType = task.getTypeIcon().trim();
                String isDone = task.isDone() ? "1" : "0";
                String taskDetails;

                if (task instanceof ToDo) {
                    taskDetails = String.format("%s | %s | %s", taskType, isDone, task.getTaskName());
                } else if (task instanceof Deadline) {
                    taskDetails = String.format("%s | %s | %s | %s", taskType, isDone, task.getTaskName(),
                            task.getBy());
                } else if (task instanceof Event) {
                    taskDetails = String.format("%s | %s | %s | %s | %s", taskType, isDone, task.getTaskName(),
                            task.getEventStart(), task.getEventEnd());
                } else {
                    continue; // Skip unknown task types
                }

                writer.println(taskDetails);
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }
}
