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

    /**
     * Creates an empty task list.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Gets a copy of the current list of tasks.
     *
     * @return A copy of the tasks list.
     */
    public static ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Checks if the given task number is valid within the current list of tasks.
     *
     * @param taskNum The task number to check.
     * @return True if the task number is valid, false otherwise.
     */
    public static Boolean taskNumIsValid(int taskNum) {
        return taskNum >= 0 && taskNum <= tasks.size();
    }

    /**
     * Marks the task at the specified index as done.
     *
     * @param taskNum The index of the task to mark as done (1-based).
     * @throws IOException if there's an error writing to the data file.
     */
    public static void markTaskAsDone(int taskNum) throws IOException {

        int taskIndex = taskNum - 1;
        tasks.get(taskIndex).setStatus(true);

        // Confirmation message
        Ui.printFormattedReply(INDENT + "Task " + taskNum + " has been marked as DONE:\n" +
                INDENT + INDENT + tasks.get(taskIndex).toString());
        Storage.overwriteDataFile(tasks);
    }

    /**
     * Marks the task at the specified index as undone.
     *
     * @param taskNum The index of the task to mark as undone (1-based).
     * @throws IOException if there's an error writing to the data file.
     */
    public static void unmarkTaskAsDone(int taskNum) throws IOException {
        int taskIndex = taskNum - 1;
        tasks.get(taskIndex).setStatus(false);

        // Confirmation message
        Ui.printFormattedReply(INDENT + "Task " + taskNum + " has been marked as UNDONE:\n" +
                INDENT + INDENT + tasks.get(taskIndex).toString());
        Storage.overwriteDataFile(tasks);
    }

    /**
     * Adds a new task to the list.
     *
     * @param task The task to add.
     * @throws IOException if there's an error writing to the data file.
     */
    public static void addTask(Task task) throws IOException {
        tasks.add(task);
    }

    /**
     * Adds a new task to the list based on the user input.
     *
     * @param userInput The user input containing the task details.
     * @param taskType The type of task to create (TODO, DEADLINE, or EVENT).
     * @throws InsufficientSpaceException if the list is full.
     * @throws IOException if there's an error writing to the data file.
     */
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

    /**
     * Deletes the task at the specified index.
     *
     * @param taskNum The index of the task to delete (1-based).
     * @throws TaskNumOutOfBoundsException if the task number is invalid.
     * @throws IOException if there's an error writing to the data file.
     */
    public static void deleteTask(int taskNum) throws TaskNumOutOfBoundsException, IOException {
        if (taskNum < 0 || taskNum > tasks.size()) {
            throw new TaskNumOutOfBoundsException();
        }

        int taskIndex = taskNum - 1;
        Ui.printFormattedReply(INDENT + "Deleted task: " + tasks.get(taskIndex) + ".");
        tasks.remove(taskIndex);

        Storage.overwriteDataFile(tasks);
    }

    /**
     * Prints the current list of tasks to the console.
     *
     * @throws EmptyListException if the list is empty.
     */
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
