package niwa.ui;

import niwa.command.CommandResult;
import niwa.data.task.Task;
import niwa.messages.NiwaMesssages;

import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * The class {@code NiwaUI} is responsible for handling user interactions with {@code Niwa} chatbot.
 * It provides methods for inputting commands and displaying messages.
 */
public class NiwaUI {
    private static final String PREFIX = "\t"; // Prefix for message formatting
    private static Scanner scanner; // Scanner for reading user input

    /**
     * Initializes a new instance of the {@code NiwaUI} class, setting up the scanner for user input.
     */
    public NiwaUI() {
        scanner = new Scanner(System.in);
    }

    /**
     * Gets the command entered by the user.
     *
     * @return The command entered by the user as a string.
     */
    public String getUserCommand() {
        if (scanner.hasNextLine()) {
            String command = scanner.nextLine();

            // Silently consume all ignored lines (empty commands)
            while (command.trim().isEmpty()) {
                command = scanner.nextLine();
            }

            return command;
        }
        return "";
    }

    /**
     * Prints a single message to the console.
     *
     * @param message The message to be printed.
     */
    public void printMessage(String message) {
        System.out.println(PREFIX + message);
    }

    /**
     * Prints a message to the console without a new line at the end.
     *
     * @param message The message to be printed.
     */
    public void printMiddleMessage(String message) {
        System.out.print(PREFIX + message);
    }

    /**
     * Prints multiple messages to the console, each as a separate line.
     *
     * @param messages The list of messages to print.
     */
    public void printMessages(List<String> messages) {
        messages.forEach(this::printMessage);
    }

    /**
     * Prints multiple messages to the console, each as a separate line.
     *
     * @param messages The messages to print, provided as a variable-length argument list.
     */
    public void printMessages(String... messages) {
        for (String m : messages) {
            printMessage(m);
        }
    }

    /**
     * Prints detailed information of a task list.
     *
     * @param tasks The list of tasks to print.
     */
    public void printTaskInfo(List<Task> tasks) {
        if (tasks == null) {
            return;
        }

        AtomicInteger index = new AtomicInteger(1); // Used for indexing tasks
        tasks.forEach((t) -> printMessage(index.getAndIncrement() + "." + t.getFullInfo()));
    }

    /**
     * Displays the result of a command execution, including feedback to the user
     * and relevant task information.
     *
     * @param result The {@code CommandResult} containing feedback and relevant tasks.
     */
    public void showCommandResult(CommandResult result) {
        if (result == null) {
            return;
        }
        printMessage(NiwaMesssages.SEPARATOR); // Print a separator
        printMessages(result.feedbackToUser); // Print feedback to user
        printTaskInfo(result.getRelevantTasks()); // Print relevant task information
        printMessage(NiwaMesssages.SEPARATOR); // Print another separator
    }
}
