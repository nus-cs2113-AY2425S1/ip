package filemanager;

import tasks.Task;
import tasks.Deadline;
import tasks.Event;
import tasks.Todo;

import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileManager {

    private String filePath;

    public FileManager(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> loadTasks() {
        ArrayList<Task> taskList = new ArrayList<>();

        try {
            File file = new File(filePath);
            if (!file.exists()) {
                file.getParentFile().mkdirs(); // Create the data directory if it doesn't exist
                file.createNewFile();
            }

            Scanner scanner = new Scanner(new FileReader(file));
            while (scanner.hasNextLine()) {
                String taskLine = scanner.nextLine();
                taskList.add(parseTask(taskLine));
            }
            scanner.close();
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        return taskList;
    }

    public void saveTasksToFile(ArrayList<Task> tasks) {
        try {
            FileWriter writer = new FileWriter(filePath);
            for (Task task : tasks) {
                writer.write(task.fileFormat() + System.lineSeparator());
            }

            writer.flush();
            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving tasks to file: " + e.getMessage());
        }
    }

    public Task parseTask(String line) {
        String[] parts = line.split(" \\| ");  // Split the line by " | " delimiter

        String taskType = parts[0];  // The first part tells us the task type (T, D, or E)
        boolean isCompleted = parts[1].equals("+");  // The second part is + or - indicating completion

        // Handle different task types with the correct number of parts
        switch (taskType) {
            case "T":
                if (parts.length < 3) {
                    System.out.println("Invalid ToDo task format: " + line);
                    return null;
                }
                String taskName = parts[2];
                Task todoTask = new Todo(taskName);
                if (isCompleted) {
                    todoTask.setStatus();
                }
                return todoTask;

            case "D":
                if (parts.length < 4) {
                    System.out.println("Invalid Deadline task format: " + line);
                    return null;  // Skip invalid tasks
                }
                String deadlineTaskName = parts[2];  // The third part is the task name for Deadline
                String deadline = parts[3].replace("by ", "");  // Remove the "by" part for Deadline
                Task deadlineTask = new Deadline(deadlineTaskName, deadline);
                if (isCompleted) {
                    deadlineTask.setStatus();
                }
                return deadlineTask;

            case "E":
                if (parts.length < 4) {
                    System.out.println("Invalid Event task format: " + line);
                    return null;  // Skip invalid tasks
                }
                String eventTaskName = parts[2];  // The third part is the task name for Event
                String[] eventTimes = parts[3].replace("from ", "").split(" to ");  // Split start and end times
                if (eventTimes.length < 2) {
                    System.out.println("Invalid Event task time format: " + line);
                    return null;  // Skip invalid tasks
                }
                String start = eventTimes[0];
                String end = eventTimes[1];
                Task eventTask = new Event(eventTaskName, start, end);
                if (isCompleted) {
                    eventTask.setStatus();
                }
                return eventTask;

            default:
                System.out.println("Unrecognized task format: " + line);
                return null;
        }
    }

}
