import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Handles loading and saving tasks to a file.
 */
public class Storage {

    private final String filePath;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    /**
     * Constructs a Storage object with the specified file path.
     *
     * @param filePath The file path where tasks are stored.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the specified file and returns a list of tasks.
     *
     * @return A list of tasks loaded from the file.
     * @throws BebeException if there is an error reading from the file.
     */
    public List<Task> load() throws BebeException {
        List<Task> tasks = new ArrayList<>();

        if (!Files.exists(Paths.get(filePath))) {
            System.out.println("Data file does not exist at: " + filePath);  // Debug message
            return tasks;  // Return empty list if no file exists
        }

        System.out.println("Loading tasks from file: " + filePath);  // Debug message
        try (Scanner scanner = new Scanner(new File(filePath))) {
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                System.out.println("Reading line: " + line);  // Debug message for each line

                String[] parts = line.split(" \\| ");
                if (parts.length < 3) {
                    throw new BebeException("Incorrect format in data file.");
                }

                Task task = parseTask(parts);  // Delegate task parsing to a method
                if (parts[1].equals("1")) task.markAsDone();  // Mark as done if needed
                tasks.add(task);
            }
        } catch (IOException e) {
            throw new BebeException("Error loading file.");
        }
        return tasks;
    }

    /**
     * Parses the task from the parts of a file line.
     *
     * @param parts The array containing task details split from the line.
     * @return The parsed Task object.
     * @throws BebeException If there is an error in the task format or date parsing.
     */
    private Task parseTask(String[] parts) throws BebeException {
        try {
            switch (parts[0]) {
                case "T":
                    return new Todo(parts[2]);
                case "D":
                    return new Deadline(parts[2], parseDateTime(parts[3]));
                case "E":
                    String[] eventTimeParts = parts[3].split(" to ");
                    if (eventTimeParts.length < 2) {
                        throw new BebeException("Invalid event format: missing 'to' in event times.");
                    }
                    return new Event(parts[2], parseDateTime(eventTimeParts[0]), parseDateTime(eventTimeParts[1]));
                default:
                    throw new BebeException("Unknown task type in file.");
            }
        } catch (DateTimeParseException e) {
            throw new BebeException("Invalid date/time format in task.");
        }
    }

    /**
     * Parses a date-time string using the specified format.
     *
     * @param dateTimeString The string representing the date and time.
     * @return The parsed LocalDateTime object.
     * @throws DateTimeParseException If the string cannot be parsed.
     */
    private LocalDateTime parseDateTime(String dateTimeString) throws DateTimeParseException {
        return LocalDateTime.parse(dateTimeString, FORMATTER);
    }

    /**
     * Saves the current list of tasks to the file.
     *
     * @param tasks The list of tasks to save.
     * @throws BebeException if there is an error writing to the file.
     */
    public void save(List<Task> tasks) throws BebeException {
        try {
            File directory = new File("./data");
            if (!directory.exists()) {
                directory.mkdir();
            }

            try (FileWriter writer = new FileWriter(filePath)) {
                for (Task task : tasks) {
                    writer.write(task.toFileFormat() + "\n");
                }
            }
        } catch (IOException e) {
            throw new BebeException("Error saving tasks.");
        }
    }
}
