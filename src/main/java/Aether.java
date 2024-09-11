import java.util.Scanner;

/**
 * Aether class represents the main logic for a simple task manager program.
 * It allows users to add, mark, unmark tasks, and display tasks.
 */
public class Aether {
    private boolean isExit = false;
    private static final int MAX_TASKS = 100;
    private Task[] tasks = new Task[MAX_TASKS];
    private int taskCount = 0;

    public static void main(String[] args) {
        Aether aether = new Aether();
        aether.run();
    }

    /**
     * Runs the main loop of the program, displaying the start screen
     * and handling user input until the user decides to exit.
     */
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
        scanner.close();
    }

    /**
     * Handles user input commands such as adding, marking, unmarking, and listing tasks.
     * @param command User input command as a string.
     */
    private void handleCommand(String command) {
        command = command.trim();
        if (command.isEmpty()) {
            Display.response("Command cannot be empty. Please enter a valid command.");
            return;
        }

        String[] commandParts = command.split(" ", 2);
        String commandName = commandParts[0].toLowerCase();
        String arguments = commandParts.length > 1 ? commandParts[1] : "";

        // Handles different commands based on user input.
        switch (commandName) {
        case "bye":
            isExit = true;
            Display.showEndScreen();
            break;
        case "list":
            listTasks();
            break;
        case "mark":
            handleMarkCommand(arguments, true);
            break;
        case "unmark":
            handleMarkCommand(arguments, false);
            break;
        case "todo":
            handleTodoCommand(arguments);
            break;
        case "deadline":
            handleDeadlineCommand(arguments);
            break;
        case "event":
            handleEventCommand(arguments);
            break;
        default:
            Display.response("Invalid command. Please try again.");
            break;
        }
        Display.printSeparator();
    }

    /**
     * Handles the "mark" and "unmark" commands to update the task's status.
     * @param arguments Task number provided by the user.
     * @param isMark Boolean flag to indicate marking or unmarking.
     */
    private void handleMarkCommand(String arguments, boolean isMark) {
        try {
            int index = Integer.parseInt(arguments.trim()) - 1;
            if (index < 0 || index >= taskCount) {
                Display.response("Invalid task number. Please enter a valid task index.");
                return;
            }
            markTaskStatus(index, isMark);
        } catch (NumberFormatException e) {
            Display.response("Invalid input. Please enter a valid task number.");
        }
    }

    /**
     * Adds a new todo task.
     * @param arguments Description of the todo task.
     */
    private void handleTodoCommand(String arguments) {
        if (arguments.isEmpty()) {
            Display.response("The description of a todo cannot be empty. Please enter a valid description.");
        } else {
            addTask(new Todo(arguments));
        }
    }

    /**
     * Adds a new deadline task.
     * @param arguments Description and deadline date in the format: description /by date.
     */
    private void handleDeadlineCommand(String arguments) {
        String[] deadlineParts = arguments.split(" /by ", 2);
        if (arguments.isEmpty() || deadlineParts.length < 2) {
            Display.response("Please enter a deadline in the format:\ndescription /by date.");
        } else {
            addTask(new Deadline(deadlineParts[0], deadlineParts[1]));
        }
    }

    /**
     * Adds a new event task.
     * @param arguments Description, start, and end times in the format: description /from start_time /to end_time.
     */
    private void handleEventCommand(String arguments) {
        String[] eventParts = arguments.split(" /from | /to ");
        if (arguments.isEmpty() || !arguments.contains("/from") || !arguments.contains("/to")
                || eventParts.length < 3) {
            Display.response("Please enter an event in the format:\ndescription /from start_time /to end_time.");
        } else {
            addTask(new Event(eventParts[0], eventParts[1], eventParts[2]));
        }
    }

    /**
     * Adds a new task to the task list.
     * @param task The task to be added.
     */
    private void addTask(Task task) {
        if (taskCount < tasks.length) {
            tasks[taskCount] = task;
            taskCount++;
            Display.response("Got it. I've added this task:\n  " + task
                    + "\nNow you have " + taskCount + " tasks in the list.");
        } else {
            Display.response("Task list is full. Sorry!");
        }
    }

    /**
     * Lists all tasks in the task list.
     */
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

    /**
     * Marks or unmarks the status of a task.
     * @param index Index of the task in the task list.
     * @param isDone Boolean flag indicating if the task is done.
     */
    private void markTaskStatus(int index, boolean isDone) {
        tasks[index].setDone(isDone);
        String message = isDone
                ? "Nice! I've marked this task as done:\n"
                : "OK, I've marked this task as not done yet:\n";
        Display.response(message + (index + 1) + "." + tasks[index]);
    }
}

// Task class, the base class for all types of tasks
class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
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

// Todo class for simple todo tasks
class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

// Deadline class for tasks with deadlines
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

// Event class for tasks with start and end times
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

// Display class to handle all user interface outputs
class Display {
    public static void showStartScreen() {
        String logo = "     ___   ______  _______  _    _  ______  _____\n"
                + "    /   | |  ____||__   __|| |  | ||  ____||  __ \\\n"
                + "   / /| | | |__      | |   | |__| || |__   | |__) |\n"
                + "  / /_| | |  __|     | |   |  __  ||  __|  |  _  /\n"
                + " /  __  | | |____    | |   | |  | || |____ | | \\ \\\n"
                + "/_/   |_| |______|   |_|   |_|  |_||______||_|  \\_\\\n";
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
