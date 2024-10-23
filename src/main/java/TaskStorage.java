import java.io.*;
import java.util.*;

public class TaskStorage {
    private static final String FILE_PATH = "./data/andy.txt";  // Updated to 'andy.txt'

    // Method to load tasks from a file
    public List<Task> loadTasks() {
        List<Task> tasks = new ArrayList<>();
        try {
            File file = new File(FILE_PATH);
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
                    continue;  // Skip to the next line without printing anything
                }

                // Task parsing logic
                String taskType = parts[0];
                boolean isDone = parts[1].equals("1");
                String description = parts[2];

                Task task = null;

                // Switch case for different task types (TodoTask, DeadlineTask, EventTask)
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
                        String eventDetails = parts[3];
                        String[] eventParts = eventDetails.split(" /to ");

                        // Ensure both "from" and "to" are present
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

    // Method to save tasks to a file
    public void saveTasks(List<Task> tasks) {
        try {
            // Ensure the directory exists
            File directory = new File("./data");
            if (!directory.exists()) {
                directory.mkdirs();  // Create the directory if it doesn't exist
            }

            FileWriter writer = new FileWriter(FILE_PATH);

            // Write each task to the file
            for (Task task : tasks) {
                String taskLine = task.getType() + " | " + (task.isDone() ? "1" : "0") + " | " + task.getDescription();

                if (task instanceof DeadlineTask) {
                    taskLine += " | " + ((DeadlineTask) task).getBy();
                } else if (task instanceof EventTask) {
                    taskLine += " | " + ((EventTask) task).getEventDetails();
                }

                writer.write(taskLine + System.lineSeparator());  // Write task to file
            }

            writer.close();  // Close the writer after saving

        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }
}