package Taylor.command;

import Taylor.task.TaskList;
import Taylor.task.Task;
import Taylor.task.Todo;
import Taylor.task.Deadline;
import Taylor.task.Event;

import java.io.*;
import java.util.ArrayList;

/**
 * Represents the storage for the Taylor task management application.
 * The Storage class handles reading and writing tasks to a file.
 */
public class Storage {
    private final String filePath;

    /**
     * Constructs a new Storage object with the specified file path.
     *
     * @param filePath The path to the file where tasks will be saved and loaded from.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the file at the specified file path. Tasks are parsed from
     * the file and stored in an ArrayList.
     *
     * @return An ArrayList of Task objects loaded from the file.
     * @throws IOException If an I/O error occurs while reading the file.
     * @throws TaylorException If the file contains an unknown task type.
     */
    public ArrayList<Task> load() throws IOException, TaylorException {
        File file = new File(filePath);
        ArrayList<Task> tasks = new ArrayList<>();
        if (file.exists()) {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = reader.readLine();

            // Read each line from the file and parse it into a task
            while (line != null) {
                String[] parts = line.split(" \\| ");
                String taskType = parts[0].trim();
                boolean isCompleted = parts[1].trim().equals("1");
                String description = parts[2].trim();

                switch (taskType) {
                    case "T" -> tasks.add(new Todo(description, isCompleted));
                    case "D" -> tasks.add(new Deadline(description, isCompleted, parts[3].trim()));
                    case "E" -> {
                        String[] time = parts[3].split("-");
                        String from = time[0].trim();
                        String to = time[1].trim();
                        tasks.add(new Event(description, isCompleted, from, to));
                    }
                    default -> throw new TaylorException("Unknown task type in file");
                }
                line = reader.readLine();
            }
            reader.close();
        }
        return tasks;
    }

    /**
     * Saves the tasks to the file at the specified file path. Each task is serialized
     * into a string format suitable for storage.
     *
     * @param tasks The TaskList containing the tasks to be saved.
     * @throws IOException If an I/O error occurs while writing to the file.
     */
    public void save(TaskList tasks) throws IOException {
        File file = new File(filePath);
        if (file.getParentFile().mkdirs()) {
            System.out.println("Directory created");
        }
        FileWriter writer = new FileWriter(file);
        writer.write(tasks.write());
        writer.close();
    }
}