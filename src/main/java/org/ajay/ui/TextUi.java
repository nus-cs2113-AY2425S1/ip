package org.ajay.ui;

import org.ajay.common.Messages;

public class TextUi {
    /**
     * Prints a break line to the console.
     */
    public static void printBreakLine() {
        System.out.println(Messages.MESSAGE_BREAKLINE);
    }

    /**
     * Gracefully exits the program.
     *
     * @param status
     */
    public static void exit(int status) {
        System.exit(status); // Print the goodbye message
    } // Print the goodbye message

    /**
     * Prints the prompt to the console for visual marker for user to type.
     */
    public static void printPrompt() {
        System.out.print(Messages.MESSAGE_PROMPT);
    }

    public void printLogo() {
        System.out.println(Messages.MESSAGE_LOGO);
    }

    public static void printExceptions(String errorMsg) {
        printBreakLine();
        System.out.println(Color.ANSI_RED + errorMsg + Color.ANSI_RESET);
        printBreakLine();
    }

    public static void printSuccess(String successMsg) {
        System.out.println(Color.ANSI_GREEN + successMsg + Color.ANSI_RESET);
    }

    public static void printInfo(String infoMsg) {
        System.out.println(Color.ANSI_CYAN + infoMsg + Color.ANSI_RESET);
    }

    public static void printWarning(String warningMsg) {
        System.out.println(Color.ANSI_YELLOW + warningMsg + Color.ANSI_RESET);
    }

    /**
     * Prints the greeting messages to the console.
     */
    public void printGreetingMsgs() {
        String[] greetings = { Messages.MESSAGE_WELCOME }; // List of greetings

        // Print the greetings
        for (String greeting : greetings) {
            printBreakLine(); // Print a break line before each greeting
            System.out.println(greeting); // Print the greeting
        }
        printBreakLine();
    }

    /**
     * Prints the goodbye messages to the console.
     */
    public void printGoodbyeMsgs() {
        String[] goodbyes = { Messages.MESSAGE_EXIT }; // List of goodbye messages
        // List of goodbye messages
        // Print the goodbye messages
        for (String goodbye : goodbyes) {
            printBreakLine();
            System.out.println(goodbye); // Print the goodbye message
        } // Print the goodbye message
        printBreakLine();
        exit(0);
    }

    public static void printHelpMsgs() {
        String[] helpMsgs = { Messages.MESSAGE_HELP };
        printBreakLine();
        // Print the help messages
        for (String helpMsg : helpMsgs) {
            System.out.println(helpMsg); // Print the help message
        } // Print the help message
        printBreakLine();
    }

}
