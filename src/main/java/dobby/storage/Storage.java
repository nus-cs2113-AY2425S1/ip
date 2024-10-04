package dobby.storage;

import dobby.tasks.Deadline;
import dobby.tasks.Event;
import dobby.tasks.Task;
import dobby.tasks.Todo;

import java.util.ArrayList;
import java.util.Scanner;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileWriter;

/**
 * The Storage class handles the saving and loading of tasks to and from a file. It manages the
 * file operations to maintain the task list across program executions.
 */
public class Storage {

    private static final String FILE_PATH = "./data/dobby.txt";
    private static final String DIRECTORY_PATH = "./data";
    public static final String TASK_SPLITTER = "\\|";
    public static final String TODO = "Todo";
    public static final String DEADLINE = "Deadline";
    public static final String EVENT = "Event";

    /**
     * Loads tasks from the file into the provided task list.
     *
     * @param taskList The list of tasks to load the tasks into.
     */
    public void loadTasks(ArrayList<Task> taskList) {
        File directory = new File(DIRECTORY_PATH);
        if (!directory.exists() && !directory.mkdir()) {
            System.out.println("Failed to create directory.");
            return;
        }

        File file = new File(FILE_PATH);
        if (!file.exists()) {
            return;
        }

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Task task = parseTaskFromFile(line);
                if (task != null) {
                    taskList.add(task);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

    /**
     * Saves the tasks from the task list into the file.
     *
     * @param taskList The list of tasks to be saved.
     */
    public static void saveTasks(ArrayList<Task> taskList) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_PATH))) {
            for (Task t: taskList) {
                writer.println(formatTaskForFile(t));
            }
        } catch (IOException e) {
            System.out.println("An error occurred while saving the tasks.");
        }
    }

    /**
     * Formats a task into a string suitable for file storage.
     *
     * @param task The task to format.
     * @return A formatted string representing the task.
     */
    public static String formatTaskForFile(Task task) {
        String type = task.getClass().getSimpleName();
        String status = task.isDone() ? "1" : "0";
        if (task instanceof Deadline) {
            Deadline deadline = (Deadline) task;
            return type + "|" + status + "|" + deadline.getDescription() + "|" + deadline.getBy();
        } else if (task instanceof Event) {
            Event event = (Event) task;
            return type + "|" + status + "|" + event.getDescription() + "|" + event.getFrom() + "|" + event.getTo();
        } else if (task instanceof Todo) {
            return type + "|" + status + "|" + task.getDescription();
        } else {
            return type + "|" + status + "|" + task.getDescription();
        }
    }

    /**
     * Parses a task from a line of text retrieved from the file.
     *
     * @param line The line of text representing a task.
     * @return The Task object corresponding to the line, or null if parsing fails.
     */
    public static Task parseTaskFromFile(String line) {
        String[] parts = line.split(TASK_SPLITTER);
        String type = parts[0];
        boolean isDone = parts[1].equals("1");

        switch (type) {
        case TODO:
            Todo todo = new Todo(parts[2]);
            if (isDone) {
                todo.markAsDone();
            }
            return todo;
        case DEADLINE:
            Deadline deadline = new Deadline(parts[2], parts[3]);
            if (isDone) {
                deadline.markAsDone();
            }
            return deadline;
        case EVENT:
            Event event = new Event(parts[2], parts[3], parts[4]);
            if (isDone) {
                event.markAsDone();
            }
            return event;
        default:
            return null;
        }
    }
}
