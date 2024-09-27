package bob.ui;
import bob.task.Task;
import bob.task.TaskList;
import java.util.Scanner;

public class Ui {

    public static final String SEPARATOR = "____________________________________________________________";

    public void showWelcome() {
        String logo = """
              ____        _
             | |_) \\ ___ | |___
             |  _ //  _  \\   _ \\
             | |_)\\\\ (_) /  |_) |
             |____/ \\___/|_|___/
            """;

        System.out.println(logo);
        System.out.println("Hello! I'm Bob");
        System.out.println("What can I do for you?");
    }

    public String readCommand() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public void showSeparatorLine() {
        System.out.println(SEPARATOR);
    }

    public void printEmptyListMessage() {
        System.out.println("Sorry! Your list is empty.");
    }

    public void printTaskAlreadyDoneMessage() {
        System.out.println("Sorry! This task is already marked as done.");
    }

    public void showMarkTaskMessage(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + task);
    }

    public void printInvalidTaskNumberMessage(TaskList tasks) {
        System.out.println("Please input a valid task number from 1 to " + tasks.getSize() + ".");
    }

    public void printInvalidInputTypeMessage() {
        System.out.println("The input task number must be an integer.");
    }

    public void printTaskAlreadyUnmarkedMessage() {
        System.out.println("Sorry! This task is already unmarked.");
    }

    public void showUnmarkTaskMessage(Task task) {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(task);
    }

    public void printEmptyDescription(String taskType) {
        System.out.println("Sorry! The description of " + taskType + " cannot be empty.");
    }

    public void printAddedTask (TaskList tasks) {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + tasks.getTask(tasks.getSize() - 1));
        System.out.println("Now you have " + tasks.getSize() + " " + (tasks.getSize() == 1 ? "task" : "tasks") + " in the list.");
    }

    public void printInvalidDeadline() {
        System.out.println("Sorry! Please provide a valid deadline with '/by <date/time>'.");
    }

    public void printInvalidEvent() {
        System.out.println("Sorry! Please provide a valid event with '/from <start date/time> /to <end date/time>'.");
    }

    public void showDeleteTaskMessage(Task removedTask, int size) {
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + removedTask);
        System.out.println("Now you have " + size + (size == 1 ? " task" : " tasks") + " in the list.");
    }

    public void showExitMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void printErrorParsingLineMessage(int lineNumber, String message) {
        System.out.println("Error parsing line " + lineNumber + ": " + message);
    }

    public void printFileNotFound(String message) {
        System.out.println("File not found: " + message);
    }

    public void printCreationMessage(String itemType, boolean success) {
        if (!success) {
            System.out.println(itemType + " failed to be created.");
            return;
        }
        System.out.println("Creating a new " + itemType + " because " + itemType + " has not existed yet.");
    }

    public void printErrorSavingTasks(String message) {
        System.out.println("An error occurred while saving the tasks: " + message);
    }

    public void printErrorAppendingTask(String message) {
        System.out.println("An error occurred while appending the task: " + message);
    }

    public void printInvalidCommand() {
        System.out.println("Sorry! This is an invalid command. I don't understand what you mean.");
    }
}