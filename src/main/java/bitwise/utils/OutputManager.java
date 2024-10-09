package bitwise.utils;

import bitwise.constants.Constants;
import bitwise.constants.Messages;
import bitwise.tasks.Task;

import java.util.ArrayList;

public class OutputManager {

    public static void printNumberOfTasks(int numberOfTasks) {
        System.out.println(Constants.INDENTATION + "Now you have " + numberOfTasks + " tasks in the list.");
    }

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

    public static void printMessage(String message) {
        System.out.println(Constants.INDENTATION + message);
    }

    public static void printMessageAddedTask(String taskInfo) {
        System.out.println(Constants.INDENTATION + Messages.MESSAGE_ADDED + Constants.INDENTATION + taskInfo);
    }

    public static void printMessageDeletedTask(String taskInfo) {
        System.out.println(Constants.INDENTATION + Messages.MESSAGE_DELETED + Constants.INDENTATION + taskInfo);
    }

    public static void printEchoInput(String input) {
        System.out.print(Constants.LINE_BREAK + Constants.INDENTATION + input + Constants.LINE_BREAK);
    }

    public static void printListCommands() {
        for (String command : Constants.LIST_COMMANDS) {
            System.out.println(command);
        }
        System.out.println(Constants.LINE_BREAK);
    }
}
