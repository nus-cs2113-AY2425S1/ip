import java.util.Scanner;

public class Jarvis {
    private static final String chatBotName = "Jarvis"; // Name of the chatbot


    /**
     * Prints a break line to the console.
     */
    private static void printBreakLine() {
        System.out.println("    ────────────────────────────────────────────────────────────");
    }

    /**
     * Gracefully exits the program.
     *
     * @param status
     */
    private static void exit(int status) {
        System.exit(status);
    }

    /**
     * Prints the greeting messages to the console.
     */
    private static void printGreetingMsgs() {
        String[] greetings = {"    Hello! I'm " + chatBotName + "\n    What can I do for you?"}; // List of greetings

        // Print the greetings
        for (String greeting : greetings) {
            printBreakLine(); // Print a break line before each greeting
            System.out.println(greeting); // Print the greeting
        }
        printBreakLine();
    }

    private static void printGoodbyeMsgs() {
        String[] goodbyes = {"    Bye. Hope to see you again soon!"}; // List of goodbye messages

        // Print the goodbye messages
        for (String goodbye : goodbyes) {
            printBreakLine();
            System.out.println(goodbye); // Print the goodbye message
        }
        printBreakLine();
        exit(0);
    }

    public static void echoUserInput(Scanner in, String lineBufferString) {

        try (in) {
            lineBufferString = in.nextLine();

            if (lineBufferString.isEmpty()) {
                System.out.println("You did not enter anything. Please try again.");
                return;
            } else if (lineBufferString.equalsIgnoreCase("bye")) {
                printGoodbyeMsgs();
            } else {
                printBreakLine();
                System.out.println("    " + lineBufferString);
                printBreakLine();
                echoUserInput(in, lineBufferString);
            }
        } catch (Exception e) {
            System.err.println("An error occurred. Please try again.");
        }
    }

    public static void main(String[] args) {
        String logo = """
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

        String lineBufferString = ""; // Buffer to store the input from the user
        Scanner in = new Scanner(System.in); // Scanner object to read input from the user

        System.out.println("Hello from\n" + logo);
        printGreetingMsgs();
        echoUserInput(in, lineBufferString);

    }
}
