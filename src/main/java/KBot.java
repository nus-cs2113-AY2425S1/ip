import java.util.ArrayList;
import java.util.Scanner;

public class KBot {
    private static final String SEPARATOR = "____________________________________________________________";
    private ArrayList<Task> tasks;
    private Scanner scanner;

    public KBot() {
        tasks = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    public static void main(String[] args) {
        KBot bot = new KBot();
        bot.run();
    }

    // High-level logic for running the bot
    public void run() {
        greetUser();

        boolean isRunning = true;
        while (isRunning) {
            String userInput = getUserInput();
            isRunning = handleCommand(userInput);
        }

        exit();
    }

    // Handles user commands and returns false if the bot should stop running
    private boolean handleCommand(String input) {
        String[] inputParts = input.split(" ", 2);
        String command = inputParts[0];
        String argument = (inputParts.length > 1) ? inputParts[1] : "";

        switch (command) {
            case "bye":
                return false;
            case "list":
                listTasks();
                break;
            case "mark":
                markTaskAsDone(Integer.parseInt(argument) - 1);
                break;
            case "unmark":
                markTaskAsNotDone(Integer.parseInt(argument) - 1);
                break;
            case "todo":
                addTodoTask(argument);
                break;
            case "deadline":
                addDeadlineTask(argument);
                break;
            case "event":
                addEventTask(argument);
                break;
            default:
                showUnknownCommandMessage();
                break;
        }
        return true;
    }

    // Utility Methods

    // Greet the user at the start
    private void greetUser() {
        printSeparator();
        System.out.println("Hello! I'm KBot");
        System.out.println("What can I do for you?");
        printSeparator();
    }

    // Display a separator line
    private void printSeparator() {
        System.out.println(SEPARATOR);
    }

    // Get user input
    private String getUserInput() {
        return scanner.nextLine();
    }

    // Add a todo task
    private void addTodoTask(String description) {
        Task task = new Todo(description);
        tasks.add(task);
        printTaskAddedMessage(task);
    }

    // Add a deadline task
    private void addDeadlineTask(String input) {
        String[] parts = input.split(" /by ");
        Task task = new Deadline(parts[0], parts[1]);
        tasks.add(task);
        printTaskAddedMessage(task);
    }

    // Add an event task
    private void addEventTask(String input) {
        String[] parts = input.split(" /from | /to ");
        Task task = new Event(parts[0], parts[1], parts[2]);
        tasks.add(task);
        printTaskAddedMessage(task);
    }

    // List all tasks
    private void listTasks() {
        printSeparator();
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i));
        }
        printSeparator();
    }

    // Mark a task as done
    private void markTaskAsDone(int index) {
        tasks.get(index).markAsDone();
        printMarkDoneMessage(tasks.get(index));
    }

    // Mark a task as not done
    private void markTaskAsNotDone(int index) {
        tasks.get(index).markAsNotDone();
        printMarkNotDoneMessage(tasks.get(index));
    }

    // Show a message for unknown commands
    private void showUnknownCommandMessage() {
        printSeparator();
        System.out.println("Unknown command.");
        printSeparator();
    }

    // Print exit message
    private void exit() {
        printSeparator();
        System.out.println("Bye. Hope to see you again soon!");
        printSeparator();
    }

    // Print helper messages
    private void printTaskAddedMessage(Task task) {
        printSeparator();
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        printSeparator();
    }

    private void printMarkDoneMessage(Task task) {
        printSeparator();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + task);
        printSeparator();
    }

    private void printMarkNotDoneMessage(Task task) {
        printSeparator();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("  " + task);
        printSeparator();
    }
}