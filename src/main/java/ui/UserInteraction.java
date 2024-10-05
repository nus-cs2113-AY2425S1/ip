package ui;

import task.TaskList;
import java.util.Scanner;

public class UserInteraction {
    private final Scanner scanner = new Scanner(System.in);

    public void showWelcomeMessage() {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("           Welcome to Jer Chat Bot!                       ");
        System.out.println("           What can I do for you today?                   ");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    public void showExitMessage() {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println(" End of Program");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    public String readCommand() {
        return scanner.nextLine().trim();
    }

    public void printDividerLine() {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    public void showMessage(String message) {
        System.out.println(message);
    }

    public void showLoadingError() {
        System.out.println("Error loading the task list from file.");
    }

    public void showTaskList(TaskList tasks) {
        printDividerLine();
        System.out.println(" Current Task List:");
        if (tasks.isEmpty()) {
            System.out.println(" No tasks in your list currently.");
        } else {
            for (int i = 0; i < tasks.getSize(); i++) {
                System.out.printf("%d. %s%n", i + 1, tasks.getTask(i));
            }
        }
        printDividerLine();
    }
}
