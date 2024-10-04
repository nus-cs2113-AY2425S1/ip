package Ryan.utility;

import Ryan.tasks.Task;
import java.util.Scanner;
import java.util.ArrayList;

public class Ui {
    private Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }

    public static void printGreeting() {
        Ui.horizontalLine();
        System.out.println("Hello! I'm Ryan.\nWhat can I do for you?");
        Ui.horizontalLine();
    }

    public static void printGoodbye() {
        Ui.horizontalLine();
        System.out.println("Goodbye! Have a great day.");
        Ui.horizontalLine();
    }

    public static void showError(String message) {
        Ui.horizontalLine();
        System.out.println("Error: " + message);
        Ui.horizontalLine();
    }

    public static void showTaskAdded(Task task, int totalTasks) {
        Ui.horizontalLine();
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + totalTasks + " tasks in the list.");
        Ui.horizontalLine();
    }

    public static void showTaskDeleted(Task task, int totalTasks) {
        Ui.horizontalLine();
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + totalTasks + " tasks in the list.");
        Ui.horizontalLine();
    }

    public static void showTaskMarked(Task task) {
        Ui.horizontalLine();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + task);
        Ui.horizontalLine();
    }

    public static void showTaskUnmarked(Task task) {
        Ui.horizontalLine();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("  " + task);
        Ui.horizontalLine();
    }
    
    public static void showFoundTasks(ArrayList<Task> tasks) {
        Ui.horizontalLine();
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
        Ui.horizontalLine();
    }

    public static void printTasks(ArrayList<Task> tasks) {
        Ui.horizontalLine();
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
        Ui.horizontalLine();
    }

    public static void horizontalLine() {
        System.out.println("_________________________________________________________");
    }

}
