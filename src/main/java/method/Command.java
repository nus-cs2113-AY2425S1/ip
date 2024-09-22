package method;

import task.Task;
import task.type.Deadline;
import task.type.Event;
import task.type.Todo;
import exceptions.IrisException;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class Command {

    private static final String SAVED_DATA_FILE = "./iris.txt";
    private static final String WELCOME_MESSAGE = "Hello! I'm Iris!\n Welcome Back!";
    private static final String END_CHAT_MESSAGE = "Bye! Hope to see you again soon!";
    private static final String EMPTY_LIST_MESSAGE = "No tasks added, add more now!";
    private static final String NONEMPTY_LIST_MESSAGE = "Here are the tasks in your list:";
    private static final String MISSING_TASK_INDEX_MESSAGE = "NOO!!! Missing task number";
    private static final String INVALID_TASK_INDEX_MESSAGE = "WHAT!!! This task does not exist";
    private static final String INVALID_TASK_INDEX_FORMAT_MESSAGE = "HMMM... The index of the task must be an integer";
    private static final String MARK_AS_COMPLETED_MESSAGE = "Nice! I've marked this task as done:";
    private static final String UNMARK_FROM_COMPLETED_MESSAGE = "OK, I've marked this task as not done yet:";
    private static final String EMPTY_DESCRIPTION_MESSAGE = "You need a description for the task!";

    // list
    public static void listTasks(ArrayList<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println(EMPTY_LIST_MESSAGE);
            return;
        }
        System.out.println(NONEMPTY_LIST_MESSAGE);
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
            System.out.println(EMPTY_DESCRIPTION_MESSAGE);
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

    // delete
    public static void deleteTask(ArrayList<Task> tasks, String text) {
        try {
            Task taskToDelete = getTaskNum(tasks, text);
            tasks.remove(taskToDelete);
            printDeleteTaskMessage(taskToDelete, tasks.size());
        }
        catch (IrisException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void printDeleteTaskMessage(Task taskToDelete, int size) {
        System.out.println("Noted. I've removed this task:\n"
                + taskToDelete
                + "\nNow you have "
                + size
                + " tasks in the list");
    }

    // mark and unmark
    public static void changeTaskStatus(ArrayList<Task> tasks, String text, boolean status) {
        try {
            Task taskToChange = getTaskNum(tasks, text);
            taskToChange.mark(status);
            printChangeTaskStatusMessage(taskToChange, status);
        }
        catch (IrisException e) {
            System.out.println(e.getMessage());
        }
    }

    private static Task getTaskNum(ArrayList<Task> tasks, String text) throws IrisException {
        try {
            String[] textParts = text.split(" ");
            if (textParts.length == 1) {
                throw new IrisException(MISSING_TASK_INDEX_MESSAGE);
            }

            int taskIndex = Integer.parseInt(textParts[1]) - 1;
            boolean isInvalidTaskIndex = taskIndex >= tasks.size() || taskIndex < 0;
            if (isInvalidTaskIndex) {
                throw new IrisException(INVALID_TASK_INDEX_MESSAGE);
            }
            return tasks.get(taskIndex);
        } catch (NumberFormatException e) { // from parseInt
            throw new IrisException(INVALID_TASK_INDEX_FORMAT_MESSAGE);
        }
    }

    private static void printChangeTaskStatusMessage(Task task, boolean status) {
        if (status) {
            System.out.println(MARK_AS_COMPLETED_MESSAGE);
        } else {
            System.out.println(UNMARK_FROM_COMPLETED_MESSAGE);
        }
        System.out.println(task);
    }

    // bye (Save Context)
    public static void saveAndLeave(ArrayList<Task> tasks) {
        try {
            FileOutputStream fos = new FileOutputStream(SAVED_DATA_FILE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(tasks);
            System.out.println(END_CHAT_MESSAGE);
        } catch (IOException e) {
            System.out.println("Failed to save: "
                    + e.getMessage());
        }
    }

    // initialise
    public static ArrayList<Task> loadAndStart() {
        System.out.println(WELCOME_MESSAGE);
        ArrayList<Task> tasks;
        try {
            FileInputStream fis = new FileInputStream(SAVED_DATA_FILE);
            ObjectInputStream ois = new ObjectInputStream(fis);
            tasks = (ArrayList<Task>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            tasks = new ArrayList<>(); // No tasks added
        }
        return tasks;
    }
}
