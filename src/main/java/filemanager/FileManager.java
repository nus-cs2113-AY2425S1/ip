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


/**
 * The FileManager class handles the loading and saving of tasks to and from a file.
 * It supports the reading of tasks from a file, parsing them into Task objects,
 * and writing them back to the file in a specific format. The supported task types
 * include ToDo, Deadline, and Event.
 */

public class FileManager {

    private String filePath;

    public FileManager(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads the tasks from the file specified by the file path. If the file
     * or its directory does not exist, it will create them. The method reads
     * each line of the file, parses it into a Task object, and adds it to the
     * task list.
     *
     * @return An ArrayList of tasks loaded from the file.
     */

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

    /**
     * Saves the list of tasks to the file. Each task is written in a format
     * suitable for reading back into the program later.
     *
     * @param tasks An ArrayList of Task objects to be saved to the file.
     */

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

    /**
     * Parses a line from the file into a Task object. The line is expected
     * to be in a specific format corresponding to different task types
     * (ToDo, Deadline, or Event). Invalid or unrecognized lines are skipped.
     *
     * @param line The string representing a task from the file.
     * @return A Task object corresponding to the line or null if the line is invalid.
     */

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
