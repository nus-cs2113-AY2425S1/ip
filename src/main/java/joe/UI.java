package joe;

import java.util.Arrays;

/**
 * UI class encapsulating the essential methods and constants that design the user interface
 */
public class UI {

    private static final String SEPARATOR = "_________________________________________________________________________";
    private static final String INTENDATION = "      ";
    private static final String LOGO = "    (_)           \n"
            + INTENDATION + "     _  ___   ___ \n"
            + INTENDATION + "    | |/ _ \\ / _ \\\n"
            + INTENDATION + "    | | (_) |  __/\n"
            + INTENDATION + "    | |\\___/ \\___|\n"
            + INTENDATION + "   _/ |           \n"
            + INTENDATION + "  |__/            \n";

    /**
     * Encapsulates basic formatting for maintaining standard output structure
     * @param input String to be formatted and printed to the UI
     * @param actionPerformed String specifying a certain action indicator to the user
     */
    public static void printReply(String input, String actionPerformed) {
        System.out.println("");
        System.out.println(INTENDATION + actionPerformed + input);
        System.out.println(INTENDATION + SEPARATOR);
    }

    /**
     * Extension of printReply for longer inputs
     * @param inputs Array of Strings to be outputted in standard format
     */
    public static void printMultiLine(String[] inputs) {
        Arrays.stream(inputs)
            .forEachOrdered(input -> System.out.println(INTENDATION + input));
        System.out.println(INTENDATION + SEPARATOR);
    }

    /**
     * Encapsulates statements executed at the start of the application
     */
    public static void printGreeting() {
        System.out.println(INTENDATION + LOGO);
        System.out.println(INTENDATION + SEPARATOR);
        System.out.println(INTENDATION + "Hello I'm Joe");
        System.out.println(INTENDATION + "How can I help you?");
        System.out.println(INTENDATION + SEPARATOR);
    }

    /**
     * Encapsulates code executed at termination of the application
     */
    public static void printFarewell() {
        System.out.println(INTENDATION + "See you soon!");
        System.out.println(INTENDATION + SEPARATOR);
    }

    /**
     * Takes in a toDoItemArrayList and prints the description of each contained Task object
     * @param taskList the TaskList whose Task's descriptions is printed out
     */
    public static void  printList(TaskList taskList) {
        if (taskList.size() > 0) {
            for (int i = 1; i <= taskList.size(); i++) {
                System.out.println(INTENDATION + i
                        + ": " + taskList.toTaskString(i));
            }
        } else {
            printReply("This task list is empty!", "");
        }
    }
}
