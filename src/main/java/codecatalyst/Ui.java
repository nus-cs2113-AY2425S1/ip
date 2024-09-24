package codecatalyst;

import codecatalyst.task.Task;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void printDivider() {
        System.out.println("        ________________________________________________________\n");
    }

    public void printGreeting() {
        printDivider();
        System.out.println("         Hello, I'm CodeCatalyst. ");
        System.out.println("         What can I do for you?");
        printDivider();
    }

    public void printGoodbye() {
        System.out.println("         Bye. Hope to see you again soon!");
    }

    public void printError(String errormessage) {
        System.out.println("Error: " + errormessage);
    }

    public void printLoadingError() {
        System.out.println("Error loading tasks from the file");
    }

    public void printTaskAdded(Task task, int size) {
        System.out.println("         Got it. I've added this task:");
        System.out.println("         " + task);
        System.out.println("         Now you have " + size + " tasks in the list.");
    }

    public void printTaskMarked(Task task) {
        System.out.println("         Nice! I've marked this task as done:");
        System.out.println("         " + task);
    }

    public void printTaskUnmarked(Task task) {
        System.out.println("         Nice! I've unmarked this task as done:");
        System.out.println("         " + task);
    }

    public void printTaskDeleted(Task task, int size) {
        System.out.println("         Noted. I've removed this task:");
        System.out.println("           " + task);
        System.out.println("         Now you have " + size + " tasks in the list.");
    }


    public void printTaskList(ArrayList<Task> tasks) {
        System.out.println("         Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("         " + (i + 1) + ". " + tasks.get(i));
        }
    }



}
