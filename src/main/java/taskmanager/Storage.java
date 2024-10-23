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
    public static void loadTasks() {
        File dataFile = new File(FILE_PATH);

        dataFile.getParentFile().mkdirs(); // Ensure parent directories exist

        try {
            // If the file doesn't exist, create it and return an empty task list
            if (!dataFile.exists()) {
                dataFile.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("Error loading tasks: " + e.getMessage());
        }

        // Reading tasks from the file
        try (BufferedReader br = new BufferedReader(new FileReader(dataFile))) {
            String line;

            // Counter for number of successfully read tasks/initial tasklist size
            int tasksRead = 0;

            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\|");

                // Skip lines that do not contain sufficient task data
                if (parts.length < 3) continue;

                // Parse type, completion, detail information from the three partitions
                String type = parts[0].trim();
                boolean isDone = parts[1].trim().equals("1");
                String description = parts[2].trim();


                Task task;

                // Parse tasks based on their type
                switch (type) {
                    case (ToDo.typeIcon): // ToDo task

                        TaskList.addToDo(description);
                        TaskList.updateIsDone(tasksRead++, isDone);

                        break;
                    case (Deadline.typeIcon): // Deadline task
                        if (parts.length < 4) continue;

                        String deadline = parts[3].trim();

                        TaskList.addDeadline(description, deadline);
                        TaskList.updateIsDone(tasksRead++, isDone);

                        break;
                    case (Event.typeIcon): // Event task
                        if (parts.length < 5) continue;

                        String start = parts[3].trim();
                        String end = parts[4].trim();
                        Event readEvent = new Event(description, start, end);

                        TaskList.addEvent(description, start, end);
                        TaskList.updateIsDone(tasksRead++, isDone);

                        break;
                    default:
                        continue; // Skip unknown task types
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading tasks: " + e.getMessage());
        }
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
