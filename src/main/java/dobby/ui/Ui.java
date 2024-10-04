package dobby.ui;

import dobby.exceptions.*;
import dobby.tasks.Task;
import dobby.tasklist.TaskList;


/**
 * The Ui class is responsible for interacting with the user. It handles all output displayed to
 * the user and processes the printing of tasks, welcome/goodbye messages, and error handling.
 */
public class Ui {

    private static final String DASH_LINE = "____________________________________________________________";

    /**
     * Prints the welcome message when the application starts.
     */
    public static void printWelcomeMessage() {
        printSeparator();
        System.out.println("    " + "Hello! Dobby is Dobby!");
        System.out.println("    " + "What can Dobby do for master?");
        printSeparator();
    }

    /**
     * Prints a message when a task is added to the list.
     *
     * @param task The task that was added.
     * @param taskListSize The current size of the task list.
     */
    public static void printTaskAddedMessage(Task task, int taskListSize) {
        printSeparator();
        System.out.println("    Dobby has added this task:");
        System.out.println("      " + task);
        if (taskListSize == 1) {
            System.out.println("    Dobby says master has " + taskListSize + " task in the list!");
        } else {
            System.out.println("    Dobby says master has " + taskListSize + " tasks in the list!");
        }
        printSeparator();
    }

    /**
     * Prints the goodbye message when the application is closing.
     */
    public static void printGoodbyeMessage() {
        printSeparator();
        System.out.println("    " + "Thank you master, Dobby is free!!!");
        printSeparator();
    }

    /**
     * Prints The task status message indicating whether a task has been marked as done or incomplete.
     * @param status The status of the task (e.g., "done", "incomplete").
     * @param task The task being marked or unmarked.
     */
    public static void printTaskStatus(String status, Task task) {
        printSeparator();
        if ("done".equals(status)) {
            System.out.println("    Dobby has magically marked this task as done:");
        } else if ("incomplete".equals(status)) {
            System.out.println("    Dobby has marked this task as incomplete:");
        }
        System.out.println("      " + task);
        printSeparator();
    }

    /**
     * Prints the list of tasks in the task list.
     *
     * @param taskList the TaskList object containing the list of tasks.
     * @throws EmptyListException If the task list is empty.
     */
    public static void printList(TaskList taskList) throws EmptyListException {
        if (taskList.isEmpty()) {
            throw new EmptyListException();
        }

        printSeparator();

        if (taskList.size() == 1) {
            System.out.println("    Here is the task in master's list:");
        } else {
            System.out.println("    Here are the tasks in master's list:");
        }

        for (int i = 1; i <= taskList.size(); i++) {
            Task t = taskList.get(i - 1);
            System.out.println("    " + i + "." + t);
        }
        printSeparator();
    }

    /**
     * Handles different types of exceptions and prints corresponding error messages to the user.
     *
     * @param exception The exception that occurred.
     */
    public static void handleExceptions(Exception exception) {
        printSeparator();
        if (exception instanceof InvalidDescriptionException) {
            System.out.println("    Dobby thinks master should check their input!");
        } else if (exception instanceof IllegalInputException) {
            System.out.println("    Dobby doesn't understand master's command!");
        } else if (exception instanceof TaskAlreadyMarkedException) {
            System.out.println("    Dobby says master's task is already marked!");
        } else if (exception instanceof TaskAlreadyUnmarkedException) {
            System.out.println("    Dobby says master's task is already unmarked!");
        } else if (exception instanceof EmptyListException) {
            System.out.println("    Dobby says master's list is empty!");
        } else if (exception instanceof IndexOutOfBoundsException) {
            System.out.println("    Dobby says master's list is full!");
        } else if (exception instanceof InvalidTaskNumberException) {
            System.out.println("    Dobby says master did not give a valid task number!");
        }
        printSeparator();
    }

    /**
     * Prints a separator line to distinguish between different sections of output.
     */
    public static void printSeparator() {
        System.out.println("  " + DASH_LINE);
    }

    public static void showMessage(String message) {
        System.out.println("    " + message);
    }

    public static void printTask(int taskNumber, Task task) {
        System.out.println("    " + taskNumber + "." + task);
    }
}
