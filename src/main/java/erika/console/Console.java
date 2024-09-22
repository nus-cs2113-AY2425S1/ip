package erika.console;

import erika.exception.EmptyListException;
import erika.task.Task;
import erika.tasklist.TaskList;
/**
 * Represents the terminal interface between the users and the program
 * Methods of this class serve to <code>print</code> information to the terminal
 */
public class Console {
    private TaskList tasks;
    /**
     * @param tasks The <code>TaskList</code> of current tasks
     */
    public Console(TaskList tasks) {
        this.tasks = tasks;
    }
    /**
     * Prints the "Welcome Message" (first message users see when starting the application)
     */
    public static void printWelcomeMessage() {
        String logo =
                " _______   ________  ___  ___  __    ________     \n" +
                        "|\\  ___ \\ |\\   __  \\|\\  \\|\\  \\|\\  \\ |\\   __  \\    \n" +
                        "\\ \\   __/|\\ \\  \\|\\  \\ \\  \\ \\  \\/  /|\\ \\  \\|\\  \\   \n" +
                        " \\ \\  \\_|/_\\ \\   _  _\\ \\  \\ \\   ___  \\ \\   __  \\  \n" +
                        "  \\ \\  \\_|| \\ \\  \\\\  \\\\ \\  \\ \\  \\\\ \\  \\ \\  \\ \\  \\ \n" +
                        "   \\ \\_______\\ \\__\\\\ _\\\\ \\__\\ \\__\\\\ \\__\\ \\__\\ \\__\\\n" +
                        "    \\|_______|\\|__|\\|__|\\|__|\\|__| \\|__|\\|__|\\|__|";
        System.out.println("\tHello from\n" + logo + "\n");
        printMessage("Hello! I'm Erika\n \tWhat can I do for you?");
    }
    /**
     * Generic printing method to print output onto the terminal
     * @param message <code>String</code> to be printed to the terminal
     */
    public static void printMessage(String message) {
        System.out.println("\t____________________________________________________________");
        System.out.println("\t" + message);
        System.out.println("\t____________________________________________________________");
    }
    /**
     * Prints a confirmational message when entries are added to the <code>TaskList</code> of tasks
     * @param newTask <code>Task</code> that was added
     */
    public static void printAddedMessage(Task newTask) {
        String message = "Got it. I've added this task:\n" + "\t  " + newTask + "\n" +
                String.format("\tNow you have %d task%s in the list.", TaskList.getTaskArraySize(),
                        (TaskList.getTaskArraySize() > 1) ? "s" : "");
        printMessage(message);
    }
    /**
     * Prints a confirmational message when entries are added to the <code>TaskList</code> of tasks
     * @param deletedTask <code>Task</code> that was deleted
     */
    public static void printDeletedMessage(Task deletedTask) {
        String message = "Nice! I've deleted this task:\n" + "\t" + deletedTask + "\n\t" +
                "Now you have " + (TaskList.getTaskArraySize() - 1) + " tasks in the list.";
        printMessage(message);
    }
    /**
     * Prints a confirmational message when entries are added to the <code>TaskList</code> of tasks
     * @param markedTask <code>Task</code> that was marked as done
     */
    public static void printMarkedMessage(Task markedTask) {
        String message = "Nice! I've marked this task as done:\n" + "\t\t" + markedTask;
        printMessage(message);
    }
    /**
     * Prints a confirmational message when entries are added to the <code>TaskList</code> of tasks
     * @param unmarkedTask <code>Task</code> that was marked as not done
     */
    public static void printUnmarkedMessage(Task unmarkedTask) {
        String message = "Nice! I've marked this task as not done yet:\n" + "\t\t" + unmarkedTask;
        printMessage(message);
    }
    /**
     * Prints the "Goodbye Message" (last message users see when terminating the application)
     */
    public static void printGoodbyeMessage() {
        printMessage("Bye! Hope to see you again soon!");
    }
}
