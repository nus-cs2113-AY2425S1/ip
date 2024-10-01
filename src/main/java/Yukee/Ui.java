package Yukee;
import java.util.Scanner;

import Yukee.task.Task;
import java.util.ArrayList;

public class Ui {
    private final Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void showWelcome() {
        String logo = """
                 Y   Y  U   U  K   K  EEEEE  EEEEE
                  Y Y   U   U  K  K   E      E
                   Y    U   U  KKK    EEEE   EEEE
                   Y    U   U  K  K   E      E
                   Y     UUU   K   K  EEEEE  EEEEE
                """;
        System.out.println("Hello! I'm Yukee, your friendly assistant!\n" + logo);
    }

    public void showLoadingError() {
        System.out.println("No existing data found. Starting with an empty task list.");
    }

    public void showGoodbye() {
        System.out.println("Bye! Hope to see you again soon!");
    }

    public void showLine() {
        System.out.println("____________________________________________");
    }

    public void showError(String message) {
        System.out.println("OOPS!!! " + message);
    }

    public String readCommand() {
        System.out.print("Enter command: ");
        return scanner.nextLine().trim();
    }

    public void showAddTask(Task task, int size) {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + size + " tasks in the list.");
    }

    public void showMarkTask(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + task);
    }

    public void showUnmarkTask(Task task) {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("  " + task);
    }

    public void showDeleteTask(Task task, int size) {
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + size + " tasks in the list.");
    }

    public void showHelp() {
        System.out.println("Here are the commands you can use:");
        System.out.println("1. 'list' - List all tasks.");
        System.out.println("2. 'mark <task_number>' - Mark a task as done.");
        System.out.println("3. 'unmark <task_number>' - Mark a task as not done.");
        System.out.println("4. 'todo <task_description>' - Add a todo task.");
        System.out.println("5. 'deadline <task_description> /by <time>' - Add a deadline task.");
        System.out.println("6. 'event <task_description> /from <start_time> /to <end_time>' - Add an event task.");
        System.out.println("7. 'delete <task_number>' - Delete a task from the list.");
        System.out.println("8. 'help' - Show this help message.");
        System.out.println("9. 'bye' - Exit the program.");
    }

    public void showFoundTasks(ArrayList<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println("No matching tasks found.");
        } else {
            System.out.println("Here are the matching tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ". " + tasks.get(i));
            }
        }
    }
}
