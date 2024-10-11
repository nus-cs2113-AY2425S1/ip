package org.ajay.data;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import org.ajay.common.Messages;
import org.ajay.data.exceptions.Error;
import org.ajay.data.exceptions.IllegalArgumentException;
import org.ajay.data.task.Task;
import org.ajay.ui.TextUi;

/**
 * Represents the list of tasks.
 */
public class TaskList {

    /** The list of tasks. */
    private static ArrayList<Task> taskList = new ArrayList<>();

    /**
     * Gets the list of tasks.
     *
     * @return The list of tasks.
     */
    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    /**
     * Sets the list of tasks.
     *
     * @param taskList The list of tasks.
     */
    public final void setTaskList(ArrayList<Task> taskList) {
        TaskList.taskList = taskList;
    }

    /** Default constructor for TaskList. */
    public TaskList() {
    }

    /**
     * Constructor for TaskList.
     *
     * @param taskList The list of tasks.
     */
    public TaskList(ArrayList<Task> taskList) {
        setTaskList(taskList);
    }

    /**
     * Adds a task to the list.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        taskList.add(task);
    }

    /**
     * Marks the task as done.
     *
     * @param taskNumber Number of the task to be marked as done
     * @throws IllegalArgumentException If the task number is out of range
     */
    public static void markAsDone(int taskNumber) throws IllegalArgumentException {
        if (!isValidTaskNumber(taskNumber)) {
            throw new IllegalArgumentException("Invalid task number. Please enter a vaild task number. "
            + Error.OUT_OF_BOUNDS.toString());
        }

        taskList.get(taskNumber - 1).markAsDone();
    }

    /**
     * Marks the task as done.
     *
     * @param taskNumber Number of the task to be marked as done
     * @throws IllegalArgumentException If the task number is out of range
     */
    public static void markAsDone(String taskNumber) throws IllegalArgumentException {
        int taskNumberMark = Integer.parseInt(taskNumber); // Get the task number

        if (!isValidTaskNumber(taskNumberMark)) {
            throw new IllegalArgumentException("Invalid task number. Please enter a vaild task number. "
            + Error.OUT_OF_BOUNDS.toString());
        }

        taskList.get(taskNumberMark - 1).markAsDone();
    }

    /**
     * Marks the task as done.
     *
     * @param taskList   list of tasks
     * @param taskNumber number of the task to be marked as done
     * @throws IllegalArgumentException If the task number is out of range
     */
    public static void markAsDone(ArrayList<Task> taskList, int taskNumber) throws IllegalArgumentException {
        if (!isValidTaskNumber(taskNumber)) {
            throw new IllegalArgumentException("Invalid task number. Please enter a vaild task number. "
            + Error.OUT_OF_BOUNDS.toString());
        }

        taskList.get(taskNumber - 1).markAsDone();
    }

    /**
     * Gets the latest added task in the list.
     *
     * @return The latest added task in the list.
     */
    public Task getLatestTask() {
        return taskList.get(taskList.size() - 1);
    }

    /** Prints the number of tasks in the list. */
    public static void printNumberOfTasks() {
        /** Ensure that the list is trimmed to size */
        taskList.trimToSize();

        int numberOfTasks = taskList.size();
        TextUi.printSuccess("Now you have " + numberOfTasks + " tasks in the list.");
    }

    /**
     * Marks the task as not done.
     *
     * @param taskNumber number of the task to be marked as not done
     * @throws IllegalArgumentException If the task number is out of range
     */
    public static void markAsUndone(int taskNumber) throws IllegalArgumentException {
        if (!isValidTaskNumber(taskNumber)) {
            throw new IllegalArgumentException("Invalid task number. Please enter a vaild task number. "
            + Error.OUT_OF_BOUNDS.toString());
        }

        taskList.get(taskNumber - 1).markAsUndone();
    }

    /**
     * Marks the task as not done.
     *
     * @param task number of the task to be marked as not done
     * @throws IllegalArgumentException
     */
    public static void markAsUndone(String task) throws IllegalArgumentException {
        int taskNumberUnmark = Integer.parseInt(task); // Get the task number

        if (!isValidTaskNumber(taskNumberUnmark)) {
            throw new IllegalArgumentException("Invalid task number. Please enter a vaild task number. "
            + Error.OUT_OF_BOUNDS.toString());
        }

        taskList.get(taskNumberUnmark - 1).markAsUndone();
    }

    /**
     * Marks the task as not done.
     *
     * @param taskList   list of tasks
     * @param taskNumber number of the task to be marked as not done
     * @throws IllegalArgumentException If the task number is out of range
     */
    public static void markAsUndone(ArrayList<Task> taskList, int taskNumber) throws IllegalArgumentException {
        if (!isValidTaskNumber(taskNumber)) {
            throw new IllegalArgumentException("Invalid task number. Please enter a vaild task number. "
            + Error.OUT_OF_BOUNDS.toString());
        }

        taskList.get(taskNumber - 1).markAsUndone();
    }

    /**
     * Checks if the task number is valid.
     *
     * @param taskNumber number of the task
     * @return true if the task number is valid, false otherwise
     */
    private static boolean isValidTaskNumber(int taskNumber) {
        return taskNumber <= taskList.size() && taskNumber > 0;
    }

    /**
     * Deletes a task from the list.
     *
     * @param taskNumber number of the task to be deleted
     * @throws IllegalArgumentException If the task number is out of range
     */
    public static void deleteTask(int taskNumber) throws IllegalArgumentException {

        if (!isValidTaskNumber(taskNumber)) {
            throw new IllegalArgumentException("Invalid task number. Please enter a vaild task number. "
            + Error.OUT_OF_BOUNDS.toString());
        }

        TextUi.printSuccess("Noted. I've removed this task: \n  " + taskList.get(taskNumber - 1).toString());
        taskList.remove(taskNumber - 1);
        taskList.trimToSize();
        printNumberOfTasks();
    }

    /**
     * Deletes a task from the list.
     *
     * @param task number of the task to be deleted
     * @throws IllegalArgumentException If the task number is out of range
     */
    public static void deleteTask(String task) throws IllegalArgumentException  {
        int taskNumberDelete = Integer.parseInt(task);

        if (!isValidTaskNumber(taskNumberDelete)) {
            throw new IllegalArgumentException("Invalid task number. Please enter a vaild task number. "
            + Error.OUT_OF_BOUNDS.toString());
        }

        TextUi.printSuccess("Noted. I've removed this task: \n  " + taskList.get(taskNumberDelete - 1).toString());
        taskList.remove(taskNumberDelete - 1);
        taskList.trimToSize();
        printNumberOfTasks();
    }

    /**
     * Deletes a task from the list.
     *
     * @param taskList   list of tasks
     * @param taskNumber number of the task to be deleted
     * @throws IllegalArgumentException If the task number is out of range
     */
    public static void deleteTask(ArrayList<Task> taskList, int taskNumber) throws IllegalArgumentException {

        if (!isValidTaskNumber(taskNumber)) {
            throw new IllegalArgumentException("Invalid task number. Please enter a vaild task number. "
            + Error.OUT_OF_BOUNDS.toString());
        }

        TextUi.printSuccess("Noted. I've removed this task: \n  " + taskList.get(taskNumber - 1).toString());
        taskList.remove(taskNumber - 1);

        /** Ensure that the list is trimmed to size */
        taskList.trimToSize();

        printNumberOfTasks();
    }

    /**
     * Finds a task in the list using stream.
     *
     * @param keyword keyword to search for
     * @return list of tasks found
     */
    public static ArrayList<Task> findTaskWithStreams(String keyword) {
        ArrayList<Task> foundTasks = new ArrayList<>();
        taskList.stream().filter(task -> task.getDescription().contains(keyword)).forEach(foundTasks::add);

        return foundTasks;
    }

    /**
     * Finds a task in the list using stream.
     *
     * @param tasks   list of tasks
     * @param keyword keyword to search for
     * @return list of tasks found
     */
    public static ArrayList<Task> findTaskWithStreams(ArrayList<Task> tasks, String keyword) {
        ArrayList<Task> foundTasks = new ArrayList<>();
        taskList.stream().filter(task -> task.getDescription().contains(keyword)).forEach(foundTasks::add);

        return foundTasks;
    }

    /** Print all the tasks in the list using streams. */
    public static void printAllTasksWithStreams() {
        if (taskList.isEmpty()) {
            TextUi.printWarning(Messages.MESSAGE_LIST_EMPTY);
        } else {
            // Solution: https://stackoverflow.com/questions/34118412/numbering-in-some-list-java-streams-foreach
            AtomicInteger counter = new AtomicInteger(1);
            System.out.println("Here are the tasks in your list:");
            taskList.stream().forEach(task -> TextUi.printSuccess(counter.getAndIncrement() + "." + task.toString()));
        }
    }

    /**
     * Prints all the tasks in the list using streams.
     *
     * @param tasks list of tasks
     */
    public static void printAllTasksWithStreams(ArrayList<Task> tasks) {
        if (tasks.isEmpty()) {
            TextUi.printWarning(Messages.MESSAGE_LIST_EMPTY);
        } else {
            AtomicInteger counter = new AtomicInteger(1);
            System.out.println("Here are the tasks in your list:");
            tasks.stream().forEach(task -> TextUi.printSuccess(counter.getAndIncrement() + "." + task.toString()));
        }
    }

    /**
     * Prints all the tasks in the list.
     *
     * @param taskList list of tasks
     */
    public static void printAllTasks(ArrayList<Task> taskList) {
        if (taskList.isEmpty()) {
            TextUi.printWarning(Messages.MESSAGE_LIST_EMPTY);
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < taskList.size(); i++) {
                TextUi.printSuccess((i + 1) + "." + taskList.get(i).toString());
            }
        }
    }

    /**
     * Prints all the tasks in the list.
     *
     * @param taskList list of tasks
     */
    public static void printAllTasks() {
        if (taskList.isEmpty()) {
            TextUi.printWarning(Messages.MESSAGE_LIST_EMPTY);
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < taskList.size(); i++) {
                TextUi.printSuccess((i + 1) + "." + taskList.get(i).toString());
            }
        }
    }
}
