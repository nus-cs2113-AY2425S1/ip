package aegis;

import aegis.task.Task;
import aegis.task.Deadline;
import aegis.task.Event;
import aegis.task.Todo;

import java.io.*;
import java.util.ArrayList;

/**
 * The Storage class handles loading and saving tasks from and to a file.
 * It provides methods to read tasks from a specified file path and write tasks back to the file.
 */
public class Storage {
    private String filePath;

    /**
     * Constructs a Storage object with the specified file path.
     *
     * @param filePath The file path where tasks are stored and retrieved from.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the file at the specified file path.
     * Reads each line from the file, parses it into a Task object, and adds it to the task list.
     *
     * @return An ArrayList of Task objects loaded from the file.
     * @throws AegisException If the file does not exist or if there is an error reading from the file.
     */
    public ArrayList<Task> load() throws AegisException {
        ArrayList<Task> taskList = new ArrayList<>();
        File file = new File(filePath);
        if (!file.exists()) {
            throw new AegisException(" No task file found. A new file will be created.");
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Task task = parseTask(line);
                taskList.add(task);
            }
        } catch (IOException e) {
            throw new AegisException(" Error reading from file.");
        }

        return taskList;
    }

    /**
     * Parses a line of text from the file and converts it into a Task object.
     *
     * @param line A line of text from the file representing a task.
     * @return A Task object parsed from the line.
     * @throws AegisException If the task type is unrecognized or if the file format is corrupted.
     */
    private Task parseTask(String line) throws AegisException {
        String[] parts = line.split(" \\| ");
        String taskType = parts[0];
        boolean isDone = parts[1].equals("1");

        switch (taskType) {
        case "T":
            Todo todo = new Todo(parts[2]);
            if (isDone) {
                todo.markAsDone();
            }
            return todo;
        case "D":
            Deadline deadline = new Deadline(parts[2], parts[3]);
            if (isDone) {
                deadline.markAsDone();
            }
            return deadline;
        case "E":
            Event event = new Event(parts[2], parts[3], parts[4]);
            if (isDone) {
                event.markAsDone();
            }
            return event;
        default:
            throw new AegisException("File format corrupted");
        }
    }

    /**
     * Saves the list of tasks to the file at the specified file path.
     * Each task is written in a format that can be read back into the application.
     *
     * @param taskList The list of Task objects to be saved to the file.
     * @throws AegisException If there is an error writing the tasks to the file.
     */
    public void save(ArrayList<Task> taskList) throws AegisException {
        try (FileWriter writer = new FileWriter(filePath)) {
            for (Task task : taskList) {
                writer.write(task.toFileFormat() + System.lineSeparator());
            }
        } catch (IOException e) {
            throw new AegisException(" Error saving tasks to file.");
        }
    }
}
