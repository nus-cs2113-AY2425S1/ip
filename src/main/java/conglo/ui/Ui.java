package conglo.ui;

import conglo.task.Task;
import conglo.task.TaskList;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private static final String LINE_SEPARATOR = "-----------------------------" +
            "-----------------------------";

    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public String getUserInput() {
        return scanner.nextLine();
    }

    public void printLineSeparator() {
        printText(LINE_SEPARATOR);
    }

    public void greetUser() {
        printLineSeparator();
        printText("Hola! I'm Conglo, the friendly task manager.");
        printLineSeparator();
        Manual.printManual();
        printLineSeparator();
        displayTaskList();
        printLineSeparator();
    }

    public void displayRemoved() {
        printText("Okie! Task is removed from list:");
    }

    public void displayAdded() {
        printText("All done! Task added to list:");
    }

    public void displayEmptyList() {
        printText("The list is empty!");
    }

    public void displayTaskList() {
        if (TaskList.isEmpty()) {
            displayEmptyList();
        } else {
            printText("Here are your tasks:");
            TaskList.listTasks();
        }
    }

    public void displayLoadingError() {
        printText("Oops! There was an error loading your tasks. Please try again.");
    }

    public void sayGoodbye() {
        printText("Goodbye. See you next time!");
    }

    public void printText(String message) {
        System.out.println(message);
    }

    public void closeScanner() {
        scanner.close();
    }
}
