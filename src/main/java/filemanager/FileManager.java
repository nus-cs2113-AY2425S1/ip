package filemanager;

import tasks.Task;
import tasks.Deadline;
import tasks.Event;
import tasks.Todo;

import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
        if (line.trim().isEmpty()) {
            System.out.println("Empty line found, skipping...");
            return null;
        }

        String[] parts = line.split(" \\| ");

        String taskType = parts[0];
        boolean isCompleted = parts[1].equals("+");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

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
                String deadlineTaskName = parts[2];
                String deadlineString = parts[3].replace("by ", "");
                LocalDateTime deadline = LocalDateTime.parse(deadlineString, formatter);
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
                String eventTaskName = parts[2];
                String[] eventTimes = parts[3].replace("from ", "").split(" to ");
                if (eventTimes.length < 2) {
                    System.out.println("Invalid Event task time format: " + line);
                    return null;
                }
                String startString = eventTimes[0];
                String endString = eventTimes[1];
                LocalDateTime start = LocalDateTime.parse(startString, formatter);
                LocalDateTime end = LocalDateTime.parse(endString, formatter);
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
