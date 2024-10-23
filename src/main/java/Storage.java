import java.util.*;
import java.io.*;

/**
 * The Storage class is responsible for reading and writing tasks from and to a file.
 * It handles the loading of tasks from a file during startup and saves tasks back to the file.
 */
public class Storage {
    private String filePath;

    /**
     * Constructor for Storage.
     *
     * @param filePath The path to the file where tasks are stored.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the specified file. If the file does not exist, returns an empty task list.
     *
     * @return A list of tasks loaded from the file.
     */
    public List<Task> loadTasks() {
        List<Task> tasks = new ArrayList<>();
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                return tasks;  // Return an empty list if the file doesn't exist
            }

            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String taskLine = scanner.nextLine();
                String[] parts = taskLine.split(" \\| ");

                // Check if the line contains at least 3 parts (taskType, isDone, description)
                if (parts.length < 3) {
                    System.out.println("Error: Task format is incorrect. Skipping line.");
                    continue;  // Skip to the next line
                }

                String taskType = parts[0];
                boolean isDone = parts[1].equals("1");
                String description = parts[2];

                Task task = null;

                switch (taskType) {
                case "T":
                    // TodoTask doesn't have extra fields, just create it
                    task = new TodoTask(description);
                    break;
                case "D":
                    if (parts.length >= 4) {
                        String by = parts[3];
                        task = new DeadlineTask(description, by);
                    } else {
                        System.out.println("Error: Missing 'by' field for deadline task. Skipping line.");
                    }
                    break;
                case "E":
                    if (parts.length >= 4) {
                        String[] eventParts = parts[3].split(" /to ");
                        if (eventParts.length == 2) {
                            String from = eventParts[0].trim();
                            String to = eventParts[1].trim();
                            task = new EventTask(description, from, to);
                        } else {
                            System.out.println("Error: Missing 'from' or 'to' field for event task. Skipping line.");
                        }
                    } else {
                        System.out.println("Error: Missing event details ('from' and 'to'). Skipping line.");
                    }
                    break;
                default:
                    System.out.println("Error: Unknown task type. Skipping line.");
                }

                if (task != null && isDone) {
                    task.setDone(true);  // Mark task as done if it was previously done
                }

                if (task != null) {
                    tasks.add(task);  // Add the task to the list if it's valid
                }
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("No saved tasks found.");
        }

        return tasks;
    }

    /**
     * Saves the given list of tasks to the file.
     *
     * @param tasks The list of tasks to be saved to the file.
     */
    public void saveTasks(List<Task> tasks) {
        try {
            // Ensure the directory exists
            File directory = new File("./data");
            if (!directory.exists()) {
                if (!directory.mkdirs()) {
                    System.out.println("Error: Failed to create directory.");
                    return;  // Stop execution if the directory can't be created
                }
            }

            FileWriter writer = new FileWriter(filePath);

            // Write each task to the file
            for (Task task : tasks) {
                String taskLine = task.getType() + " | " + (task.isDone() ? "1" : "0") + " | " + task.getDescription();
                if (task instanceof DeadlineTask) {
                    taskLine += " | " + ((DeadlineTask) task).getBy();
                } else if (task instanceof EventTask) {
                    taskLine += " | " + ((EventTask) task).getEventDetails();
                }
                writer.write(taskLine + System.lineSeparator());
            }

            writer.close();  // Close the writer after saving
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }
}