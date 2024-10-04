import java.util.*;
import java.io.*;

/**
 * The Storage class is responsible for reading and writing tasks from and to a file.
 * It handles the loading of tasks from a file during startup and saves tasks back to the file.
 */
public class Storage {
    private String filePath;

    /**
     * Constructor for Storage.
     *
     * @param filePath The path to the file where tasks are stored.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the specified file. If the file does not exist, returns an empty task list.
     *
     * @return A list of tasks loaded from the file.
     */
    public List<Task> loadTasks() {
        List<Task> tasks = new ArrayList<>();
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                return tasks;
            }

            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String taskLine = scanner.nextLine();
                String[] parts = taskLine.split(" \\| ");
                String taskType = parts[0];
                boolean isDone = parts[1].equals("1");
                String description = parts[2];

                Task task = null;
                switch (taskType) {
                case "T":
                    task = new TodoTask(description);
                    break;
                case "D":
                    task = new DeadlineTask(description, parts[3]);
                    break;
                case "E":
                    String[] eventParts = parts[3].split(" /to ");
                    task = new EventTask(description, eventParts[0], eventParts[1]);
                    break;
                }

                if (task != null && isDone) {
                    task.setDone(true);
                }
                tasks.add(task);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("No saved tasks found.");
        }
        return tasks;
    }

    /**
     * Saves the given list of tasks to the file.
     *
     * @param tasks The list of tasks to be saved to the file.
     */
    public void saveTasks(List<Task> tasks) {
        try {
            FileWriter writer = new FileWriter(filePath);
            for (Task task : tasks) {
                String taskLine = task.getType() + " | " + (task.isDone() ? "1" : "0") + " | " + task.getDescription();
                if (task instanceof DeadlineTask) {
                    taskLine += " | " + ((DeadlineTask) task).getBy();
                } else if (task instanceof EventTask) {
                    taskLine += " | " + ((EventTask) task).getEventDetails();
                }
                writer.write(taskLine + System.lineSeparator());
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }
}