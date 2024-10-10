package eva.storage;

import eva.tasks.Deadline;
import eva.tasks.Event;
import eva.tasks.Task;
import eva.tasks.Todo;
import eva.exception.EvaException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * The Storage class is responsible for reading from and writing to a file
 * that stores task data. It provides methods to save tasks to a file and
 * load tasks from a file into an ArrayList of Task objects.
 */
public class Storage {

    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Saves the list of tasks to the file specified by filePath.
     * If the directory does not exist, it creates one.
     *
     * @param tasks The list of tasks to be saved to the file.
     */
    public void saveTasksToFile(ArrayList<Task> tasks) throws EvaException{
        try {
            File directory = new File("data");
            if (!directory.exists()) {
                boolean dirCreated = directory.mkdirs();
                if (!dirCreated) {
                    throw new EvaException("Could not create the directory for storing tasks.");
                }
            }

            try (FileWriter fw = new FileWriter(filePath)) {
                for (Task task : tasks) {
                    fw.write(task.toString().trim() + System.lineSeparator());
                }
            }
        } catch (IOException e) {
            throw new EvaException("An error occurred while saving tasks to file: ");
        }
    }

    /**
     * Loads tasks from the file specified by filePath and returns them
     * as an ArrayList of Task objects.
     *
     * @return An ArrayList of Task objects loaded from the file.
     *         If the file does not exist, returns an empty ArrayList.
     */
    public ArrayList<Task> loadTasksFromFile() {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                return tasks;
            }

            BufferedReader br = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = br.readLine()) != null) {
                String trimmedLine = line.trim();
                Task task = parseTaskFromString(trimmedLine);
                if (task != null) {
                    tasks.add(task);
                }
            }
            br.close();
        } catch (IOException e) {
            System.out.println("An error occurred while loading tasks from file.");
        }
        return tasks;
    }

    /**
     * Parses a task from a string representation of a task. The format of the
     * string determines whether the task is a Todo, Deadline, or Event.
     *
     * @param line The string representation of a task.
     * @return A Task object parsed from the string, or null if the format is unrecognized.
     */
    private Task parseTaskFromString(String line) {
        boolean isDone = line.contains("[X]");

        if (line.startsWith("[T]")) {
            return parseTodoFromStorage(line, isDone);
        } else if (line.startsWith("[D]")) {
            return parseDeadlineFromStorage(line, isDone);
        } else if (line.startsWith("[E]")) {
            return parseEventFromStorage(line, isDone);
        } else {
            System.out.println("Warning: Unrecognized task format - " + line);
            return null;
        }
    }

    private static Event parseEventFromStorage(String line, boolean isDone) {
        String description;
        String[] parts = line.split("\\(from: | to: |\\)");
        description = parts[0].substring(6).trim();
        String from = parts[1].trim();
        String to = parts[2].trim();
        Event event = new Event(description, from, to);
        if (isDone) {
            event.setMarkAsDone();
        }
        return event;
    }

    private static Deadline parseDeadlineFromStorage(String line, boolean isDone) {
        String description;
        String[] parts = line.split("\\(by: ");
        description = parts[0].substring(6).trim();
        String by = parts[1].replace(")", "");
        Deadline deadline = new Deadline(description, by);
        if (isDone) {
            deadline.setMarkAsDone();
        }
        return deadline;
    }

    private static Todo parseTodoFromStorage(String line, boolean isDone) {
        String description;
        description = line.substring(6).trim();
        Todo todo = new Todo(description);
        if (isDone) {
            todo.setMarkAsDone();
        }
        return todo;
    }
}
