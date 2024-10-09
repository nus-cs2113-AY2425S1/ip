package bitwise.utils;

import bitwise.constants.Constants;
import bitwise.constants.Messages;
import bitwise.tasks.Task;

import java.util.ArrayList;

/**
 * The OutputManager class is responsible for handling the output display
 * of the task management application. It provides methods to print
 * various messages, including task lists, welcome messages, and user
 * input feedback.
 */
public class OutputManager {

    /**
     * Prints the number of tasks currently in the list.
     *
     * @param numberOfTasks the total number of tasks in the list
     */
    public static void printNumberOfTasks(int numberOfTasks) {
        System.out.println(Constants.INDENTATION + "Now you have " + numberOfTasks + " tasks in the list.");
    }

    /**
     * Prints the list of tasks along with their indices.
     *
     * @param tasksList    the list of tasks to be printed
     * @param numberOfTasks the total number of tasks in the list
     */
    public static void printTasksList(ArrayList<Task> tasksList, int numberOfTasks) {
        System.out.println(Constants.INDENTATION + Messages.MESSAGE_TASK_LIST);
        for (int i = 0; i < numberOfTasks; i++) {
            System.out.println(Constants.INDENTATION + Integer.toString(i + 1) + ". " + tasksList.get(i).toString());
        }
        OutputManager.printNumberOfTasks(numberOfTasks);
    }

    public static void printWelcomeMessage() {
        System.out.println(Messages.MESSAGE_WELCOME);
    }

    public static void printExitMessage() {
        System.out.println(Messages.MESSAGE_EXIT);
    }

    public static void printLineBreak() {
        System.out.print(Constants.LINE_BREAK);
    }

    /**
     * Prints a custom message with indentation.
     *
     * @param message the message to be printed
     */
    public static void printMessage(String message) {
        System.out.println(Constants.INDENTATION + message);
    }

    /**
     * Prints a message indicating a task has been added.
     *
     * @param taskInfo information about the added task
     */
    public static void printMessageAddedTask(String taskInfo) {
        System.out.println(Constants.INDENTATION + Messages.MESSAGE_ADDED + Constants.INDENTATION + taskInfo);
    }

    /**
     * Prints a message indicating a task has been deleted.
     *
     * @param taskInfo information about the deleted task
     */
    public static void printMessageDeletedTask(String taskInfo) {
        System.out.println(Constants.INDENTATION + Messages.MESSAGE_DELETED + Constants.INDENTATION + taskInfo);
    }

    public static void printListCommands() {
        for (String command : Constants.LIST_COMMANDS) {
            System.out.println(command);
        }
        System.out.println(Constants.LINE_BREAK);
    }
}
