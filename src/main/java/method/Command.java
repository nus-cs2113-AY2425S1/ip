package method;

import task.Task;
import task.type.Deadline;
import task.type.Event;
import task.type.Todo;
import exceptions.IrisExceptions;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class Command {

    private static final String SAVED_DATA_FILE = "./src/main/java/data/iris.txt";

    // list
    public static void listTasks(ArrayList<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println("No tasks added.");
            return;
        }
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            Task currentTask = tasks.get(i);
            System.out.println((i + 1)
                    + "."
                    + currentTask);
        }
    }

    // add
    public static void addTask(ArrayList<Task> tasks, String text) {
        String[] textParts = text.split(" ", 2);
        String command = textParts[0].toLowerCase();

        boolean hasOnlyCommand = textParts.length == 1;
        if (hasOnlyCommand) {
            System.out.println("MISSING!!! The description of a "
                    + command
                    + " cannot be empty.");
            return;
        }

        String details = textParts[1];
        Task newTask;
        switch (command) {
        case "todo":
            newTask = new Todo(details);
            break;
        case "deadline":
            newTask = new Deadline(details);
            break;
        default:
            newTask = new Event(details);
        }
        tasks.add(newTask);
        printAddTaskMessage(tasks);
    }

    private static void printAddTaskMessage(ArrayList<Task> tasks) {
        System.out.println("Got it. I've added this task:\n"
                + tasks.get(tasks.size() - 1)
                + "\nNow you have "
                + tasks.size()
                + " tasks in the list");
    }

    // mark and unmark
    public static void changeTaskStatus(ArrayList<Task> tasks, String text, boolean status) {
        try {
            Task taskToChange = getTaskToChangeStatus(tasks, text);
            taskToChange.mark(status);
            printChangeTaskStatusMessage(taskToChange, status);
        }
        catch (IrisExceptions e) {
            System.out.println(e.getMessage());
        }
    }

    private static Task getTaskToChangeStatus(ArrayList<Task> tasks, String text) throws IrisExceptions {
        try {
            String[] textParts = text.split(" ");
            if (textParts.length == 1) {
                throw new IrisExceptions("NOO!!! Missing task number");
            }

            int taskIndex = Integer.parseInt(textParts[1]) - 1;
            boolean isInvalidTaskIndex = taskIndex >= tasks.size() || taskIndex < 0;
            if (isInvalidTaskIndex) {
                throw new IrisExceptions("WHAT!!! This task does not exist");
            }
            return tasks.get(taskIndex);
        } catch (NumberFormatException e) { // from parseInt
            throw new IrisExceptions("HMMM... The index of the task must be an integer");
        }
    }

    private static void printChangeTaskStatusMessage(Task task, boolean status) {
        if (status) {
            System.out.println("Nice! I've marked this task as done:");
        } else {
            System.out.println("OK, I've marked this task as not done yet:");
        }
        System.out.println(task);
    }

    // bye (Save Context)
    public static void saveAndLeave(ArrayList<Task> tasks) {
        try {
            FileOutputStream fos = new FileOutputStream(SAVED_DATA_FILE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(tasks);
            System.out.println("Bye! Hope to see you again soon!");
        } catch (IOException e) {
            System.out.println("Failed to save: " + e.getMessage());
        }
    }

    // initialise
    public static ArrayList<Task> loadAndStart() {
        System.out.println("Hello! I'm Iris!");
        ArrayList<Task> tasks;
        try {
            FileInputStream fis = new FileInputStream(SAVED_DATA_FILE);
            ObjectInputStream ois = new ObjectInputStream(fis);
            tasks = (ArrayList<Task>) ois.readObject();
            System.out.println("Welcome back!");
        } catch (IOException | ClassNotFoundException e) {
            tasks = new ArrayList<>(); // No tasks added
            System.out.println("No tasks found! Start adding!");
        }
        return tasks;
    }
}
