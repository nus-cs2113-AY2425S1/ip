package tyrone.ui;

import java.util.Scanner;

/**
 * Class to receive user inputs and print to UI
 */
public class Ui {

    private static final Scanner scanner = new Scanner(System.in);

    /**
     * Print text followed by a new line.
     *
     * @param s String of text to print.
     */
    public static void println(String s) {
        System.out.println(s);
    }

    /**
     * Prints chatbot introduction message.
     * Called when chatbot is started up.
     */
    public void printIntroMessage() {
        System.out.println("Hello! I'm Tyrone.\nWhat can I do for you?");
    }

    /**
     * Prints chatbot exit message.
     * Called just before exiting chatbot.
     */
    public void printExitMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Reads a single line of user input.
     *
     * @return Single line of user input.
     */
    public String receiveInput() {
        return scanner.nextLine();
    }

}
