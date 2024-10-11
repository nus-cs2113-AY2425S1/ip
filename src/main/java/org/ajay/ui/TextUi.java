package org.ajay.ui;

import org.ajay.common.Messages;

/**
 * Text Ui class deals with the user interface of the application.
 */
public class TextUi {

    /** Prints a break line to the console. */
    public static void printBreakLine() {
        System.out.println(Messages.MESSAGE_BREAKLINE);
    }

    /**
     * Gracefully exits the program.
     *
     * @param status the status code to exit.
     */
    public static void exit(int status) {
        System.exit(status);
    }

    /** Prints the prompt to the console for visual marker for user to type. */
    public static void printPrompt() {
        System.out.print(Messages.MESSAGE_PROMPT);
    }

    /** Prints the logo to the console. */
    public void printLogo() {
        System.out.println(Messages.MESSAGE_LOGO);
    }

    /**
     * Prints exceptions message to the console in red color.
     *
     * @param errorMsg The error message to be printed.
     */
    public static void printExceptions(String errorMsg) {
        printBreakLine();
        System.out.println(Color.ANSI_RED + errorMsg + Color.ANSI_RESET);
        printBreakLine();
    }

    /**
     * Prints the error message to the console in green color.
     *
     * @param successMsg The success message to be printed.
     */
    public static void printSuccess(String successMsg) {
        System.out.println(Color.ANSI_GREEN + successMsg + Color.ANSI_RESET);
    }

    /**
     * Prints the info message to the console in CYAN color.
     *
     * @param infoMsg The info message to be printed.
     */
    public static void printInfo(String infoMsg) {
        System.out.println(Color.ANSI_CYAN + infoMsg + Color.ANSI_RESET);
    }

    /**
     * Prints the warning message to the console in yellow color.
     *
     * @param warningMsg The warning message to be printed.
     */
    public static void printWarning(String warningMsg) {
        System.out.println(Color.ANSI_YELLOW + warningMsg + Color.ANSI_RESET);
    }

    /** Prints the greeting messages to the console. */
    public void printGreetingMsgs() {
        String[] greetingMsgs = { Messages.MESSAGE_WELCOME };
        for (String greetingMsg : greetingMsgs) {
            printBreakLine();
            System.out.println(greetingMsg);
        }
        printBreakLine();
    }

    /** Prints the goodbye messages to the console. */
    public void printGoodbyeMsgs() {
        String[] goodbyeMsgs = { Messages.MESSAGE_EXIT };
        for (String goodbyeMsg : goodbyeMsgs) {
            printBreakLine();
            System.out.println(goodbyeMsg);
        }
        printBreakLine();

        /** exit gracefully */
        exit(0);
    }

    /** Prints the help messages to the console. */
    public static void printHelpMsgs() {
        String[] helpMsgs = { Messages.MESSAGE_HELP };
        printBreakLine();
        for (String helpMsg : helpMsgs) {
            System.out.println(helpMsg);
        }
        printBreakLine();
    }

}
