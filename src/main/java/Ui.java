import java.util.Scanner;

public class Ui {
    private static final String LINE = "____________________________________________________________\n";

    public void showLine() {
        System.out.println(LINE);
    }

    public void showWelcome() {
        showLine();
        System.out.println("Hello! I'm POIROT\nWhat can I do for you?");
        showLine();
    }

    public void showExit() {
        showLine();
        System.out.println("Bye. Hope to see you again soon!");
        showLine();
    }

    public String readCommand() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public void showError(String message) {
        showLine();
        System.out.println(message);
        showLine();
    }

    public void showAddedTask(Task task, int taskCount) {
        showLine();
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + taskCount + " tasks in the list.");
        showLine();
    }

    public void showDeletedTask(Task task, int taskCount) {
        showLine();
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + taskCount + " tasks in the list.");
        showLine();
    }

    public void showTaskList(Task[] tasks, int lastIndex) {
        showLine();
        if (lastIndex == 0) {
            System.out.println("No actions available");
        } else {
            for (int i = 0; i < lastIndex; i++) {
                System.out.println((i + 1) + "." + tasks[i]);
            }
        }
        showLine();
    }

    public void showMarkTask(Task task) {
        showLine();
        System.out.println("Nice! I've marked this task as done:\n");
        System.out.print("[" + task.getStatusIcon() + "] ");
        System.out.println(task.getDescription());
        showLine();
    }

    public void showUnmarkTask(Task task) {
        showLine();
        System.out.println("OK, I've marked this task as not done yet:\n");
        System.out.print("[" + task.getStatusIcon() + "] ");
        System.out.println(task.getDescription());
        showLine();
    }
}
