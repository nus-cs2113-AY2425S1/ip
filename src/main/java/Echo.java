import command.*;
import task.*;
import java.util.Scanner;

public class Echo {
    private static final String SEPARATOR = "_".repeat(30);

    public static void runEcho() {
        // Utilize Scanner object to allow user input
        Scanner scanner = new Scanner(System.in);

        TaskList taskList = new TaskList();

        // Define the greeting message and the chatbot name as variables
        String chatbotName = "Echo";
        String greetingMessage = "Hello! I'm " + chatbotName + "\nWhat can I do for you?\n";

        // Define the exit message as a variable
        String exitMessage = "Bye. Hope to see you again soon!";

        // Print the greeting message from Echo
        printGreeting(greetingMessage);

        String userInput;
        do {
            // Read user input
            userInput = scanner.nextLine();

            // Handles different user inputs for list, bye, mark, and unmark
            processUserInput(userInput, taskList);
        } while (!userInput.equalsIgnoreCase("bye"));

        // Print the exit message from Echo
        printExitMessage(exitMessage);
    }

    private static void processUserInput(String userInput, TaskList taskList) {
        if (userInput.startsWith("list")) {
            new ListCommand().execute(taskList, userInput);
        } else if (userInput.startsWith("mark")) {
            new MarkCommand().execute(taskList, userInput);
        } else if (userInput.startsWith("unmark")) {
            new UnmarkCommand().execute(taskList, userInput);
        } else if (userInput.startsWith("todo")) {
            new ToDoCommand().execute(taskList, userInput);
        } else if (userInput.startsWith("deadline")) {
            new DeadlineCommand().execute(taskList, userInput);
        } else if (userInput.startsWith("event")) {
            new EventCommand().execute(taskList, userInput);
        } else {
            System.out.println(SEPARATOR);
            System.out.println("Invalid input, please try again.");
            System.out.println(SEPARATOR);
        }
    }

    /**
     * Method where Echo greets the user.
     *
     * @param message The start message displayed on CLI.
     */
    private static void printGreeting(String message) {
        System.out.println(SEPARATOR);
        System.out.println(message);
        System.out.println(SEPARATOR);
    }

    /**
     * Method to handle the exit message.
     *
     * @param message Displays exit message by Echo.
     */
    private static void printExitMessage(String message) {
        System.out.println(SEPARATOR);
        System.out.println(message);
        System.out.println(SEPARATOR);
    }

    public static void main(String[] args) {
        runEcho();
    }
}