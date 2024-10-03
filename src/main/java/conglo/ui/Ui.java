package conglo.ui;

import conglo.task.Task;
import conglo.task.TaskList;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles user interactions for task managing.
 */
public class Ui {

    private static final String LINE_SEPARATOR = "-----------------------------" +
            "-----------------------------";
    private Scanner scanner;

    /**
     * Constructs a Ui object, initializing the scanner for user input.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Retrieves user input from the console.
     *
     * @return The input string from the user.
     */
    public String getUserInput() {
        return scanner.nextLine();
    }

    /**
     * Prints a line separator to the console.
     */
    public void printLineSeparator() {
        printText(LINE_SEPARATOR);
    }

    /**
     * Greets the user and displays the manual.
     */
    public void greetUser() {
        printLineSeparator();
        printText("Hola! I'm Conglo, the friendly task manager.");
        printLineSeparator();
        Manual.printManual();
        printLineSeparator();
        displayTaskList();
        printLineSeparator();
    }

    /**
     * Displays a message indicating that a task has been removed.
     */
    public void displayRemoved() {
        printText("Okie! Task is removed from list:");
    }

    /**
     * Displays a message indicating that a task has been added.
     */
    public void displayAdded() {
        printText("All done! Task added to list:");
    }

    /**
     * Displays a message indicating that the task list is empty.
     */
    public void displayEmptyList() {
        printText("The list is empty!");
    }

    /**
     * Displays the current task list.
     * If the list is empty, it shows a corresponding message.
     */
    public void displayTaskList() {
        if (TaskList.isEmpty()) {
            displayEmptyList();
        } else {
            printText("Here are your tasks:");
            TaskList.listTasks();
        }
    }

    /**
     * Displays tasks with matching keyword that user provided.
     *
     * @param keyword Used to match words in descriptions.
     */
    public void displayFoundTasks(String keyword) {
        ArrayList<Task> matchingTasks = TaskList.findTasks(keyword);
        if (matchingTasks.isEmpty()) {
            System.out.println("No task matches the keyword :(");
        } else {
            System.out.println("Tasks matched in your list:");
            for (int i = 0; i < matchingTasks.size(); i++) {
                System.out.println((i + 1) + ". " + matchingTasks.get(i).toFileFormat());
            }
        }
    }

    /**
     * Displays an error message when there is an issue loading tasks.
     */
    public void displayLoadingError() {
        printText("Oops! There was an error loading your tasks. Please try again.");
    }

    /**
     * Displays a goodbye message to the user.
     */
    public void sayGoodbye() {
        printText("Goodbye. See you next time!");
    }

    /**
     * Prints a specified message to the console.
     */
    public void printText(String message) {
        System.out.println(message);
    }

    /**
     * Closes the scanner to prevent resource leaks.
     */
    public void closeScanner() {
        scanner.close();
    }
}
