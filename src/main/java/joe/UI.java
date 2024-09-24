package joe;

import java.util.Arrays;

/**
 * UI class encapsulating the essential methods and constants that design the user interface
 */
public class UI {

    private static final String SEPARATOR = "_________________________________________________";
    private static final String INTENDATION = "      ";
    private static final String LOGO = "    (_)           \n"
            + INTENDATION + "     _  ___   ___ \n"
            + INTENDATION + "    | |/ _ \\ / _ \\\n"
            + INTENDATION + "    | | (_) |  __/\n"
            + INTENDATION + "    | |\\___/ \\___|\n"
            + INTENDATION + "   _/ |           \n"
            + INTENDATION + "  |__/            \n";

    public static void printReply(String input, String actionPerformed) {
        System.out.println(INTENDATION + actionPerformed + input);
        System.out.println(INTENDATION + SEPARATOR);
    }

    public static void printMultiLine(String[] inputs) {
        Arrays.stream(inputs)
            .forEachOrdered(input -> System.out.println(INTENDATION + input));
    }

    public static void printGreeting() {
        System.out.println(INTENDATION + LOGO);
        System.out.println(INTENDATION + SEPARATOR);
        System.out.println(INTENDATION + "Hello I'm Joe");
        System.out.println(INTENDATION + "How can I help you?");
        System.out.println(INTENDATION + SEPARATOR);
    }

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
            System.out.println("This task list is empty!");
        }
    }
}
