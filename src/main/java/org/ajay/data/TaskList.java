package org.ajay.data;

import java.util.ArrayList;
import org.ajay.data.exceptions.Error;
import org.ajay.data.task.Task;
import org.ajay.ui.TextUi;

public class TaskList {

    private static ArrayList<Task> taskList = new ArrayList<>(); // ArrayList to store tasks

    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    public void setTaskList(ArrayList<Task> taskList) {
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
     */
    public static void markAsDone(int taskNumber) {

        taskList.get(taskNumber - 1).markAsDone();
    }

    public static void markAsDone(String task) {
        int taskNumberMark = Integer.parseInt(task); // Get the task number

        taskList.get(taskNumberMark - 1).markAsDone();
    }

    public static void markAsDone(ArrayList<Task> taskList, int taskNumber) {

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
        System.out.println("Now you have " + numberOfTasks + " tasks in the list.");
    }

    /**
     * Marks the task as not done.
     *
     * @param taskList   list of tasks
     * @param taskNumber number of the task to be marked as not done
     */
    public static void markAsUndone(int taskNumber) {

        taskList.get(taskNumber - 1).markAsUndone();
    }

    public static void markAsUndone(String task) {
        int taskNumberUnmark = Integer.parseInt(task); // Get the task number

        taskList.get(taskNumberUnmark - 1).markAsUndone();
    }

    public static void markAsUndone(ArrayList<Task> taskList, int taskNumber) {

        taskList.get(taskNumber - 1).markAsUndone();
    }

    public static void deleteTask(String task) throws IllegalArgumentException {
        int taskNumberDelete = Integer.parseInt(task);
        if (taskNumberDelete > taskList.size()) {
            throw new IllegalArgumentException("Out of range index. " + Error.OUT_OF_BOUNDS.toString());
        }

        System.out.println("Noted. I've removed this task: \n  " + taskList.get(taskNumberDelete - 1).toString());
        taskList.remove(taskNumberDelete - 1);
        taskList.trimToSize();
        printNumberOfTasks();
    }

    public static void deleteTask(int taskNumber) throws IllegalArgumentException {

        if (taskNumber > taskList.size()) {
            throw new IllegalArgumentException("Out of range index. " + Error.OUT_OF_BOUNDS.toString());
        }

        System.out.println("Noted. I've removed this task: \n  " + taskList.get(taskNumber - 1).toString());
        taskList.remove(taskNumber - 1);
        taskList.trimToSize();
        printNumberOfTasks();
    }

    public static void deleteTask(ArrayList<Task> taskList, int taskNumber) throws IllegalArgumentException {

        if (taskNumber > taskList.size()) {
            throw new IllegalArgumentException("Out of range index. " + Error.OUT_OF_BOUNDS.toString());
        }

        System.out.println("Noted. I've removed this task: \n  " + taskList.get(taskNumber - 1).toString());
        taskList.remove(taskNumber - 1);
        taskList.trimToSize();
        printNumberOfTasks();
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
