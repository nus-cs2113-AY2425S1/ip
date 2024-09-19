package Tars;
import Tars.Task.Deadline;
import Tars.Task.Task;
import Tars.Task.Todo;
import Tars.Task.Event;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    // Save tasks to file
    public void saveTasks(List<Task> tasks) throws IOException {
        File file = new File(filePath);
        File directory = file.getParentFile();
        if (directory != null && !directory.exists()) {
            directory.mkdirs();  // Create the "data" directory if it doesn't exist
        }

        FileWriter fileWriter = new FileWriter(file);
        for (Task task : tasks) {
            fileWriter.write(task.toSaveFormat() + System.lineSeparator());
        }
        fileWriter.close();
    }

    // Load tasks from file
    public List<Task> loadTasks() throws IOException {
        List<Task> tasks = new ArrayList<>();
        File file = new File(filePath);
        if (!file.exists()) {
            return tasks;
        }

        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String taskString = scanner.nextLine();
            tasks.add(convertStringToTask(taskString));
        }
        scanner.close();
        return tasks;
    }

    // Convert the saved string to a Task object
    private Task convertStringToTask(String taskString) {
        String[] taskParts = taskString.split(" \\| ");
        String type = taskParts[0];
        boolean isDone = taskParts[1].equals("1");

        switch (type) {
            case "T":
                return new Todo(taskParts[2], isDone);
            case "D":
                return new Deadline(taskParts[2], taskParts[3], isDone);
            case "E":
                return new Event(taskParts[2], taskParts[3], taskParts[4], isDone);
            default:
                return null;
        }
    }
}
