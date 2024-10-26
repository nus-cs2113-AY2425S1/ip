import java.io.*;
import java.nio.file.*;
import java.time.*;
import java.time.format.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles the persistence of task data to and from storage.
 * This class manages reading and writing tasks to a file in a specified format.
 * Each task is stored as a single line with fields separated by vertical bars (|).
 *
 * The file format for each task type is as follows:
 * - Todo:     T | isDone | description
 * - Deadline: D | isDone | description | deadline
 * - Event:    E | isDone | description | startTime | endTime
 */
public class Storage {
    private String filePath;

    /**
     * Constructs a Storage object with the specified file path.
     *
     * @param filePath The path where task data will be stored
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the storage file.
     * Creates necessary directories if they don't exist.
     * If the file exists, reads and parses each line into a Task object.
     *
     * @return                  - ArrayList of Task objects loaded from the file
     * @throws PlopBotException - If there is an error reading the file or parsing tasks
     */
    public ArrayList<Task> load() throws PlopBotException {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            Files.createDirectories(Paths.get(filePath).getParent());
            if (Files.exists(Paths.get(filePath))) {
                List<String> lines = Files.readAllLines(Paths.get(filePath));
                for (String line : lines) {
                    tasks.add(parseTaskFromString(line));
                }
            }
        } catch (IOException e) {
            throw new PlopBotException("Error loading tasks: " + e.getMessage());
        }
        return tasks;
    }

    /**
     * Saves the provided list of tasks to the storage file.
     * Creates necessary directories if they don't exist.
     * Each task is converted to a string format before being written.
     *
     * @param tasks             - ArrayList of Task objects to be saved
     * @throws PlopBotException - If there is an error writing to the file
     */
    public void save(ArrayList<Task> tasks) throws PlopBotException {
        try {
            Files.createDirectories(Paths.get(filePath).getParent());
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
            for (Task task : tasks) {
                writer.write(convertTaskToString(task));
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            throw new PlopBotException("Error saving tasks: " + e.getMessage());
        }
    }

    /**
     * Parses a string representation of a task into a Task object.
     * The string should be in the format: type | isDone | description [| additional fields...]
     * where type is 'T' for Todo, 'D' for Deadline, or 'E' for Event.
     *
     * @param line              - The string representation of the task
     * @return                  - Task object parsed from the string
     * @throws PlopBotException - If the string format is invalid or contains invalid data
     */
    private Task parseTaskFromString(String line) throws PlopBotException {
        String[] parts = line.split("\\|");
        if (parts.length < 3) {
            throw new PlopBotException("Invalid task format: " + line);
        }
        String type = parts[0].trim();
        boolean isDone = parts[1].trim().equals("1");
        String description = parts[2].trim();

        Task task;
        switch (type) {
            case "T":
                task = new Task(description);
                break;
            case "D":
                if (parts.length < 4) throw new PlopBotException("Invalid deadline format: " + line);
                LocalDate deadline = LocalDate.parse(parts[3].trim(), DateTimeFormatter.ISO_LOCAL_DATE);
                task = new Task(description, deadline);
                break;
            case "E":
                if (parts.length < 5) throw new PlopBotException("Invalid event format: " + line);
                LocalDateTime startTime = LocalDateTime.parse(parts[3].trim(), DateTimeFormatter.ISO_LOCAL_DATE_TIME);
                LocalDateTime endTime = LocalDateTime.parse(parts[4].trim(), DateTimeFormatter.ISO_LOCAL_DATE_TIME);
                task = new Task(description, startTime, endTime);
                break;
            default:
                throw new PlopBotException("Unknown task type: " + type);
        }
        if (isDone) task.toggleStatus();
        return task;
    }

    /**
     * Converts a Task object into its string representation for storage.
     * The format varies based on the task type:
     * - Todo:     T | isDone | description
     * - Deadline: D | isDone | description | deadline
     * - Event:    E | isDone | description | startTime | endTime
     *
     * @param task - The Task object to convert
     * @return     - String representation of the task
     */
    private String convertTaskToString(Task task) {
        String baseString = String.format("%s | %d | %s",
                task.getTypeIcon(), task.getStatus() ? 1 : 0, task.getName());
        switch (task.getTypeIcon()) {
            case "D":
                return baseString + " | " + task.getDeadline().format(DateTimeFormatter.ISO_LOCAL_DATE);
            case "E":
                return baseString + " | " + task.getStartTime().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME) +
                        " | " + task.getEndTime().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
            default:
                return baseString;
        }
    }
}
