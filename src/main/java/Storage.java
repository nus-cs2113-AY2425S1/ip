import java.io.*;
import java.util.ArrayList;
import java.util.List;
import task.*;

/**
 * Handles saving and loading tasks to and from a file.
 */
public class Storage {
    private static final String FILE_PATH = "./data/diana.txt";

    /**
     * Saves all tasks from the TaskList to a file at the specified path.
     * The method will create the directories and file if they don't exist.
     *
     * @param tasks TaskList object containing all tasks to be saved.
     * @throws IOException If an I/O error occurs during file writing.
     */
    public static void saveTasks(TaskList tasks) throws IOException {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            file.getParentFile().mkdirs(); // Create directory if it doesn't exist
        }
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));

        for (Task task : tasks.getTasks()) {
            writer.write(task.toFileFormat()); // Write each task in file format
            writer.newLine();
        }

        writer.close(); // Close the writer to avoid memory leaks
    }

    /**
     * Loads tasks from a file at the specified path and returns them as a TaskList object.
     * If the file does not exist, it returns an empty TaskList.
     *
     * @return TaskList containing the loaded tasks.
     * @throws IOException If an I/O error occurs during file reading.
     */
    public static TaskList loadTasks() throws IOException {
        TaskList tasks = new TaskList();
        File file = new File(FILE_PATH);

        if (!file.exists()) {
            System.out.println("File does not exist");
            return tasks; // Return empty TaskList if the file doesn't exist
        }

        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;

        while ((line = reader.readLine()) != null) {
            try {
                tasks.addTask(parseTask(line)); // Parse and add each task
            } catch (IllegalArgumentException e) {
                System.out.println("Skipped malformed task: " + line); // Handle invalid tasks
            }
        }

        System.out.println("Loaded " + tasks.getTasks().size() + " tasks");

        reader.close(); // Close the reader to avoid memory leaks
        return tasks;
    }

    /**
     * Parses a line of text from the file into a Task object.
     * The line must be formatted correctly or an exception will be thrown.
     *
     * @param line The line of text to be parsed.
     * @return A Task object created from the parsed data.
     * @throws IllegalArgumentException If the task format is invalid.
     */
    private static Task parseTask(String line) {
        String[] parts = line.split(" \\| ");
        if (parts.length < 3) {
            throw new IllegalArgumentException("Invalid task format: " + line);
        }

        String taskType = parts[0];
        if (!taskType.equals("T") && !taskType.equals("D") && !taskType.equals("E")) {
            throw new IllegalArgumentException("Invalid task type: " + line);
        }

        String taskCompletion = parts[1];
        if (!taskCompletion.equals("1") && !taskCompletion.equals("0")) {
            throw new IllegalArgumentException("Invalid marking format: " + line);
        }

        boolean isDone = parts[1].equals("1");

        String description = parts[2].trim();
        if (description.isEmpty()) {
            throw new IllegalArgumentException("Invalid description: " + line);
        }

        Task task;
        switch (taskType) {
        case "T":
            if (parts.length > 3) {
                throw new IllegalArgumentException("Invalid todo format: " + line);
            }
            task = new Todo(description);
            break;
        case "D":
            if (parts.length < 4) {
                throw new IllegalArgumentException("Invalid deadline format: " + line);
            }
            String by = parts[3];
            task = new Deadline(description, by);
            break;
        case "E":
            if (parts.length < 5) {
                throw new IllegalArgumentException("Invalid event format: " + line);
            }
            String from = parts[3];
            String to = parts[4];
            task = new Event(description, from, to);
            break;
        default:
            task = new Task(description);
            System.out.println("Unknown task type: " + taskType);
        }

        if (isDone) {
            task.markAsDone();
        }

        return task;
    }
}
