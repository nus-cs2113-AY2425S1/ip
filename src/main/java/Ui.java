import java.util.Scanner;
public class Ui {
    private static final String SEPARATOR = "____________________________________________________________";

    public void greetUser() {
        printSeparator();
        System.out.println("Hello! I'm KBot");
        System.out.println("What can I do for you?");
        printSeparator();
    }

    public void printSeparator() {
        System.out.println(SEPARATOR);
    }

    public void showError(String errorMessage) {
        printSeparator();
        System.out.println(errorMessage);
        printSeparator();
    }

    public void exit() {
        printSeparator();
        System.out.println("Bye. Hope to see you again soon!");
        printSeparator();
    }

    public void printTaskAddedMessage(Task task, int taskCount) {
        printSeparator();
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + taskCount + " tasks in the list.");
        printSeparator();
    }

    public void printMarkDoneMessage(Task task) {
        printSeparator();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + task);
        printSeparator();
    }

    public void printMarkNotDoneMessage(Task task) {
        printSeparator();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("  " + task);
        printSeparator();
    }

    public void printDeleteTaskMessage(Task task, int taskCount) {
        printSeparator();
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + taskCount + " tasks in the list.");
        printSeparator();
    }

    public String getUserInput(Scanner scanner) {
        return scanner.nextLine();
    }
}
