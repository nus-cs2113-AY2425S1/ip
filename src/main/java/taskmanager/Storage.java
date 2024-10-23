package taskmanager;

import tasks.Task;
import tasks.Event;
import tasks.Deadline;
import tasks.ToDo;

import java.io.*;
import java.util.ArrayList;

/**
 * The {@code Storage} class is responsible for handling the persistence of tasks to and from a file.
 * It supports loading tasks from a file and saving the current list of tasks back to the file.
 * The file stores different types of tasks (e.g., ToDos, Deadlines, Events) in a specified format.
 */
public class Storage {

    private static final String FILE_PATH = "./data/terri.txt"; // Path to the storage file

    /**
     * Loads tasks from a file into an {@code ArrayList<Task>}.
     * The tasks are parsed from a file where each line represents a task in a specific format.
     * If the file or data directory does not exist, they are created.
     *
     * @return an {@code ArrayList<Task>} containing all tasks loaded from the file.
     */
    public static ArrayList<Task> loadTasks() {
        ArrayList<Task> tasks = new ArrayList<>();
        File dataFile = new File(FILE_PATH);

        dataFile.getParentFile().mkdirs(); // Ensure parent directories exist

        try {
            // If the file doesn't exist, create it and return an empty task list
            if (!dataFile.exists()) {
                dataFile.createNewFile();
                return tasks;
            }

            // Reading tasks from the file
            try (BufferedReader br = new BufferedReader(new FileReader(dataFile))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] parts = line.split("\\|");

                    // Skip lines that do not contain sufficient task data
                    if (parts.length < 3) continue;

                    String type = parts[0].trim();
                    boolean isDone = parts[1].trim().equals("1");
                    String description = parts[2].trim();
                    Task task;

                    // Parse tasks based on their type
                    switch (type) {
                        case "T": // ToDo task
                            ToDo todo = new ToDo(description);
                            if (isDone) todo.setDone(true);
                            tasks.add(todo);
                            break;
                        case "D": // Deadline task
                            if (parts.length < 4) continue;

                            String deadline = parts[3].trim();
                            Deadline deadlineTask = new Deadline(description, deadline);

                            if (isDone) deadlineTask.setDone(true);

                            tasks.add(deadlineTask);

                            break;
                        case "E": // Event task
                            if (parts.length < 5) continue;
                            String start = parts[3].trim();
                            String end = parts[4].trim();
                            Event event = new Event(description, start, end);
                            if (isDone) event.setDone(true);
                            tasks.add(event);
                            break;
                        default:
                            continue; // Skip unknown task types
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading tasks: " + e.getMessage());
        }

        return tasks;
    }

    /**
     * Saves the current list of tasks to a file. Each task is written in a specific format
     * based on its type (e.g., ToDo, Deadline, Event). The format is consistent with the one used for loading.
     *
     * @param tasks the list of tasks to save.
     */
    public static void saveTasks(ArrayList<Task> tasks) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_PATH))) {
            for (Task task : tasks) {
                String taskType = task.getTypeIcon().trim();
                String isDone = task.isDone() ? "1" : "0";
                String taskDetails;

                // Format task details based on its specific type
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
