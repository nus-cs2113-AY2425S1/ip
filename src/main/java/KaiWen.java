import java.util.Scanner;
import java.util.ArrayList;
import java.io.IOException;

public class KaiWen {
    private static ArrayList<Task> tasks = new ArrayList<>();
    private static final String FILE_PATH = "./Users/robertwang/ip/kaiwen.txt";
    private static Storage storage;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        storage = new Storage(FILE_PATH);

        try {
            tasks = storage.load();
        } catch (IOException e) {
            System.out.println("Error loading tasks from file: " + e.getMessage());
            tasks = new ArrayList<>();
        }

        printLine();
        System.out.println(" Hello! I'm KaiWen");
        System.out.println(" What can I do for you?");
        printLine();

        while (true) {
            String input = scanner.nextLine();

            if (input.equals("bye")) {
                printLine();
                System.out.println(" Bye. Hope to see you again soon!");
                printLine();
                break;
            }

            try {
                handleInput(input);
                storage.save(tasks);
            } catch (KaiException e) {
                printLine();
                System.out.println(" OOPS!!! " + e.getMessage());
                printLine();
            } catch (IOException e) {
                printLine();
                System.out.println(" OOPS!!! Error saving tasks: " + e.getMessage());
                printLine();
            }
        }

        scanner.close();
    }

    public static void handleInput(String input) throws KaiException {
        if (input.equals("list")) {
            handleListCommand();
        } else if (input.startsWith("todo")) {
            handleTodoCommand(input);
        } else if (input.startsWith("deadline")) {
            handleDeadlineCommand(input);
        } else if (input.startsWith("event")) {
            handleEventCommand(input);
        } else if (input.startsWith("mark")) {
            handleMarkCommand(input);
        } else if (input.startsWith("unmark")) {
            handleUnmarkCommand(input);
        } else if (input.startsWith("delete")) {
            handleDeleteCommand(input);
        } else {
            throw new KaiException("I'm sorry, but I don't know what that means :-(");
        }
    }

    public static void handleTodoCommand(String input) throws KaiException {
        if (input.trim().equals("todo")) {
            throw new KaiException("The description of a todo cannot be empty.");
        }
        String description = input.substring(5).trim();
        tasks.add(new Todo(description));
        printAddedTask(tasks.get(tasks.size() - 1));
    }

    public static void handleDeadlineCommand(String input) throws KaiException {
        if (input.trim().equals("deadline")) {
            throw new KaiException("The description of a deadline cannot be empty.");
        }
        String[] parts = input.substring(9).split(" /by ");
        if (parts.length < 2) {
            throw new KaiException("The deadline needs a description and a '/by' date.");
        }
        tasks.add(new Deadline(parts[0].trim(), parts[1].trim()));
        printAddedTask(tasks.get(tasks.size() - 1));
    }

    public static void handleEventCommand(String input) throws KaiException {
        if (input.trim().equals("event")) {
            throw new KaiException("The description of an event cannot be empty.");
        }
        String[] parts = input.substring(6).split(" /from ");
        if (parts.length < 2 || !parts[1].contains(" /to ")) {
            throw new KaiException("The event needs a description, a '/from' time, and a '/to' time.");
        }
        String[] timeParts = parts[1].split(" /to ");
        tasks.add(new Event(parts[0].trim(), timeParts[0].trim(), timeParts[1].trim()));
        printAddedTask(tasks.get(tasks.size() - 1));
    }

    public static void handleListCommand() {
        printLine();
        if (tasks.isEmpty()) {
            System.out.println(" No tasks to display!");
        } else {
            System.out.println(" Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ". " + tasks.get(i));
            }
        }
        printLine();
    }

    public static void handleMarkCommand(String input) throws KaiException {
        String[] parts = input.split(" ");
        if (parts.length < 2) {
            throw new KaiException("You must specify a task number to mark.");
        }
        int taskNumber = Integer.parseInt(input.split(" ")[1]) - 1;
        if (taskNumber < 0 || taskNumber >= tasks.size()) {
            throw new KaiException("Task number is out of range.");
        }
        tasks.get(taskNumber).markAsDone();
        printLine();
        System.out.println(" Nice! I've marked this task as done:");
        System.out.println("   " + tasks.get(taskNumber));
        printLine();
    }

    public static void handleUnmarkCommand(String input) throws KaiException {
        String[] parts = input.split(" ");
        if (parts.length < 2) {
            throw new KaiException("You must specify a task number to unmark.");
        }
        int taskNumber = Integer.parseInt(input.split(" ")[1]) - 1;
        if (taskNumber < 0 || taskNumber >= tasks.size()) {
            throw new KaiException("Task number is out of range.");
        }
        tasks.get(taskNumber).markAsNotDone();
        printLine();
        System.out.println(" OK, I've marked this task as not done yet:");
        System.out.println("   " + tasks.get(taskNumber));
        printLine();
    }

    public static void handleDeleteCommand(String input) throws KaiException {
        try {
            String[] parts = input.split(" ");
            if (parts.length < 2) {
                throw new KaiException("You must specify a task number to delete.");
            }
            int taskNumber = Integer.parseInt(parts[1]) - 1;
            if (taskNumber < 0 || taskNumber >= tasks.size()) {
                throw new KaiException("Invalid task number. Please provide a valid task number.");
            }
            Task removedTask = tasks.remove(taskNumber);
            printLine();
            System.out.println(" Noted. I've removed this task:");
            System.out.println("   " + removedTask);
            System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
            printLine();
        } catch (NumberFormatException e) {
            throw new KaiException("Please enter a valid task number.");
        }
    }

    public static void printAddedTask(Task task) {
        printLine();
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + task);
        System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
        printLine();
    }

    public static void printLine() {
        System.out.println("____________________________________________________________");
    }
}
