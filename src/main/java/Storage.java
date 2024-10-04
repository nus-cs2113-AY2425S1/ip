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
     * Constructs a Storage object with the given file path.
     *
     * @param filePath The path to the file where tasks are stored.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads the list of tasks from the file.
     *
     * @return A list of tasks loaded from the file.
     * @throws BebeException If there is an error during loading or if the file format is incorrect.
     */
    public List<Task> load() throws BebeException {
        List<Task> tasks = new ArrayList<>();

        if (!Files.exists(Paths.get(filePath))) {
            return tasks;
        }

        try (Scanner scanner = new Scanner(new File(filePath))) {
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                String[] parts = line.split(" \\| ");
                if (parts.length < 3) {
                    throw new BebeException("Incorrect format in data file.");
                }

                Task task = parseTask(parts);
                if (parts[1].equals("1")) task.markAsDone();
                tasks.add(task);
            }
        } catch (IOException e) {
            throw new BebeException("Error loading file.");
        }
        return tasks;
    }

    /**
     * Parses a single task from its string representation.
     *
     * @param parts The parts of the task string, split by " | ".
     * @return The corresponding Task object (Todo, Deadline, or Event).
     * @throws BebeException If the task type is unknown or the date format is invalid.
     */
    private Task parseTask(String[] parts) throws BebeException {
        try {
            switch (parts[0]) {
                case "T":
                    return new Todo(parts[2]);
                case "D":
                    return new Deadline(parts[2], parseDateTime(parts[3]));
                case "E":
                    return new Event(parts[2], parseDateTime(parts[3]), parseDateTime(parts[4]));
                default:
                    throw new BebeException("Unknown task type in file.");
            }
        } catch (DateTimeParseException e) {
            throw new BebeException("Invalid date/time format in task.");
        }
    }

    /**
     * Parses a string into a LocalDateTime object.
     *
     * @param dateTimeString The string representation of the date and time.
     * @return The parsed LocalDateTime object.
     * @throws DateTimeParseException If the string cannot be parsed into a valid date and time.
     */
    private LocalDateTime parseDateTime(String dateTimeString) throws DateTimeParseException {
        return LocalDateTime.parse(dateTimeString, FORMATTER);
    }

    /**
     * Saves the list of tasks to the file.
     *
     * @param tasks The list of tasks to be saved.
     * @throws BebeException If there is an error during saving.
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
