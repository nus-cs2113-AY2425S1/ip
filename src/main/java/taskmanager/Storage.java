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
            if (dataFile.exists()) {
                BufferedReader br = new BufferedReader(new FileReader(dataFile));
                String line;

                while ((line = br.readLine()) != null) {
                    String[] parts = line.split("\\|");

                    if (parts.length < 3) continue; // Skip lines that don't have enough data

                    String taskType = parts[0].trim();
                    boolean isDone = parts[1].trim().equals("1");

                    Task task;
                    switch (taskType) {
                        case "T":
                            task = new ToDo(parts[2].trim());
                            break;
                        case "D":
                            task = new Deadline(parts[2].trim(), parts[3].trim());
                            break;
                        case "E":
                            task = new Event(parts[2].trim(), parts[3].trim(), parts[4].trim());
                            break;
                        default:
                            continue; // Skip unknown task types
                    }
                    if (isDone) {
                        task.setDone(true);
                    }
                    tasks.add(task);
                }
                br.close();
            }
        } catch (IOException e) {
            System.out.println("Error loading tasks: " + e.getMessage());
        }
        return tasks;
    }

    // Method to save tasks to the file
    public static void saveTasks(ArrayList<Task> tasks) {
        try {
            PrintWriter writer = new PrintWriter(new FileWriter(FILE_PATH));
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
            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }
}
