import java.util.ArrayList;
import java.util.Scanner;


class Task {
    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    @Override
    public String toString() {
        return getStatusIcon() + " " + description;
    }
}

public class KBot {
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
            String[] inputParts = input.split(" ", 2);
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

                default:
                    addTask(input);
                    break;
            }
        }
    }

    private void greetUser() {
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm KBot");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");
    }

    private String getUserInput() {
        return scanner.nextLine();
    }

    private void addTask(String description) {
        tasks.add(new Task(description));
        System.out.println("____________________________________________________________");
        System.out.println("added: " + description);
        System.out.println("____________________________________________________________");
    }

    private void listTasks() {
        System.out.println("____________________________________________________________");
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i));
        }
        System.out.println("____________________________________________________________");
    }

    private void markTaskAsDone(int index) {
        if (index >= 0 && index < tasks.size()) {
            tasks.get(index).markAsDone();
            System.out.println("____________________________________________________________");
            System.out.println("Nice! I've marked this task as done:");
            System.out.println("  " + tasks.get(index));
            System.out.println("____________________________________________________________");
        }
    }

    private void markTaskAsNotDone(int index) {
        if (index >= 0 && index < tasks.size()) {
            tasks.get(index).markAsNotDone();
            System.out.println("____________________________________________________________");
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println("  " + tasks.get(index));
            System.out.println("____________________________________________________________");
        }
    }

    private void exit() {
        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }

    public static void main(String[] args) {
        KBot bot = new KBot();
        bot.run();
    }
}
