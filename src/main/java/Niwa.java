import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Niwa chatbot class that processes user commands to manage a task list [Level-3].
 */
public class Niwa {

    /** Static variables to hold the chatbot's name and logo*/
    static final String NAME = "Niwa";
    static final String LOGO = "\t _   _\n"
            + "\t| \\ | | (_)  _       _  ___\n"
            + "\t|  \\| | | | | | __  // //| |\n"
            + "\t| |\\  | | | | |/  |// //_| |\n"
            + "\t|_| \\_|_|_| |__/|__/ //  |_|";

    /** Variable to check if the chatbot is running*/
    private boolean isRunning;

    /** List to store tasks*/
    private List<Task> tasks = new ArrayList<>();

    /**
     * Getter for the chatbot's name.
     *
     * @return The name of the chatbot.
     */
    public static String getName() {
        return NAME;
    }

    /**
     * Getter for the chatbot's logo.
     *
     * @return The logo of the chatbot.
     */
    public static String getLogo() {
        return LOGO;
    }

    /**
     * Getter for the isRunning flag.
     *
     * @return True if the chatbot is running, false otherwise.
     */
    public boolean getRunning() {
        return isRunning;
    }

    /**
     * Constructor initializes the chatbot and displays a greeting and user guide.
     */
    public Niwa() {
        isRunning = true;
        printGreet(NAME, LOGO);
        help();
    }

    /**
     * Processes the command entered by the user.
     *
     * @param command The user command to process.
     */
    public void processCommand(String command) {
        printHorizontalLine(40);

        command = command.trim();
        String[] commandParts = command.split(" ", 2);

        if (commandParts.length == 1) { // Handle single-word commands
            switch (commandParts[0]) {
            case "bye":
                printExit();
                isRunning = false;
                break;
            case "list":
                list();
                break;
            case "help":
                help();
                break;
            default:
                echo(command);
                break;
            }
        } else if (commandParts.length == 2) { // Handle commands with arguments
            switch (commandParts[0]) {
            case "mark":
                try {
                    int index = Integer.parseInt(commandParts[1]);
                    mark(index);
                } catch (NumberFormatException e) {
                    System.out.println("\tTask's index must be a number!");
                }
                break;
            case "unmark":
                try {
                    int index = Integer.parseInt(commandParts[1]);
                    unmark(index);
                } catch (NumberFormatException e) {
                    System.out.println("\tTask's index must be a number!");
                }
                break;
            case "delete":
                try {
                    int index = Integer.parseInt(commandParts[1]);
                    delete(index);
                } catch (NumberFormatException e) {
                    System.out.println("\tTask's index must be a number!");
                }
                break;
            case "add":
                Task temp = new Task(commandParts[1]);
                add(temp);
                break;
            default:
                echo(command);
                break;
            }
        }
    }

    /**
     * Lists all tasks in the task list.
     */
    private void list() {
        System.out.println("\tHere are the tasks in your list:");
        int index = 1;

        for (Task task : tasks) {
            System.out.printf("\t%d.%s %s%n", index++, task.getStatusIcon(), task.getDescription());
        }
    }

    /**
     * Adds a new task to the task list.
     *
     * @param task The task to add.
     */
    private void add(Task task) {
        tasks.add(task);
        System.out.println("\tadded: " + task.getDescription());
    }

    /**
     * Echoes the user's command back to them.
     *
     * @param command The command to echo.
     */
    private void echo(String command) {
        System.out.println("\t" + command);
    }

    /**
     * Marks a task as done.
     *
     * @param index The index of the task to mark.
     */
    private void mark(int index) {
        try {
            index = index - 1; /* Convert to zero-based index */
            Task taskToMark = tasks.get(index);
            taskToMark.markAsDone();

            System.out.println("\tNice! I've marked this task as done:");
            System.out.printf("\t   %s %s%n", taskToMark.getStatusIcon(), taskToMark.getDescription());
        } catch (IndexOutOfBoundsException e) {
            System.out.println("\tIndex is out of bound!");
        }
    }

    /**
     * Marks a task as not done yet.
     *
     * @param index The index of the task to mark.
     */
    private void unmark(int index) {
        try {
            index = index - 1; /* Convert to zero-based index */
            Task taskToMark = tasks.get(index);
            taskToMark.markAsUndone();

            System.out.println("\tOK, I've marked this task as not done yet:");
            System.out.printf("\t   %s %s%n", taskToMark.getStatusIcon(), taskToMark.getDescription());
        } catch (IndexOutOfBoundsException e) {
            System.out.println("\tIndex is out of bound!");
        }
    }

    /**
     * Deletes a task from the task list.
     *
     * @param index The index of the task to delete.
     */
    private void delete(int index) {
        try {
            index = index - 1; /* Convert to zero-based index */
            Task task = tasks.get(index);
            tasks.remove(index);

            System.out.println("\tOK, I've deleted this task:");
            System.out.printf("\t   %s%n", task.getDescription());

            list(); // Display the updated task list
        } catch (IndexOutOfBoundsException e) {
            System.out.println("\tIndex is out of bound!");
        }
    }

    /**
     * Prints a user guide.
     */
    private void help() {
        String guide = "\t- add [task description]: Add a new task to our list.\n"
                + "\t- list: List all current tasks.\n"
                + "\t- delete [task index]: Delete the task at the given index.\n"
                + "\t- mark [task index]: Mark the task at the given index as done.\n"
                + "\t- unmark [task index]: Mark the task at the given index as undone.\n"
                + "\t- help: Print this guide.\n"
                + "\t- bye: End the program.";
        System.out.println(guide);
    }

    /**
     * Prints a greeting message when the chatbot starts.
     *
     * @param name The name of the chatbot.
     * @param logo The logo of the chatbot.
     */
    public void printGreet(String name, String logo) {
        System.out.println(logo);
        printHorizontalLine(40);
        System.out.println("\tHello sweeties! I'm " + name + "!");
        System.out.println("\tWhat can I do for you? Let's chat <3");
    }

    /**
     * Prints a farewell message.
     */
    public void printExit() {
        System.out.println("\tBye bae. Hope to see you again soon! Moah~");
        printHorizontalLine(40);
    }

    /**
     * Retrieves the next command from the user.
     *
     * @return The command input by the user.
     */
    public String getCommand() {
        printHorizontalLine(40);

        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    /**
     * Prints a horizontal line with a specified number of dashes.
     *
     * @param numDash The number of dashes to print.
     */
    public void printHorizontalLine(int numDash) {
        System.out.print("\t");

        for (int i = 0; i < numDash; i++) {
            System.out.print("_");
        }

        System.out.print("\n");
    }
}
