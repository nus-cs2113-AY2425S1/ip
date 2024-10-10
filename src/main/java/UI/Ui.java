package UI;

import java.util.Scanner;
import task.Task;
import task.TaskList;

/**
 * Handles interactions with the user.
 */
public class Ui {
    private static final String INDENTATION = "    ";

    public void showWelcome() {
        String LOGO = """
                      ██▓     ██▓ ▄▄▄
                      ▓██▒    ▓██▒▒████▄
                      ▒██░    ▒██▒▒██  ▀█▄
                      ▒██░    ░██░░██▄▄▄▄██
                      ░██████▒░██░ ▓█   ▓██▒
                      ░ ▒░▓  ░░▓   ▒▒   ▓▒█░
                      ░ ░ ▒  ░ ▒ ░  ▒   ▒▒ ░
                      ░ ░    ▒ ░  ░   ▒
                          ░  ░ ░        ░  ░
                      """;
        System.out.println(INDENTATION + "Hello! I'm \n" + LOGO);
        System.out.println(INDENTATION + "What can I do for you?");
        printLine();
    }

    public String readCommand() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine().trim();
    }

    public void printLine() {
        System.out.println(INDENTATION + "___________________________________________________________");
    }

    public void addTaskAndPrint(Task task, int taskCount) {
        System.out.println(INDENTATION + "Got it. I've added this task:");
        System.out.println(INDENTATION + task.toString());
        System.out.println(INDENTATION + "Now you have " + taskCount + " tasks in the list.");
    }

    public void showDeleteTask(Task task, int taskCount) {
        System.out.println(INDENTATION + "Noted. I've removed this task:");
        System.out.println(INDENTATION + task.toString());
        System.out.println(INDENTATION + "Now you have " + taskCount + " tasks in the list.");
    }

    public void printTasks(TaskList tasks) {
        if (tasks.isEmpty()) {
            System.out.println(INDENTATION + "No tasks found.");
        } else {
            System.out.println(INDENTATION + "Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println(INDENTATION + (i + 1) + "." + tasks.get(i).toString());
            }
        }
    }

    public void showMarkDone(Task task) {
        System.out.println(INDENTATION + "Nice! I've marked this task as done:");
        System.out.println(INDENTATION + task.toString());
    }

    public void showUnmarkDone(Task task) {
        System.out.println(INDENTATION + "OK, I've unmarked this task:");
        System.out.println(INDENTATION + task.toString());
    }

    public void showFarewell(){
        System.out.println(INDENTATION + "Bye. Hope to see you again soon!");
    }

    public void showError(String message) {
        System.out.println(INDENTATION + message);
    }

    public void showLoadingError() {
        System.out.println(INDENTATION + "Error loading tasks from file.");
    }
}
