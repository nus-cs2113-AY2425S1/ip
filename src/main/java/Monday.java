import model.*;
import exception.MondayException;
import java.util.Scanner;

public class Monday {
    private static final String LOGO = " __  __                 _             \n"
            + "|  \\/  | ___  _ __   __| | __ _ _   _ \n"
            + "| |\\/| |/ _ \\| '_ \\ / _ |/ _ | | | |\n"
            + "| |  | | (_) | | | | (_| | (_| | |_| |\n"
            + "|_|  |_|\\___/|_| |_|\\__,_|\\__,_|\\__, |\n"
            + "                                |___/  \n";
    private static final String LINE = "____________________________________________________________";
    private static final int MAX_TASKS = 100;
    private Task[] tasks = new Task[MAX_TASKS];
    private int taskCount = 0;

    public static void main(String[] args) {
        Monday monday = new Monday();
        monday.run();
    }

    private void run() {
        printWelcomeMessage();
        handleUserInput();
    }

    private void printWelcomeMessage() {
        System.out.println("Hello from\n" + LOGO);
        System.out.println(LINE);
        System.out.println("    Hello! I'm MONDAY\n    What can I do for you?");
        System.out.println(LINE);
    }

    private void handleUserInput() {
        Scanner scanner = new Scanner(System.in);
        String input;

        while (true) {
            input = scanner.nextLine();
            System.out.println(LINE);

            try {
                if (input.equalsIgnoreCase("list")) {
                    listTasks();
                } else if (input.startsWith("mark ")) {
                    markTaskAsDone(input);
                } else if (input.startsWith("unmark ")) {
                    unmarkTaskAsNotDone(input);
                } else if (input.equalsIgnoreCase("bye")) {
                    printGoodbyeMessage();
                    break;
                } else {
                    addTask(input); // This might throw a MondayException
                }
            } catch (MondayException e) {
                System.out.println("    OOPS!!! " + e.getMessage());
            }

            System.out.println(LINE);
        }
    }

    private void listTasks() {
        System.out.println("    Here are the tasks in your list:");
        for (int i = 0; i < taskCount; i++) {
            System.out.println("    " + (i + 1) + "." + tasks[i]);
        }
    }

    private void markTaskAsDone(String input) {
        try {
            int taskNumber = Integer.parseInt(input.substring(5)) - 1;
            if (isValidTaskNumber(taskNumber)) {
                tasks[taskNumber].markAsDone();
                System.out.println("    Nice! I've marked this task as done:");
                System.out.println("      " + tasks[taskNumber]);
            } else {
                throw new MondayException("Invalid task number.");
            }
        } catch (NumberFormatException e) {
            System.out.println("    OOPS!!! Please enter a valid task number.");
        } catch (MondayException e) {
            System.out.println("    OOPS!!! " + e.getMessage());
        }
    }

    private void unmarkTaskAsNotDone(String input) {
        try {
            int taskNumber = Integer.parseInt(input.substring(7)) - 1;
            if (isValidTaskNumber(taskNumber)) {
                tasks[taskNumber].markAsNotDone();
                System.out.println("    OK, I've marked this task as not done yet:");
                System.out.println("      " + tasks[taskNumber]);
            } else {
                throw new MondayException("Invalid task number.");
            }
        } catch (NumberFormatException e) {
            System.out.println("    OOPS!!! Please enter a valid task number.");
        } catch (MondayException e) {
            System.out.println("    OOPS!!! " + e.getMessage());
        }
    }

    private void addTask(String input) throws MondayException {
        Task task = null;

        if (input.startsWith("todo ")) {
            String description = input.substring(5).trim();
            if (description.isEmpty()) {
                throw new MondayException("The description of a todo cannot be empty.");
            }
            task = new Todo(description);

        } else if (input.startsWith("deadline ")) {
            if (input.contains(" /by ")) {
                String[] parts = input.substring(9).split(" /by ");
                String description = parts[0].trim();
                String by = parts[1].trim();
                if (description.isEmpty() || by.isEmpty()) {
                    throw new MondayException("The description or deadline time cannot be empty.");
                }
                task = new Deadline(description, by);
            } else {
                throw new MondayException("Invalid deadline format. Please use: deadline <description> /by <time>");
            }

        } else if (input.startsWith("event ")) {
            if (input.contains(" /from ") && input.contains(" /to ")) {
                String[] parts = input.substring(6).split(" /from | /to ");
                String description = parts[0].trim();
                String from = parts[1].trim();
                String to = parts[2].trim();
                if (description.isEmpty() || from.isEmpty() || to.isEmpty()) {
                    throw new MondayException("The description or event time cannot be empty.");
                }
                task = new Event(description, from, to);
            } else {
                throw new MondayException("Invalid event format. Please use: event <description> /from <start time> /to <end time>");
            }

        } else if (input.trim().isEmpty()) {
            throw new MondayException("The task description cannot be empty.");
        } else {
            // Provide user guidance for valid commands
            throw new MondayException("Sorry, I don't understand the command. "
                    + "Please use keywords like: 'todo', 'deadline', or 'event'. "
                    + "For example, 'todo <description>' or 'deadline <description> /by <time>'.");
        }

        tasks[taskCount] = task;
        taskCount++;
        System.out.println("    Got it. I've added this task:");
        System.out.println("      " + task);
        System.out.println("    Now you have " + taskCount + " tasks in the list.");
    }

    private void printGoodbyeMessage() {
        System.out.println("    Bye. Hope to see you again soon!");
    }

    private boolean isValidTaskNumber(int taskNumber) {
        return taskNumber >= 0 && taskNumber < taskCount;
    }
}
