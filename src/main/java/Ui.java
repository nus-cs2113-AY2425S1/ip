package main.java;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private final Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    public void start() {
        printLine();
        System.out.println("Hello! I'm KenChat");
        System.out.println("What can I do for you?");
        System.out.println("Use <help> to show full list of commands.");
        printLine();
        System.out.println();
    }

    public void end() {
        printLine();
        System.out.println("Bye. Hope to see you again soon!");
        printLine();
        System.out.println();
    }

    public void printLine() {
        System.out.println("____________________________________");
    }

    public String readCommand() {
        return sc.nextLine();
    }

    public void showError(String message) {
        printLine();
        System.out.println(message);
        printLine();
        System.out.println();
    }

    public void showLoadingError() {
        showError("Error loading data from file.");
    }

    public void showTaskAdded(Task task, int size) {
        printLine();
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + size + " tasks in the list.");
        printLine();
        System.out.println();
    }

    public void showTaskRemoved(Task task, int size) {
        printLine();
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + size + " tasks in the list.");
        printLine();
        System.out.println();
    }

    public void showTaskMarked(Task task) {
        printLine();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + task);
        printLine();
        System.out.println();
    }

    public void showTaskUnmarked(Task task) {
        printLine();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("  " + task);
        printLine();
        System.out.println();
    }

    public void showTasksList(ArrayList<Task> tasks) {
        printLine();
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
        printLine();
        System.out.println();
    }

    public void showHelp() {
        printLine();
        System.out.println("Valid commands:");
        System.out.println("1. todo <description> - Adds a todo task.");
        System.out.println("2. deadline <description> /by <date> - Adds a deadline task. <date> can be date and/or time.");
        System.out.println("3. event <description> /from <date> /to <date> - Adds an event task.  <date> can be date and/or time.");
        System.out.println("4. list - Displays all tasks in the list.");
        System.out.println("5. mark <task number> - Marks the specified task as done.");
        System.out.println("6. unmark <task number> - Marks the specified task as not done.");
        System.out.println("7. delete <task number> - Deletes the specified task from the list.");
        System.out.println("8. find <keyword> - Find task(s) in the list by searching with keyword(s).");
        System.out.println("9. bye - Exits the program.");
        System.out.println("10. help - Shows this help message.");
        printLine();
        System.out.println();
    }

    public void showMessage(String message) {
        System.out.println(message);
    }
}