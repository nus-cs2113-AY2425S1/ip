import model.*;
import exception.MondayException;
import java.io.*;
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
    private static final String FILE_PATH = "../data/monday.txt";

    public static void main(String[] args) {
        Monday monday = new Monday();
        monday.loadTasks();
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
                    saveTasks();
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

    private void saveTasks() {
        try {
            File file = new File(FILE_PATH);
            // Create the parent directory if it doesn't exist
            file.getParentFile().mkdirs();

            PrintWriter printWriter = new PrintWriter(new FileWriter(file));
            for (int i = 0; i < taskCount; i++) {
                Task task = tasks[i];
                if (task instanceof Todo) {
                    Todo todo = (Todo) task;
                    printWriter.println("T | " + (todo.isDone() ? "1" : "0") + " | " + todo.getDescription());
                } else if (task instanceof Deadline) {
                    Deadline deadline = (Deadline) task;
                    printWriter.println("D | " + (deadline.isDone() ? "1" : "0") + " | " + deadline.getDescription() + " | " + deadline.getBy());
                } else if (task instanceof Event) {
                    Event event = (Event) task;
                    printWriter.println("E | " + (event.isDone() ? "1" : "0") + " | " + event.getDescription() + " | " + event.getFrom() + " | " + event.getTo());
                }
            }
            printWriter.close();
            System.out.println("    Tasks saved to file.");
        } catch (IOException e) {
            System.out.println("    OOPS!!! There was an error saving the tasks.");
            e.printStackTrace(); // For debugging purposes
        }
    }

    private void loadTasks() {
        try {
            File file = new File(FILE_PATH);
            if (!file.exists()) {
                System.out.println("    No previous tasks found.");
                return;
            }

            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(" \\| ");
                String type = parts[0];
                boolean isDone = parts[1].equals("1");
                String description = parts[2];

                switch (type) {
                case "T":
                    tasks[taskCount++] = new Todo(description);
                    tasks[taskCount - 1].markAsDone();
                    break;
                case "D":
                    String by = parts[3];
                    tasks[taskCount++] = new Deadline(description, by);
                    tasks[taskCount - 1].markAsDone();
                    break;
                case "E":
                    String from = parts[3];
                    String to = parts[4];
                    tasks[taskCount++] = new Event(description, from, to);
                    tasks[taskCount - 1].markAsDone();
                    break;
                default:
                    throw new MondayException("Unknown task type.");
                }
            }

            scanner.close();
            System.out.println("    Tasks loaded from file.");
        } catch (FileNotFoundException e) {
            System.out.println("    OOPS!!! No saved tasks found.");
        } catch (MondayException e) {
            System.out.println("    OOPS!!! " + e.getMessage());
        }
    }
}
