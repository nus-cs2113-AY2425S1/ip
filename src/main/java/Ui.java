import java.util.Scanner;
import java. util. ArrayList;

public class Ui {

    public static final String HORIZONTAL_LINE = "---------------------------------------------------------------";
    private final Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }

    public void showWelcomeMessage() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Hello! I'm Eva!");
        System.out.println("What can I do for you?");
        System.out.println(HORIZONTAL_LINE);
    }

    public void showGoodbyeMessage() {
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(HORIZONTAL_LINE);
    }

    public void showErrorMessage(String message) {
        System.out.println(message);
        System.out.println(HORIZONTAL_LINE);
    }

    public void showMessage(String message) {
        System.out.println(message);
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void showTaskList(String taskList) {
        System.out.println(taskList);
        System.out.println(HORIZONTAL_LINE);
    }

    public void closeScanner() {
        scanner.close();
    }

    public void printTaskList(ArrayList<Task> tasks) {
        if (tasks.isEmpty()) {
            showMessage("Your task list is currently empty.");
            return;
        }

        showMessage("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            showMessage((i + 1) + ". " + tasks.get(i).toString());
        }
        showMessage(HORIZONTAL_LINE);
    }
}
