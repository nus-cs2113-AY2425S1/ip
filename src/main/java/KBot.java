import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class KBot {
    private static final String SEPARATOR = "____________________________________________________________";
    private static final String FILE_PATH = "./data/KBot.txt"; // relative path for saving tasks
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

    public void run() {
        greetUser();

        boolean isRunning = true;
        while (isRunning) {
            try {
                String userInput = getUserInput();
                isRunning = handleCommand(userInput);
            } catch (KBotException e) {
                showError(e.getMessage());
            }
        }

        exit();
    }

    private boolean handleCommand(String input) throws KBotException {
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
                try {
                    markTaskAsDone(Integer.parseInt(argument) - 1);
                } catch (NumberFormatException | IndexOutOfBoundsException e) {
                    throw new KBotException("Invalid task number to mark.");
                }
                break;
            case "unmark":
                try {
                    markTaskAsNotDone(Integer.parseInt(argument) - 1);
                } catch (NumberFormatException | IndexOutOfBoundsException e) {
                    throw new KBotException("Invalid task number to unmark.");
                }
                break;
            case "todo":
                if (argument.isEmpty()) {
                    throw new KBotException("The description of a todo cannot be empty.");
                } else {
                    addTodoTask(argument);
                }
                break;
            case "deadline":
                if (!argument.contains("/by ")) {
                    throw new KBotException("The deadline description or date is missing.");
                } else {
                    addDeadlineTask(argument);
                }
                break;
            case "event":
                if (!argument.contains("/from ") || !argument.contains("/to ")) {
                    throw new KBotException("The event description or timing is missing.");
                } else {
                    addEventTask(argument);
                }
                break;
            case "delete":
                try {
                    deleteTask(Integer.parseInt(argument));
                } catch (NumberFormatException e) {
                    throw new KBotException("Invalid task number to delete.");
                }
                break;
            default:
                throw KBotException.unknownCommand();
        }

        saveTasksToFile(); // Save the updated task list after each change
        return true;
    }


    // Utility Methods

    private void greetUser() {
        printSeparator();
        System.out.println("Hello! I'm KBot");
        System.out.println("What can I do for you?");
        printSeparator();
    }

    private void printSeparator() {
        System.out.println(SEPARATOR);
    }

    private String getUserInput() {
        return scanner.nextLine();
    }

    private void addTodoTask(String description) {
        Task task = new Todo(description);
        tasks.add(task);
        printTaskAddedMessage(task);
    }

    private void addDeadlineTask(String input) throws KBotException {
        String[] parts = input.split(" /by ");
        if (parts.length < 2) {
            throw new KBotException("Invalid format for deadline. Use: deadline <description> /by <time>");
        }
        Task task = new Deadline(parts[0], parts[1]);
        tasks.add(task);
        printTaskAddedMessage(task);
    }

    private void addEventTask(String input) throws KBotException {
        String[] parts = input.split(" /from | /to ");
        if (parts.length < 3) {
            throw new KBotException("Invalid format for event. Use: event <description> /from <start> /to <end>");
        }
        Task task = new Event(parts[0], parts[1], parts[2]);
        tasks.add(task);
        printTaskAddedMessage(task);
    }

    private void listTasks() {
        printSeparator();
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i));
        }
        printSeparator();
    }

    private void markTaskAsDone(int index) throws KBotException {
        if (index >= tasks.size() || index < 0) {
            throw new KBotException("Invalid task number.");
        }
        tasks.get(index).markAsDone();
        printMarkDoneMessage(tasks.get(index));
    }

    private void markTaskAsNotDone(int index) throws KBotException {
        if (index >= tasks.size() || index < 0) {
            throw new KBotException("Invalid task number.");
        }
        tasks.get(index).markAsNotDone();
        printMarkNotDoneMessage(tasks.get(index));
    }

    private void showError(String errorMessage) {
        printSeparator();
        System.out.println(errorMessage);
        printSeparator();
    }

    private void exit() {
        printSeparator();
        System.out.println("Bye. Hope to see you again soon!");
        printSeparator();
    }

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

    public void deleteTask(int taskIndex) throws KBotException {
        if (taskIndex < 1 || taskIndex > tasks.size()) {
            throw new KBotException("OOPS!!! Task does not exist.");
        }
        Task removedTask = tasks.remove(taskIndex - 1); // Adjust for 0-based index
        System.out.println(SEPARATOR);
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + removedTask);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        System.out.println(SEPARATOR);
    }

<<<<<<< HEAD
    // File Saving and Loading Methods

    private void saveTasksToFile() {
        try {
            File file = new File(FILE_PATH);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs(); // Create directories if they do not exist
            }
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            for (Task task : tasks) {
                writer.write(task.toFileString());
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error occurred while saving tasks to file: " + e.getMessage());
        }
    }
=======
>>>>>>> branch-Level-6
}


