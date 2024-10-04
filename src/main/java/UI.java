import java.util.List;
import java.util.Scanner;

public class UI {
    private Scanner scanner;

    public UI() {
        scanner = new Scanner(System.in);
    }

    public void showWelcomeMessage() {
        System.out.println("Hello! I'm ANDY");
    }

    public String getUserInput() {
        return scanner.nextLine();
    }

    public void showGoodbyeMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void showFoundTasks(List<Task> tasks) {
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }
    public void showTaskAddedMessage(String description) {
        System.out.println("Added: " + description);
    }

    public void showTaskDeletedMessage(String description) {
        System.out.println("Deleted: " + description);
    }

    public void showTaskList(TaskList tasks) {
        if (tasks.isEmpty()) {
            System.out.println("The list is empty.");
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ". " + tasks.getTask(i));
            }
        }
    }

    public void showErrorMessage(String message) {
        System.out.println("Error: " + message);
    }
}