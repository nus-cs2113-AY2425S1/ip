package method;

import task.Task;
import task.type.*;
import exceptions.IrisExceptions;

import java.util.ArrayList;

public class Command {
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

    // delete
    public static void deleteTask(ArrayList<Task> tasks, String text) {
        try {
            Task taskToDelete = getTaskNum(tasks, text);
            tasks.remove(taskToDelete);
            System.out.println("Noted. I've removed this task:\n"
                    + taskToDelete
                    + "\nNow you have "
                    + tasks.size()
                    + " tasks in the list");
        }
        catch (IrisExceptions e) {
            System.out.println(e.getMessage());
        }
    }

    // mark and unmark
    public static void changeTaskStatus(ArrayList<Task> tasks, String text, boolean status) {
        try {
            Task taskToChange = getTaskNum(tasks, text);
            taskToChange.mark(status);
            printChangeTaskStatusMessage(taskToChange, status);
        }
        catch (IrisExceptions e) {
            System.out.println(e.getMessage());
        }
    }

    private static Task getTaskNum(ArrayList<Task> tasks, String text) throws IrisExceptions {
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
}
