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


public class Storage {

    private static final String FILE_PATH = "./data/dobby.txt";
    private static final String DIRECTORY_PATH = "./data";
    public static final String TASK_SPLITTER = "\\|";
    public static final String TODO = "Todo";
    public static final String DEADLINE = "Deadline";
    public static final String EVENT = "Event";


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

    public static void saveTasks(ArrayList<Task> taskList) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_PATH))) {
            for (Task t: taskList) {
                writer.println(formatTaskForFile(t));
            }
        } catch (IOException e) {
            System.out.println("An error occurred while saving the tasks.");
        }
    }

    public static String formatTaskForFile(Task task) {
        String type = task.getClass().getSimpleName();
        String status = task.isDone() ? "1" : "0";
        if (task instanceof Deadline) {
            Deadline deadline = (Deadline) task;
            return type + "|" + status + "|" + deadline.getDescription() + "|" + deadline.getBy();
        } else if (task instanceof Event) {
            Event event = (Event) task;
            return type + "|" + status + "|" + event.getDescription() + "|" + event.getFromTime() + "|" + event.getToTime();
        } else if (task instanceof Todo) {
            return type + "|" + status + "|" + task.getDescription();
        } else {
            return type + "|" + status + "|" + task.getDescription();
        }
    }

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
