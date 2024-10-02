import java.io.*;
import java.util.ArrayList;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        // Set the file path for storing tasks
        this.filePath = filePath;
    }

    public ArrayList<Task> load() throws AirBorderException {
        // Load tasks from the specified file and return them as an ArrayList
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                // If the file doesn't exist, return an empty list
                return tasks;
            }
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            // Read each line from the file and parse it into a Task object
            while ((line = reader.readLine()) != null) {
                Task task = parseTaskFromFile(line);
                if (task != null) {
                    tasks.add(task);
                }
            }
            reader.close();
        } catch (IOException e) {
            // Throw an exception if an error occurs during file reading
            throw new AirBorderException("Error loading tasks: " + e.getMessage());
        }
        return tasks;
    }

    public void save(TaskList taskList) throws AirBorderException {
        // Save the current list of tasks to the file
        try {
            File file = new File(filePath);
            file.getParentFile().mkdirs(); // Ensure the directory exists
            FileWriter writer = new FileWriter(file);
            // Write each task to the file in a formatted manner
            for (Task task : taskList.getAllTasks()) {
                writer.write(formatTaskForSave(task) + System.lineSeparator());
            }
            writer.close();
        } catch (IOException e) {
            // Throw an exception if an error occurs during file writing
            throw new AirBorderException("Error saving tasks: " + e.getMessage());
        }
    }

    private String formatTaskForSave(Task task) {
        // Format a Task object into a string suitable for saving to file
        if (task instanceof ToDo) {
            return "T | " + (task.isDone ? "1" : "0") + " | " + task.description;
        } else if (task instanceof Deadline) {
            Deadline deadline = (Deadline) task;
            return "D | " + (task.isDone ? "1" : "0") + " | " + task.description + " | " + deadline.by;
        } else if (task instanceof Event) {
            Event event = (Event) task;
            return "E | " + (task.isDone ? "1" : "0") + " | " + task.description + " | " + event.from + " | " + event.to;
        }
        return "";
    }

    private Task parseTaskFromFile(String line) {
        // Parse a line from the file into a Task object
        String[] parts = line.split(" \\| ");
        switch (parts[0]) {
            case "T":
                ToDo todo = new ToDo(parts[2]);
                if (parts[1].equals("1")) {
                    todo.markAsDone();
                }
                return todo;
            case "D":
                Deadline deadline = new Deadline(parts[2], parts[3]);
                if (parts[1].equals("1")) {
                    deadline.markAsDone();
                }
                return deadline;
            case "E":
                Event event = new Event(parts[2], parts[3], parts[4]);
                if (parts[1].equals("1")) {
                    event.markAsDone();
                }
                return event;
            default:
                return null;
        }
    }
}
