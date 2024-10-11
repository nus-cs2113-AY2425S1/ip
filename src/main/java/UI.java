import task.Task;

import java.util.List;
import java.util.Scanner;

public class UI {
    public static void displayTasks(List<Task> tasks) {
        System.out.println("____________________________________________________________");
        for(int i = 0; i < tasks.size(); i++) {
            System.out.println(i+1 + "." + tasks.get(i));
        }
        System.out.println("____________________________________________________________");
    }

    static void displayTaskMarked(Task task) {
        System.out.println("____________________________________________________________");
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(" " + task);
        System.out.println("____________________________________________________________");
    }

    static void displayTaskUnmarked(Task task) {
        System.out.println("____________________________________________________________");
        System.out.println("Ok, I've marked this task as not done yet:");
        System.out.println(" " + task);
        System.out.println("____________________________________________________________");
    }

    static void displayTaskAdded(List<Task> tasks, Task task) {
        System.out.println("____________________________________________________________");
        System.out.println("Got it. I've added this task:");
        System.out.println(" " + task);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        System.out.println("____________________________________________________________");
    }

    static void displayTaskDeleted(List<Task> tasks, Task task) {
        System.out.println("____________________________________________________________");
        System.out.println("Noted. I've removed this task:");
        System.out.println(" " + task);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        System.out.println("____________________________________________________________");
    }

    static void displayLoadSuccessMessage() {
        System.out.println("Loaded tasks from file successfully.");
    }

    static Scanner readCommand() {
        Scanner in = new Scanner(System.in);
        return in;
    }

    static void displayWelcomeMessage() {
        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm Flash");
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");
    }

    static void displayByeMessage() {
        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }
}
