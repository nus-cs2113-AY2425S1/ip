package app;

import tasks.Task;

import exceptions.TerriException;

import taskmanager.TaskList;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * The {@code Terri} class is the main entry point of the chatbot application.
 * It processes user inputs, handles task management commands, and provides
 * instructions or reminders when needed. The chatbot continues to run in a loop
 * until the user terminates it with an exit command.
 *
 * <p>The class offers functionality for adding tasks, marking tasks as complete or incomplete,
 * listing tasks, and handling events or deadlines. The chatbot also provides error handling
 * and user guidance when unrecognized commands are entered.</p>
 */

public class Terri {
    final static String UNRECOGNISED_KEYWORD = "Hey! I don't understand that command.";
    final static String OFFER_REFRESHER = "Did you want a refresher on what tasks I can do for ya? Type Y to accept.";
    final static String REFRESHER_ACCEPTED = "No worries! Here's all the things I can do again.";
    final static String REFRESHER_DECLINED = "Gotcha - let me know what I can do for ya then!";

    /**
     * Prints a welcome message along with the application logo and instructions.
     * This method is called at the start of the application.
     */
    private static void printWelcomeMessage() {
        String logo = "\n" +
                "$$$$$$$$\\                               $$\\ \n" +
                "\\__$$  __|                              \\__|\n" +
                "   $$ |    $$$$$$\\   $$$$$$\\   $$$$$$\\  $$\\ \n" +
                "   $$ |   $$  __$$\\ $$  __$$\\ $$  __$$\\ $$ |\n" +
                "   $$ |   $$$$$$$$ |$$ |  \\__|$$ |  \\__|$$ |\n" +
                "   $$ |   $$   ____|$$ |      $$ |      $$ |\n" +
                "   $$ |   \\$$$$$$$\\ $$ |      $$ |      $$ |\n" +
                "   \\__|    \\_______|\\__|      \\__|      \\__|\n" +
                "                                            \n";

        System.out.println(logo);
        System.out.println("Heya! I'm Terri.");
        printInstructions();
    }

    /**
     * The main method initializes the chatbot and starts processing user input.
     * It continually reads user input and processes task-related commands, until
     * the user types "bye" to exit the application.
     *
     * @param args Command-line arguments (not used in this application).
     */
    public static void main(String[] args) {

        printWelcomeMessage();

        // Initialise a scanner to read input
        Scanner scanner = new Scanner(System.in);
        // Initialise Task array and counter
        ArrayList<Task> taskList = new ArrayList<>();

        // Continually check for and process user input
        while (true) {
            String userInput = scanner.nextLine().trim();

            // Short-circuit check for exit input.
            if (userInput.equalsIgnoreCase("bye")) {
                // Exit Chatbot and close scanner.
                exitTerri(scanner);
                break;
            }

            // Process input and catch exceptions appropriately
            try {
                handleInput(userInput, taskList);
            }
            catch (TerriException ex) {
                // Print custom error message and offer general refresher
                System.out.println(ex.getMessage());
                System.out.println(OFFER_REFRESHER);

                if (scanner.nextLine().equalsIgnoreCase("Y")) {
                    System.out.println(REFRESHER_ACCEPTED);
                    printInstructions();
                } else {
                    System.out.println(REFRESHER_DECLINED);
                }
            }
            printDivider(); 
        }
    }

    /**
     * Processes the user input and handles task-related commands.
     * Recognized commands include listing tasks, marking tasks as complete or incomplete,
     * adding deadlines or events, and deleting tasks. If an unrecognized command is entered,
     * a {@code TerriException} is thrown.
     *
     * @param userInput The input entered by the user.
     * @param taskList The current list of tasks managed by the chatbot.
     * @throws TerriException If the input command is not recognized.
     */
    private static void handleInput(String userInput, ArrayList<Task> taskList) throws TerriException {

        // Isolate individual keywords in user input
        String[] keyWord = userInput.split(" ");

        // Define behaviour depending on initial keyword
        switch (keyWord[0].toLowerCase()) {
            // List all tasks with completion indicators
            case "list":
                TaskList.listTasks();
                break;
            // Mark tasks complete
            case "mark":
                TaskList.handleSetDone(keyWord,true);
                break;
            // Mark task not complete
            case "unmark":
                TaskList.handleSetDone(keyWord,false);
                break;
            case "deadline":
                TaskList.handleDeadline(keyWord);
                break;
            case "event":
                TaskList.handleEvent(keyWord);
                break;
            case "todo":
                TaskList.addToDo(keyWord);
                break;
            case "delete":
                TaskList.deleteTask(keyWord);
                break;
            // Handle unrecognised keywords by offering instruction page
            default:
                throw new TerriException(UNRECOGNISED_KEYWORD);
        }
    }

    /**
     * Exits the chatbot by printing a goodbye message, closing the scanner,
     * and terminating the program.
     *
     * @param scanner The {@code Scanner} object used to read user input.
     */
    public static void exitTerri(Scanner scanner) {
    System.out.println("Bye then! See ya soon!");
    printDivider();
    // Close scanner after loop ends and program terminates
    scanner.close();
    }

    /**
     * Prints a divider line used to visually separate sections of output.
     */
    public static void printDivider() {
        String divider = "____________________________________________________________";
        System.out.println(divider);
    }

    /**
     * Prints instructions on how to use the chatbot and the available commands.
     * This method is called at the beginning of the application and when a refresher
     * is requested.
     */
    private static void printInstructions() {
        System.out.println("I can log and manage a ton of different tasks - just " +
                "use different keywords followed by a description to tell me what to do:");

        printDivider();

        System.out.println("KEYWORDS:");
        System.out.println("'todo' + (description) : logs a task with no specific due date");
        System.out.println("'deadline' + (description) + '/by (due date)' : logs a task due " +
                "at a specific point in time");
        System.out.println("'event' +  (description) + '/from (start time)' + '/to (end time)' : " +
                "logs an event occurring during a specified period");
        System.out.println("If I don't see one of the above keywords, " +
                "I'll ask ya if you want a refresher!");

        printDivider();

        System.out.println("UTILITY:");
        System.out.println("'list' : Generates a list of all currently logged tasks/events, " +
                "ordered oldest-newest. The number before each task is it's 'task number'");

        System.out.println("'mark' + '(task number)' : Records that a task has been completed");
        System.out.println("'unmark' + '(task number)' : Records that a task has not been completed");

        printDivider();

        System.out.println("So - what can I help you with today?");

        printDivider();

    }




}
