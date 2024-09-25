package ui;

import model.Task;
import tasklist.TaskList;

import java.util.Scanner;

public class Ui {

    private static final String LOGO = " __  __                 _             \n"
            + "|  \\/  | ___  _ __   __| | __ _ _   _ \n"
            + "| |\\/| |/ _ \\| '_ \\ / _ |/ _ | | | |\n"
            + "| |  | | (_) | | | | (_| | (_| | |_| |\n"
            + "|_|  |_|\\___/|_| |_|\\__,_|\\__,_|\\__, |\n"
            + "                                |___/  \n";

    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void printWelcomeMessage() {
        System.out.println("Hello from\n" + LOGO);
        printLine();
        System.out.println("    Hello! I'm MONDAY\n    What can I do for you?");
        printLine();
    }

    public void showTaskAdded(Task task, int size) {
        System.out.println("    Added: " + task);
        System.out.println("    Now you have " + size + " tasks in the list.");
    }

    public void showTaskList(TaskList tasks) {
        System.out.println("    Here are your tasks:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("    " + (i + 1) + ". " + tasks.get(i));
        }
    }

    public void showError(String message) {
        System.out.println("    Error: " + message);
    }

    public void showTaskRemoved(Task task, int size) {
        System.out.println("    Removed: " + task);
        System.out.println("    Now you have " + size + " tasks in the list.");
    }

    public void showTaskMarkedDone(Task task) {
        System.out.println("    Nice! I've marked this task as done:");
        System.out.println("    " + task);
    }

    public void showTaskUnmarked(Task task) {
        System.out.println("    Nice! I've marked this task as not done:\n  " + task.toString());
    }

    public void showLoadingError() {
        System.out.println("    Error loading tasks from file. Starting with an empty task list.");
    }

    public String getInput() {
        return scanner.nextLine();
    }

    public void showExitMessage() {
        System.out.println("    Exiting the application. Goodbye!");
    }
    public void printLine() {
        System.out.println("_____________________________________________");
    }
}
