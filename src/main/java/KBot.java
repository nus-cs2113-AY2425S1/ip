import java.util.ArrayList;
import java.util.Scanner;

public class KBot {
    // Define a constant for the separator line
    private static final String SEPARATOR = "____________________________________________________________";

    private ArrayList<Task> tasks;
    private Scanner scanner;

    public KBot() {
        tasks = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    public void run() {
        greetUser();

        while (true) {
            String input = getUserInput();
            String[] inputParts = input.split(" ", 2); // Split input into command and arguments
            String command = inputParts[0];

            switch (command) {
                case "bye":
                    exit();
                    return;

                case "list":
                    listTasks();
                    break;

                case "mark":
                    if (inputParts.length > 1) {
                        markTaskAsDone(Integer.parseInt(inputParts[1]) - 1);
                    }
                    break;

                case "unmark":
                    if (inputParts.length > 1) {
                        markTaskAsNotDone(Integer.parseInt(inputParts[1]) - 1);
                    }
                    break;

                case "todo":
                    if (inputParts.length > 1) {
                        addTodo(inputParts[1]);
                    }
                    break;

                case "deadline":
                    if (inputParts.length > 1) {
                        addDeadline(inputParts[1]);
                    }
                    break;

                case "event":
                    if (inputParts.length > 1) {
                        addEvent(inputParts[1]);
                    }
                    break;

                default:
                    System.out.println("Unknown command.");
                    break;
            }
        }
    }

    private void greetUser() {
        System.out.println(SEPARATOR);
        System.out.println("Hello! I'm KBot");
        System.out.println("What can I do for you?");
        System.out.println(SEPARATOR);
    }

    private String getUserInput() {
        return scanner.nextLine();
    }

    private void addTodo(String description) {
        Task task = new Todo(description);
        tasks.add(task);
        System.out.println(SEPARATOR);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        System.out.println(SEPARATOR);
    }

    private void addDeadline(String input) {
        String[] parts = input.split(" /by ");
        Task task = new Deadline(parts[0], parts[1]);
        tasks.add(task);
        System.out.println(SEPARATOR);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        System.out.println(SEPARATOR);
    }

    private void addEvent(String input) {
        String[] parts = input.split(" /from | /to ");
        Task task = new Event(parts[0], parts[1], parts[2]);
        tasks.add(task);
        System.out.println(SEPARATOR);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        System.out.println(SEPARATOR);
    }

    private void listTasks() {
        System.out.println(SEPARATOR);
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i));
        }
        System.out.println(SEPARATOR);
    }

    private void markTaskAsDone(int index) {
        if (index >= 0 && index < tasks.size()) {
            tasks.get(index).markAsDone();
            System.out.println(SEPARATOR);
            System.out.println("Nice! I've marked this task as done:");
            System.out.println("  " + tasks.get(index));
            System.out.println(SEPARATOR);
        }
    }

    private void markTaskAsNotDone(int index) {
        if (index >= 0 && index < tasks.size()) {
            tasks.get(index).markAsNotDone();
            System.out.println(SEPARATOR);
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println("  " + tasks.get(index));
            System.out.println(SEPARATOR);
        }
    }

    private void exit() {
        System.out.println(SEPARATOR);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(SEPARATOR);
    }

    public static void main(String[] args) {
        KBot bot = new KBot();
        bot.run();
    }
}
