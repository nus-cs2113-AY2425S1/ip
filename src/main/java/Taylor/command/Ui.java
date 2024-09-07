package Taylor.command;

import Taylor.task.Task;
import Taylor.task.TaskList;

import java.util.Scanner;

public class Ui {
    private static final String LINE = "____________________________________________________________";
    private final Scanner sc;

    public Ui() {
        sc = new Scanner(System.in);
    }

    public void showWelcome() {
        System.out.println(LINE);
        System.out.println("Hello! I'm Taylor.command.Taylor");
        System.out.println("What can I do for you?");
        System.out.println(LINE);
    }

    public void showGoodbye() {
        System.out.println(LINE);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(LINE);
    }

    public void showLine() {
        System.out.println(LINE);
    }

    public void showLoadingError() {
        System.out.println("Unable to load the file.");
    }

    public String readCommand() {
        return sc.nextLine();
    }

    public void showTasks(TaskList tasks) {
        System.out.println(LINE);
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(i + 1 + "." + tasks.get(i));
        }
        System.out.println(LINE);
    }

    public void showTaskAdded(Task task, TaskList tasks) {
        System.out.println(LINE);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        System.out.println(LINE);
    }

    public void showTaskDeleted(String task, TaskList tasks) {
        System.out.println(LINE);
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        System.out.println(LINE);
    }

    public void println(String message){
        System.out.println(LINE);
        System.out.println(message);
        System.out.println(LINE);
    }
}