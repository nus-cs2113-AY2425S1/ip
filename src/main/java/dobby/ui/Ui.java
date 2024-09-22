package dobby.ui;

import dobby.exceptions.MissingDescriptionException;
import dobby.exceptions.EmptyListException;
import dobby.exceptions.IllegalInputException;
import dobby.exceptions.TaskAlreadyMarkedException;
import dobby.exceptions.TaskAlreadyUnmarkedException;
import dobby.tasks.Task;
import dobby.tasklist.TaskList;

public class Ui {

    private static final String DASH_LINE = "____________________________________________________________";

    public static void printWelcomeMessage() {
        printSeparator();
        System.out.println("    " + "Hello! Dobby is Dobby!");
        System.out.println("    " + "What can Dobby do for master?");
        printSeparator();
    }

    public static void printTaskAddedMessage(Task task, int taskListSize) {
        printSeparator();
        System.out.println("    Dobby has added this task:");
        System.out.println("      " + task);
        System.out.println("    Dobby says master has " + taskListSize + " tasks in the list!");
        printSeparator();
    }

    public static void printGoodbyeMessage() {
        printSeparator();
        System.out.println("    " + "Thank you master, Dobby is free!!!");
        printSeparator();
    }

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

    public static void printList(TaskList taskList) throws EmptyListException {
        if (taskList.isEmpty()) {
            throw new EmptyListException();
        }

        printSeparator();
        System.out.println("    Here are the tasks in master's list:");
        for (int i = 1; i <= taskList.size(); i++) {
            Task t = taskList.get(i - 1);
            System.out.println("    " + i + "." + t);
        }
        printSeparator();
    }

    public static void handleExceptions(Exception exception) {
        printSeparator();
        if (exception instanceof MissingDescriptionException) {
            System.out.println("    Dobby thinks master should add a description here!");
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
        }
        printSeparator();
    }

    public static void printSeparator() {
        System.out.println("  " + DASH_LINE);
    }
}
