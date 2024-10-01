package bean.tasklist;

import java.io.IOException;
import java.util.ArrayList;

import bean.Main;
import bean.exceptions.EmptyListException;
import bean.exceptions.InsufficientSpaceException;
import bean.exceptions.TaskNumOutOfBoundsException;
import bean.storage.Storage;
import bean.task.Task;
import bean.task.Todo;
import bean.task.Deadline;
import bean.task.Event;
import bean.ui.Ui;

import static bean.constants.Constants.*;

public class TaskList {

    private static ArrayList<Task> tasks;

    public enum TaskType {
        TODO, DEADLINE, EVENT
    }

    /**
     * Creates an empty task list.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    // Getters and Setters
    public static ArrayList<Task> getTasks() {
        return tasks;
    }

    public static Boolean isWithinSizeLimit() {
        return tasks.size() < MAX_LIST_COUNT;
    }

    public static Boolean taskNumIsValid(int taskNum) {
        return taskNum >= 0 && taskNum <= tasks.size();
    }

    public static void markTaskAsDone(int taskNum) throws IOException {

        int taskIndex = taskNum - 1;
        tasks.get(taskIndex).setStatus(true);
        // Confirmation message
        Ui.printFormattedReply(INDENT + "Task " + taskNum + " has been marked as DONE:\n" +
                INDENT + INDENT + tasks.get(taskIndex).toString());
        Storage.overwriteDataFile(tasks);
    }

    public static void unmarkTaskAsDone(int taskNum) throws IOException {
        int taskIndex = taskNum - 1;
        tasks.get(taskIndex).setStatus(false);
        // Confirmation message
        Ui.printFormattedReply(INDENT + "Task " + taskNum + " has been marked as UNDONE:\n" +
                INDENT + INDENT + tasks.get(taskIndex).toString());
        Storage.overwriteDataFile(tasks);
    }

    public static void addTask(Task task) throws IOException {
        tasks.add(task);
    }

    public static void addTask(String userInput, Main.TaskType taskType) throws InsufficientSpaceException, IOException {
        if (tasks.size() >= MAX_LIST_COUNT) {
            throw new InsufficientSpaceException();
        }

        if (taskType == Main.TaskType.TODO) {
            // Extract description
            String description = userInput.split("todo ")[1].trim();

            tasks.add(new Todo(description));

        } else if (taskType == Main.TaskType.DEADLINE) {
            // Extract description and by
            String[] parts = userInput.split("/by ");
            // parts: [0] = "deadline {description} ", [1] = " {by}"
            String description = parts[0].substring("deadline ".length()).trim();
            String by = parts[1].trim();

            tasks.add(new Deadline(description, by));

        } else if (taskType == Main.TaskType.EVENT) {
            // Extract description, from and to
            String[] splitDescription = userInput.split("/from");
            // splitDescription: [0] = "event {description} ", [1] = "{from} /to {to}"
            String description = splitDescription[0].substring("events".length()).trim();
            String[] splitFromTo = splitDescription[1].split("/to");
            // splitFromTo: [0] = "{from} ", [1] = "{to}"
            String from = splitFromTo[0].trim();
            String to = splitFromTo[1].trim();

            tasks.add(new Event(description, from, to));

        } else {
            throw new RuntimeException("Something went wrong while adding task");
        }
        Storage.appendNextLineToFile(tasks.get(tasks.size() - 1).serialise());
    }

    public static void deleteTask(int taskNum) throws TaskNumOutOfBoundsException, IOException {
        if (taskNum < 0 || taskNum > tasks.size()) {
            throw new TaskNumOutOfBoundsException();
        }
        int taskIndex = taskNum - 1;
        Ui.printFormattedReply(INDENT + "Deleted task: " + tasks.get(taskIndex) + ".");
        tasks.remove(taskIndex);
        Storage.overwriteDataFile(tasks);
    }


    public static void printTasks() throws EmptyListException {
        if (tasks.isEmpty()) {
            throw new EmptyListException();
        }

        System.out.println(SEPARATOR_LINE +
                INDENT + "TO DO LIST:\n");

        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(INDENT + (i + 1) + ". " + tasks.get(i).toString());
        }
        System.out.println(SEPARATOR_LINE);
    }

}
