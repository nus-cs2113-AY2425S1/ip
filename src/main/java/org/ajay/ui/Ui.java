package org.ajay.ui;

import org.ajay.utils.Constants;


public class Ui {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public final static String EXIT_STRING = "bye";
    public final static String EXIT_STRING_ALT = "exit";



    static String logo = """
                              @@@@@@@@@@@@@@@@@@@@@@@
                          @@%     @@          @@      @@@
                       @@         @@          @@          @@
                    @@            @@          @@             @@
                  @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
                 @                                              *@
                @@@                                             @@@
               @  @@@                                         @@@  @
              @&    @@         @@@@@@@@@@@@@@@@@@@@@         @@.   @@
              @      @@@        @@              @@         @@@      @
              @       *@@         @@           @(         @@(       @
              @       ,@@@@        @@        @@         @@@@        @
              @   @@@@@  %@@         @@     @          @@& @@@@@    @
              @@@@         @@@        /@  @@         @@@       *@@@@/
               @@           @@@         @@          @@@           @@
                @@            @@&                 (@@            @@
                  @       @@@@@@@@               @@@@@@@        @
                   @@ @@@@*      @@,            @@      @@@@  @&
                      @@          @@@         @@@          @@
                        @@@         @@       @@         @@#
                            @@@@     @@@   @@@     @@@@
                                   @@@@@@@@@@@@@

                 ██╗    █████╗    ██████╗   ██╗   ██╗  ██╗   ███████╗
                 ██║   ██╔══██╗   ██╔══██╗  ██║   ██║  ██║   ██╔════╝
                 ██║   ███████║   ██████╔╝  ██║   ██║  ██║   ███████╗
            ██   ██║   ██╔══██║   ██╔══██╗  ╚██╗ ██╔╝  ██║   ╚════██║
            ╚█████╔╝██╗██║  ██║██╗██║  ██║██╗╚████╔╝██╗██║██╗███████║██╗
             ╚════╝ ╚═╝╚═╝  ╚═╝╚═╝╚═╝  ╚═╝╚═╝ ╚═══╝ ╚═╝╚═╝╚═╝╚══════╝╚═╝
            """;

    /**
     * Prints a break line to the console.
     */
    public static void printBreakLine() {
        System.out.println(ANSI_BLACK + "────────────────────────────────────────────────────────────" + ANSI_RESET);
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
        System.out.print("  ");
    }

    public static void printLogo() {
        System.out.println("Hello from\n" + ANSI_BLUE + logo + ANSI_RESET);
    }

    public static void printExceptions(String errorMsg) {
        System.out.println(ANSI_RED + errorMsg + ANSI_RESET);
    }

    public static void printSuccess(String successMsg) {
        System.out.println(ANSI_GREEN + successMsg + ANSI_RESET);
    }

    public static void printInfo(String infoMsg) {
        System.out.println(ANSI_CYAN + infoMsg + ANSI_RESET);
    }

    public static void printWarning(String warningMsg) {
        System.out.println(ANSI_YELLOW + warningMsg + ANSI_RESET);
    }

    /**
     * Prints the greeting messages to the console.
     */
    public static void printGreetingMsgs() {
        String[] greetings = {"Hello! I'm " + ANSI_YELLOW + Constants.chatBotName + ANSI_RESET + "\nWhat can I do for you?"}; // List of greetings

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
    public static void printGoodbyeMsgs() {
        String[] goodbyes = {"Bye. Hope to see you again soon!"}; // List of goodbye messages
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
        String[] helpMsgs = {"Here are the list of commands you can use:", "1. todo <task> - Adds a todo task to the list", "2. deadline <task> /by <date> - Adds a deadline task to the list", "3. event <task> /from <date> /to <date> - Adds an event task to the list", "4. list - Lists all the tasks in the list", "5. done <task number> - Marks the task as done", "6. delete <task number> - Deletes the task from the list", "7. find <keyword> - Finds the tasks with the keyword", "8. help - Shows the list of commands", "9. bye | exit - Exits the program"
        };

        // Print the help messages
        for (String helpMsg : helpMsgs) {
            printBreakLine();
            System.out.println(helpMsg); // Print the help message
        } // Print the help message
        printBreakLine();
    }


}
