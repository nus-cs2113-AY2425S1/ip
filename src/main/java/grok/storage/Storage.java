package grok.storage;

import grok.tasks.Task;
import grok.tasks.Todo;
import grok.tasks.Deadline;
import grok.tasks.Event;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the file.
     * @return A list of tasks loaded from the file.
     * @throws IOException If there is an error reading the file.
     */
    public List<Task> loadTasks() throws IOException {
        List<Task> tasks = new ArrayList<>();
        File file = new File(filePath);
        if (!file.exists()) {
            return tasks;  // Return an empty list if the file doesn't exist
        }

        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String taskLine = scanner.nextLine();
            String[] taskDetails = taskLine.split(" \\| ");
            String taskType = taskDetails[0];
            boolean isDone = taskDetails[1].equals("1");

            Task task;
            switch (taskType) {
            case "T":
                task = new Todo(taskDetails[2]);
                break;
            case "D":
                task = new Deadline(taskDetails[2], taskDetails[3]);
                break;
            case "E":
                task = new Event(taskDetails[2], taskDetails[3], taskDetails[4]);
                break;
            default:
                System.out.println("Unknown task type: " + taskType);
                continue;
            }

            if (isDone) {
                task.markAsDone();
            }

            tasks.add(task);
        }
        scanner.close();
        return tasks;
    }

    /**
     * Saves the tasks to the file.
     * @param tasks The list of tasks to save.
     * @throws IOException If there is an error writing to the file.
     */
    public void saveTasks(List<Task> tasks) throws IOException {
        FileWriter writer = new FileWriter(filePath);
        for (Task task : tasks) {
            writer.write(task.toSaveFormat() + "\n");
        }
        writer.close();
    }
}
