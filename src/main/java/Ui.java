import java.util.Scanner;

/**
 * Handles displaying messages on the command-line interface (CLI) for the Diana assistant.
 *
 * This class is responsible for reading user input and displaying various messages,
 * such as welcome and goodbye messages, task-related instructions, and error messages.
 */
public class Ui {
    private static final String ENCLOSURE = "------------------------------";
    private Scanner scanner;

    /**
     * Constructor for the Ui class.
     * Initializes a new Scanner instance for reading user input from the command line.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Reads user input from the command line.
     *
     * @return The user's input as a String.
     */
    public String readInput() {
        return scanner.nextLine();
    }

    /**
     * Closes the Scanner to release system resources.
     */
    public void closeScanner() {
        scanner.close();
    }

    /**
     * Prints the welcome message along with the list of commands that the user can execute.
     */
    public void printWelcomeMessage() {
        printEnclosure();
        System.out.println("Hello, I am Diana! A personal assistant that helps you keep track of your tasks :)");
        System.out.println("Here are the list of things that Diana can do for you:");
        printListofCommands();
        printEnclosure();
    }

    /**
     * Prints the list of commands available for the user to interact with the task manager.
     */
    public void printListofCommands() {
        System.out.println("1. Create a todo task eg. [todo read book]");
        System.out.println("2. Create an event task eg. [event read book /from 2/12/2019 1800 /to 3/12/2019 1800]");
        System.out.println("3. Create a deadline task eg. [deadline read book /by 2/12/2019 1800]");
        System.out.println("4. Type either mark <index> or unmark <index> to indicate the completion rate of your task");
        System.out.println("5. Delete a task eg. [delete 1]");
        System.out.println("6. Type list to view the your list of tasks.");
        System.out.println("7. Type bye to exit the programme.");
        System.out.println("8. Type find <task> to find tasks with specific keywords.");
        System.out.println("9. Type date <date> to find tasks on specific dates.");
    }

    /**
     * Prints an error message to notify the user about errors in command execution or task processing.
     *
     * @param errorMessage The error message to be displayed.
     */
    public void printErrorMessage(String errorMessage) {
        System.out.println("Error: " + errorMessage);
    }

    /**
     * Prints an enclosure line (a line of dashes) to visually separate output sections.
     */
    public void printEnclosure() {
        System.out.println(ENCLOSURE);
    }

    /**
     * Prints a goodbye message when the user exits the application.
     */
    public void printGoodByeMessage() {
        System.out.println("Goodbye!");
    }

    /**
     * Prints an error message if loading tasks from storage fails.
     * This is used to inform the user that tasks couldn't be loaded and an empty list is being used instead.
     */
    public void showLoadingError() {
        System.out.println("Error loading tasks, reverting to empty list.");
    }
}
