import java.util.ArrayList;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

/**
 * Handles the loading and saving of tasks to and from a file.
 * This class manages the storage of tasks in a text file and the conversion of tasks to and from file format.
 */
public class Storage {
    private final String filePath;

    // Constructs a Storage object that operates on the specified file path.
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads the tasks from the file. If the file does not exist, it creates the file and returns an empty list.
     * Parses the task details from the file and converts them into Task objects.
     */
    public ArrayList<Task> load() throws IOException {
        File file = new File(filePath);
        ArrayList<Task> tasks = new ArrayList<>();

        if (!file.exists()) {
            File directory = new File(file.getParent());

            // Create directory if it doesn't exist
            if (!directory.exists()) {
                directory.mkdirs();
            }

            // Create new file if it doesn't exist
            file.createNewFile();
            return tasks;
        }

        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;

        while ((line = reader.readLine()) != null) {
            String[] data = line.split(" \\| ");
            Task task;

            switch (data[0]) {
                case "T":
                    task = new Todo(data[2]);
                    break;
                case "D":
                    try {
                        LocalDate deadlineDate = LocalDate.parse(data[3]);
                        task = new Deadline(data[2], deadlineDate);
                    } catch (DateTimeParseException e) {
                        throw new IOException("Invalid date format in deadline task.");
                    }
                    break;
                case "E":
                    try {
                        LocalDate eventDate = LocalDate.parse(data[3]);
                        LocalTime startTime = LocalTime.parse(data[4]);
                        LocalTime endTime = LocalTime.parse(data[5]);
                        task = new Event(data[2], eventDate, startTime, endTime);
                    } catch (DateTimeParseException e) {
                        throw new IOException("Invalid date or time format in event task.");
                    }
                    break;
                default:
                    throw new IOException("Corrupted data format.");
            }

            if (data[1].equals("1")) {
                task.markAsDone();
            }

            tasks.add(task);
        }

        reader.close();
        return tasks;
    }

    /**
     * Saves the current list of tasks to the file.
     * Converts each task into a format suitable for storage and writes them to the file.
     */
    public void save(ArrayList<Task> tasks) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));

        for (Task task : tasks) {
            writer.write(task.toFileFormat() + System.lineSeparator());
        }

        writer.close();
    }
}