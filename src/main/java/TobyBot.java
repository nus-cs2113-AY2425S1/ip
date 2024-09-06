import java.util.ArrayList;
import java.util.Scanner;

public class TobyBot {
    private static final String LINE = "____________________________________________________________";
    private static final ArrayList<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(LINE);
        System.out.println("Hello! I'm TobyBot");
        System.out.println("What can I do for you?");
        System.out.println(LINE);

        while (true) {
            String input = scanner.nextLine();
            System.out.println(LINE);

            try {
                if (input.equals("bye")) {
                    System.out.println("Bye. Hope to see you again soon!");
                    System.out.println(LINE);
                    break;
                } else if (input.equals("list")) {
                    listTasks();
                } else if (input.startsWith("mark ")) {
                    markTask(input);
                } else if (input.startsWith("unmark ")) {
                    unmarkTask(input);
                } else if (input.startsWith("todo ")) {
                    addTodoTask(input.substring(5).trim());
                } else if (input.startsWith("deadline ")) {
                    addDeadlineTask(input.substring(9).trim());
                } else if (input.startsWith("event ")) {
                    addEventTask(input.substring(6).trim());
                } else {
                    throw new TobyBotException("I'm sorry. Your input doesn't fit the format of all tasks :-(");
                }
            } catch (TobyBotException e) {
                System.out.println(e.getMessage());
            }

            System.out.println(LINE);
        }
    }

    private static void listTasks() {
        if (tasks.isEmpty()) {
            System.out.println("Your task list is empty.");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + "." + tasks.get(i));
            }
        }
    }

    private static void addTodoTask(String description) throws TobyBotException {
        if (description.isEmpty()) {
            throw new TobyBotException("The description of a todo cannot be empty.");
        }
        Task task = new Todo(description);
        tasks.add(task);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    private static void addDeadlineTask(String description) throws TobyBotException {
        String[] parts = description.split(" /by ");
        if (parts.length < 2 || parts[0].isEmpty()) {
            throw new TobyBotException("The description or deadline of a task cannot be empty.");
        }
        Task task = new Deadline(parts[0], parts[1]);
        tasks.add(task);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    private static void addEventTask(String description) throws TobyBotException {
        String[] parts = description.split(" /from | /to ");
        if (parts.length < 3 || parts[0].isEmpty()) {
            throw new TobyBotException("The description or timing of an event cannot be empty.");
        }
        Task task = new Event(parts[0], parts[1], parts[2]);
        tasks.add(task);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    private static void markTask(String input) {
        try {
            int taskNumber = Integer.parseInt(input.split(" ")[1]) - 1;
            Task task = tasks.get(taskNumber);
            task.markAsDone();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println("  " + task);
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            System.out.println("Invalid task number.");
        }
    }

    private static void unmarkTask(String input) {
        try {
            int taskNumber = Integer.parseInt(input.split(" ")[1]) - 1;
            Task task = tasks.get(taskNumber);
            task.markAsNotDone();
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println("  " + task);
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            System.out.println("Invalid task number.");
        }
    }
}

class TobyBotException extends Exception {
    public TobyBotException(String message) {
        super(message);
    }
}

class Task {
    protected String description;
    protected boolean isDone;

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
        return (isDone ? "X" : " ");
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}

class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

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