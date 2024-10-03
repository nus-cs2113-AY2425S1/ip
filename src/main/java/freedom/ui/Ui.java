package freedom.ui;

import java.util.Scanner;

/**
 * Class to facilitate program interactions with the user.
 */
public class Ui {
    private final static String DIVIDER = "\t________________________________________\n";
    private static Scanner scanner = new Scanner(System.in);

    /**
     * Reads and returns the user's input.
     */
    public String readInput() {
        return scanner.nextLine();
    }

    /**
     * Prints the welcome message of the chatbot.
     */
    public void printStartMessage() {
        printHeadDivider();
        System.out.print("""
                \tHello! I'm Freedom
                \tWhat can I do for you?
                """);
        printTailDivider();
    }

    /**
     * Prints the ending message of the chatbot.
     */
    public void printEndMessage() {
        printHeadDivider();
        System.out.println("\tBye! Hope to see you again soon.");
        printTailDivider();
    }

    /**
     * Indicates the command provided is invalid.
     */
    public void printInvalidCommand() {
        printHeadDivider();
        System.out.println("\tSorry! I don't understand your command");
        printTailDivider();
    }

    /**
     * Indicates the date and time provided is invalid.
     */
    public void printInvalidDateTime() {
        printHeadDivider();
        System.out.print("""
                \tDate and/or Time is not correctly formatted!
                \tUse this: dd/mm/yyyy hhmm
                \tYou can use placeholders like 0000 if the time is not specified.
                """);
        printTailDivider();
    }

    /**
     * Prints horizontal line to indicate the start of a chatbot response.
     */
    public void printHeadDivider() {
        System.out.print(DIVIDER);
    }

    /**
     * Prints horizontal line to indicate the end of a chatbot response.
     */
    public void printTailDivider() {
        System.out.println(DIVIDER);
    }

    public void printPlaceholder() {
        System.out.print("");
    }
}
