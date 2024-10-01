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

public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() throws IOException {
        File file = new File(filePath);
        ArrayList<Task> tasks = new ArrayList<>();

        if (!file.exists()) {
            File directory = new File(file.getParent());
            if (!directory.exists()) {
                directory.mkdirs(); // Create directory if it doesn't exist
            }
            file.createNewFile(); // Create new file if it doesn't exist
            return tasks; // Return empty task list if file doesn't exist
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

    public void save(ArrayList<Task> tasks) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));

        for (Task task : tasks) {
            writer.write(task.toFileFormat() + System.lineSeparator());
        }

        writer.close();
    }
}