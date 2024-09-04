import java.util.Scanner;

public class Aether {
    private boolean isExit = false;
    private Task[] tasks = new Task[100];
    private int taskCount = 0;

    public static void main(String[] args) {
        Aether aether = new Aether();
        aether.run();
    }

    public void run() {
        Display.showStartScreen();
        Scanner scanner = new Scanner(System.in);

        // Main loop to keep the program running until the user exits
        while (!isExit) {
            System.out.println("You:");
            String command = scanner.nextLine();
            Display.printSeparator();
            handleCommand(command);
        }
    }

    private void handleCommand(String command) {
        int index = 0;

        // Trim leading and trailing spaces and split the command into parts
        command = command.trim();
        String[] commandParts = command.split(" ", 2);
        String commandName = commandParts[0].toLowerCase();

        String arguments = commandParts.length > 1 ? commandParts[1] : "";

        // Switch to Handle different commands
        switch (commandName) {
        case "bye":
            isExit = true;
            Display.showEndScreen();
            break;
        case "list":
            listTasks();
            break;
        case "mark":
            index = Integer.parseInt(arguments) - 1;
            markTaskStatus(index, true);
            break;
        case "unmark":
            index = Integer.parseInt(arguments) - 1;
            markTaskStatus(index, false);
            break;
        case "todo":
            addTask(new Todo(arguments));
            break;
        case "deadline":
            String[] deadlineParts = arguments.split(" /by ", 2);
            addTask(new Deadline(deadlineParts[0], deadlineParts[1]));
            break;
        case "event":
            String[] eventParts = arguments.split(" /from | /to ");
            addTask(new Event(eventParts[0], eventParts[1], eventParts[2]));
            break;
        default:
            Display.response("Invalid command. Please try again.");
            break;
        }
        Display.printSeparator();
    }

    private void addTask(Task task) {
        if (taskCount < tasks.length) {
            tasks[taskCount] = task;
            taskCount++;
            Display.response("Got it. I've added this task:\n  " + task);
        } else {
            Display.response("Task list is full. Sorry!");
        }
    }

    private void listTasks() {
        if (taskCount == 0) {
            Display.response("Task list is empty.");
        } else {
            Display.response("Here are the tasks in your list:");
            for (int i = 0; i < taskCount; i++) {
                System.out.println((i + 1) + "." + tasks[i]);
            }
        }
    }

    private void markTaskStatus(int index, boolean isDone) {
        tasks[index].setDone(isDone);
        String message = isDone
                ? "Nice! I've marked this task as done:\n"
                : "OK, I've marked this task as not done yet:\n";
        // Use task's `toString()` to include task type and status
        Display.response(message + (index + 1) + "." + tasks[index]);
    }
}

class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return description;
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    public String getStatus() {
        return (isDone ? "✓" : " ");
    }

    @Override
    public String toString() {
        return "[" + getStatus() + "] " + description;
    }
}

// Todo class inherits from Task
class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

// Deadline class inherits from Task
class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}

// Event class inherits from Task
class Event extends Task {
    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}

class Display {
    public static void showStartScreen() {
        String logo = "     ___   ______  _______  _    _  ______  _____   \n"
                + "    /   | |  ____||__   __|| |  | ||  ____||  __ \\  \n"
                + "   / /| | | |__      | |   | |__| || |__   | |__) | \n"
                + "  / /_| | |  __|     | |   |  __  ||  __|  |  _  /  \n"
                + " /  __  | | |____    | |   | |  | || |____ | | \\ \\  \n"
                + "/_/   |_| |______|   |_|   |_|  |_||______||_|  \\_\\ \n";
        String startScreen = "Aether:\n" + "Hello! I'm Aether, your friendly assistant.\n"
                + "How can I help you today?\n";

        System.out.println(logo);
        printSeparator();
        System.out.println(startScreen);
        printSeparator();
    }

    public static void showEndScreen() {
        String endScreen = "Aether:\n" + "Goodbye! Hope to see you again soon!";
        System.out.println(endScreen);
    }

    public static void printSeparator() {
        System.out.println("____________________________________________________");
    }

    public static void response(String message) {
        System.out.println("Aether:\n" + message);
    }
}