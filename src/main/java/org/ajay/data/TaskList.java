package org.ajay.data;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import org.ajay.data.exceptions.Error;
import org.ajay.data.exceptions.IllegalArgumentException;
import org.ajay.data.task.Task;
import org.ajay.ui.TextUi;

public class TaskList {

    private static ArrayList<Task> taskList = new ArrayList<>(); // ArrayList to store tasks

    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    public final void setTaskList(ArrayList<Task> taskList) {
        TaskList.taskList = taskList;
    }

    public TaskList() {
    }

    public TaskList(ArrayList<Task> taskList) {
        setTaskList(taskList);
    }

    /**
     * Adds a task to the list.
     *
     * @param task task to be added
     */
    public void addTask(Task task) {
        taskList.add(task);
    }

    /**
     * Marks the task as done.
     *
     * @param taskList   list of tasks
     * @param taskNumber number of the task to be marked as done
     * @throws IllegalArgumentException
     */
    public static void markAsDone(int taskNumber) throws IllegalArgumentException {
        if (!isValidTaskNumber(taskNumber)) {
            throw new IllegalArgumentException("Invalid task number. Please enter a vaild task number. "
            + Error.OUT_OF_BOUNDS.toString());
        }

        taskList.get(taskNumber - 1).markAsDone();
    }

    public static void markAsDone(String task) throws IllegalArgumentException {
        int taskNumberMark = Integer.parseInt(task); // Get the task number

        if (!isValidTaskNumber(taskNumberMark)) {
            throw new IllegalArgumentException("Invalid task number. Please enter a vaild task number. "
            + Error.OUT_OF_BOUNDS.toString());
        }

        taskList.get(taskNumberMark - 1).markAsDone();
    }

    public static void markAsDone(ArrayList<Task> taskList, int taskNumber) throws IllegalArgumentException {
        if (!isValidTaskNumber(taskNumber)) {
            throw new IllegalArgumentException("Invalid task number. Please enter a vaild task number. "
            + Error.OUT_OF_BOUNDS.toString());
        }

        taskList.get(taskNumber - 1).markAsDone();
    }

    public Task getLatestTask() {
        return taskList.get(taskList.size() - 1);
    }

    /**
     * Prints the number of tasks in the list.
     */
    public static void printNumberOfTasks() {
        taskList.trimToSize();
        int numberOfTasks = taskList.size();
        TextUi.printSuccess("Now you have " + numberOfTasks + " tasks in the list.");
    }

    /**
     * Marks the task as not done.
     *
     * @param taskList   list of tasks
     * @param taskNumber number of the task to be marked as not done
     * @throws IllegalArgumentException
     */
    public static void markAsUndone(int taskNumber) throws IllegalArgumentException {
        if (!isValidTaskNumber(taskNumber)) {
            throw new IllegalArgumentException("Invalid task number. Please enter a vaild task number. "
            + Error.OUT_OF_BOUNDS.toString());
        }

        taskList.get(taskNumber - 1).markAsUndone();
    }

    public static void markAsUndone(String task) throws IllegalArgumentException {
        int taskNumberUnmark = Integer.parseInt(task); // Get the task number

        if (!isValidTaskNumber(taskNumberUnmark)) {
            throw new IllegalArgumentException("Invalid task number. Please enter a vaild task number. "
            + Error.OUT_OF_BOUNDS.toString());
        }

        taskList.get(taskNumberUnmark - 1).markAsUndone();
    }

    public static void markAsUndone(ArrayList<Task> taskList, int taskNumber) throws IllegalArgumentException {
        if (!isValidTaskNumber(taskNumber)) {
            throw new IllegalArgumentException("Invalid task number. Please enter a vaild task number. "
            + Error.OUT_OF_BOUNDS.toString());
        }

        taskList.get(taskNumber - 1).markAsUndone();
    }

    private static boolean isValidTaskNumber(int taskNumber) {
        return taskNumber <= taskList.size() && taskNumber > 0;
    }

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

    public static void deleteTask(ArrayList<Task> taskList, int taskNumber) throws IllegalArgumentException {

        if (!isValidTaskNumber(taskNumber)) {
            throw new IllegalArgumentException("Invalid task number. Please enter a vaild task number. "
            + Error.OUT_OF_BOUNDS.toString());
        }

        TextUi.printSuccess("Noted. I've removed this task: \n  " + taskList.get(taskNumber - 1).toString());
        taskList.remove(taskNumber - 1);
        taskList.trimToSize();
        printNumberOfTasks();
    }

    public static ArrayList<Task> findTaskWithStreams(String keyword) {
        ArrayList<Task> foundTasks = new ArrayList<>();
        taskList.stream().filter(task -> task.getDescription().contains(keyword)).forEach(foundTasks::add);

        return foundTasks;
    }

    public static ArrayList<Task> findTaskWithStreams(ArrayList<Task> tasks, String keyword) {
        ArrayList<Task> foundTasks = new ArrayList<>();
        taskList.stream().filter(task -> task.getDescription().contains(keyword)).forEach(foundTasks::add);

        return foundTasks;
    }

    public static void printAllTasksWithStreams() {
        if (taskList.isEmpty()) {
            TextUi.printWarning("The list is empty.");
        } else {
            // Solution: https://stackoverflow.com/questions/34118412/numbering-in-some-list-java-streams-foreach
            AtomicInteger counter = new AtomicInteger(1);
            System.out.println("Here are the tasks in your list:");
            taskList.stream().forEach(task -> TextUi.printSuccess(counter.getAndIncrement() + "." + task.toString()));
        }
    }

    public static void printAllTasksWithStreams(ArrayList<Task> tasks) {
        if (tasks.isEmpty()) {
            TextUi.printWarning("The list is empty.");
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
            TextUi.printWarning("The list is empty.");
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
            TextUi.printWarning("The list is empty.");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < taskList.size(); i++) {
                TextUi.printSuccess((i + 1) + "." + taskList.get(i).toString());
            }
        }
    }
}
